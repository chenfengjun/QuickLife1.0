/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.adapter.ActionBaseAdapter;
import org.chan.quicklife.adapter.MyPagerAdapter;
import org.chan.quicklife.customview.MyMoveView;
import org.chan.quicklife.entity.ActionLog;
import org.chan.quicklife.entity.Business;
import org.chan.quicklife.entity.Friend;
import org.chan.quicklife.R;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Package org.chan.quicklife.activity.ActionActivity.java
 * 
 * @ClassName ActionActivity
 * 
 * @Description ��̬���棬��ʾ�ҵĶ�̬��Ϣ�����Ѷ�̬����Ϣ
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class ActionActivity extends Activity implements SensorEventListener {

	/** ���ں��򻬶��л�ҳ�� */
	private ViewPager viewPager;
	/** ������ */
	private SensorManager mSensorManager;
	/** �� */
	private Vibrator vibrator;
	private List<View> viewList;
	private PagerActivity pager;
	private SharedPreferences share;

	private View viewMy;
	private View viewFriend;

	// ��̬listView
	private ListView myLV;
	private ListView friendLV;

	private ProgressBar pb;
	private TextView tv1;
	private TextView tv2;

	private List<ActionLog> myActions = new ArrayList<ActionLog>();
	private List<ActionLog> friendActions = new ArrayList<ActionLog>();

	private ActionBaseAdapter myAdapter;
	private ActionBaseAdapter friendAdapter;

	private TextView menuBtn;
	private IServer actionBinder;
	private long currentId;
	// ���ӷ���
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			actionBinder = Stub.asInterface(service);
			new Thread(new RefDataRunnable()).start();
			if (share.getBoolean("loginState", false)) {
				getMessage();
			}
		}
	};

	/** UIͬ�� */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				myActions.clear();
				friendActions.clear();
				break;
			case 2:
				ActionLog action = (ActionLog) msg.obj;
				myActions.add(action);
				break;
			case 3:
				ActionLog faction = (ActionLog) msg.obj;
				friendActions.add(faction);
				break;
			default:
				break;
			}
			myAdapter.notifyDataSetChanged();
			friendAdapter.notifyDataSetChanged();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("quicklife.LifeCycle ", "action.onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action);

		viewList = new ArrayList<View>();
		viewPager = (ViewPager) findViewById(R.id.main_action_vPager);
		pager = new PagerActivity(this, viewPager, viewList);
		viewMy = LayoutInflater.from(this)
				.inflate(R.layout.layout_action, null);
		viewFriend = LayoutInflater.from(this).inflate(R.layout.layout_action,
				null);
		pager.addTab(R.id.main_action_tv1, viewMy);
		pager.addTab(R.id.main_action_tv2, viewFriend);
		viewPager.setAdapter(new MyPagerAdapter(viewList));
		pager.initImageView(R.id.main_action_cursor);
		pager.setPageChangeListener();
		pager.switchTab(0);

		initComponet();
		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		getApplicationContext().bindService(intent, sc, BIND_AUTO_CREATE);
		initShack();
	}

	/**
	 * ��ʼ��ҡһҡ
	 */
	private void initShack() {
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	/**
	 * ��ʼ�����
	 */
	private void initComponet() {
		share = getSharedPreferences("state", MODE_PRIVATE);
		currentId = share.getLong("currentUserId", -1);

		friendLV = (ListView) viewFriend.findViewById(R.id.layout_action_lv);
		friendAdapter = new ActionBaseAdapter(this, friendActions);
		friendLV.setAdapter(friendAdapter);
		friendLV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ActionLog actionLog = myActions.get(arg2);
				if (actionLog.getAcType() != 1) {
					return;
				}
				try {
					Business business = actionBinder.getBusiness(actionLog
							.getBussId());
					Intent intent = new Intent();
					intent.setClass(ActionActivity.this, MyMapActivity.class);
					intent.putExtra("lon", business.getLon());
					intent.putExtra("lat", business.getLat());
					intent.putExtra("name", business.getName());
					startActivity(intent);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		tv1 = (TextView) viewMy.findViewById(R.id.layout_action_tv);
		tv2 = (TextView) viewFriend.findViewById(R.id.layout_action_tv);
		pb = (ProgressBar) findViewById(R.id.main_action_progressBar1);
		myLV = (ListView) viewMy.findViewById(R.id.layout_action_lv);
		myAdapter = new ActionBaseAdapter(this, myActions);
		myLV.setAdapter(myAdapter);
		myLV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ActionLog actionLog = myActions.get(arg2);
				// �ж������ǩ���¼��ͷ���
				if (actionLog.getAcType() != 1) {
					return;
				}
				try {
					Business business = actionBinder.getBusiness(actionLog
							.getBussId());
					Intent intent = new Intent();
					intent.setClass(ActionActivity.this, MyMapActivity.class);
					intent.putExtra("lon", business.getLon());
					intent.putExtra("lat", business.getLat());
					intent.putExtra("name", business.getName());
					pb.setVisibility(View.VISIBLE);
					startActivity(intent);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		menuBtn = (TextView) findViewById(R.id.main_action_title_iv2);
		menuBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity main = (MainActivity) getParent();
				if (main.getMoveView().getNowState() == MyMoveView.MAIN)
					main.getMoveView().moveToRight(true);
				if (main.getMoveView().getNowState() == MyMoveView.RIGHT)
					main.getMoveView().moveToMain(true);
			}
		});

	}

	/**
	 * ��ȡ֪ͨ��Ϣ
	 */
	private void getMessage() {
		String contentText;
		try {
			List<Friend> friends = actionBinder.getFriendApply(currentId);
			if (friends.size() > 0) {
				if (friends.size() > 1) {
					contentText = friends.get(0).getName() + "��"
							+ friends.get(1).getName() + "���û�����������";
				} else {
					contentText = friends.get(0).getName() + "����������";
				}
				// ����֪ͨ
				NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				Notification nfc = new Notification();
				nfc.icon = R.drawable.ic_logo_s;
				nfc.defaults = Notification.DEFAULT_SOUND; // Ĭ��֪ͨ������
				nfc.tickerText = "��������Ϣ��";
				nfc.flags = Notification.FLAG_AUTO_CANCEL;
				PendingIntent pIntent = PendingIntent.getActivity(
						ActionActivity.this, 100,
						new Intent(ActionActivity.this,
								NotificationActivity.class),
						PendingIntent.FLAG_UPDATE_CURRENT);
				nfc.setLatestEventInfo(ActionActivity.this, "��������",
						contentText, pIntent);
				nm.notify(0, nfc);
			}
		} catch (Exception e) {
			return;
		}
	}

	/**
	 * @Package org.chan.quicklife.activity.ActionActivity.java
	 * 
	 * @ClassName RefDataRunnable
	 * 
	 * @Description ˢ������߳�
	 * 
	 * @Author ChenFengjun
	 * 
	 * @Version v1.0
	 */
	class RefDataRunnable implements Runnable {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			if (!share.getBoolean("loginState", false)) {
				return;
			}
			if (actionBinder != null) {
				try {
					handler.sendEmptyMessage(1);
					List<ActionLog> myAc = actionBinder.getActionIds(currentId);
					for (ActionLog actionLog : myAc) {
						ActionLog ac = actionBinder.getActionById(actionLog
								.getId());
						Message msg = handler.obtainMessage();
						msg.what = 2;
						msg.obj = ac;
						handler.sendMessage(msg);
					}
					List<Friend> friends = actionBinder.getFriends(currentId);
					for (Friend friend : friends) {
						List<ActionLog> friendAc = actionBinder
								.getActionIds(friend.getFriendId());
						for (ActionLog actionLog : friendAc) {
							ActionLog ac = actionBinder.getActionById(actionLog
									.getId());
							Message msg = handler.obtainMessage();
							msg.what = 3;
							msg.obj = ac;
							handler.sendMessage(msg);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	protected void onDestroy() {
		Log.v("quicklife.LifeCycle ", "action.onDestroy");
		getApplicationContext().unbindService(sc);
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		Log.v("quicklife.LifeCycle ", "action.onPause");
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	protected void onPostResume() {
		Log.v("quicklife.LifeCycle ", "action.onPostResume");
		super.onPostResume();
	}

	@Override
	protected void onResume() {
		Log.v("quicklife.LifeCycle ", "action.onResume");
		pb.setVisibility(View.INVISIBLE);
		if (share.getBoolean("loginState", false)) {
			currentId = share.getLong("currentUserId", -1);
			new Thread(new RefDataRunnable()).start();
			tv1.setVisibility(View.INVISIBLE);
			tv2.setVisibility(View.INVISIBLE);
		} else {
			myActions.clear();
			friendActions.clear();
			myAdapter.notifyDataSetChanged();
			friendAdapter.notifyDataSetChanged();
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
		}
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.v("quicklife.LifeCycle ", "action.onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.v("quicklife.LifeCycle ", "action.onStop");
		super.onStop();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (!share.getBoolean("shackState", true)) {
			return;
		}
		int sensorType = event.sensor.getType();
		int shakeSenseValue = 14;
		float[] values = event.values;
		if (sensorType == Sensor.TYPE_ACCELEROMETER) {
			if ((Math.abs(values[0]) > shakeSenseValue
					|| Math.abs(values[1]) > shakeSenseValue || Math
					.abs(values[2]) > shakeSenseValue)) {
				new Thread(new RefDataRunnable()).start();
				displayToast("���ˢ����...");
				vibrator.vibrate(500);
			}
		}
	}

	/**
	 * @param string
	 */
	private void displayToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}

}
