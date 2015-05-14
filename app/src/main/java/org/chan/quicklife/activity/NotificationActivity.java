/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.adapter.FriendBaseAdapter;
import org.chan.quicklife.entity.Friend;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * @Package org.gread.quicklife.activity.ActionActivity.java
 * 
 * @ClassName ActionActivity
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class NotificationActivity extends Activity {
	private ListView friendLv;
	private ImageView backBtn;
	private FriendBaseAdapter adapter;
	private List<Friend> friends = new ArrayList<Friend>();
	private IServer friendBinder;
	private SharedPreferences shared;
	private long currentId;
	private long shipId;
	private long friendId;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			friendBinder = Stub.asInterface(service);
			refData();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_echo);
		initField();

		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");

		bindService(intent, sc, BIND_AUTO_CREATE);
	}

	/**
	 * 
	 */
	private void initField() {
		// TODO Auto-generated method stub
		shared = getSharedPreferences("state", MODE_PRIVATE);
		currentId = shared.getLong("currentUserId", -1);
		friendLv = (ListView) findViewById(R.id.echo_lv);
		adapter = new FriendBaseAdapter(this, friends);
		friendLv.setAdapter(adapter);
		friendLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long id) {
				shipId = id;
				friendId=friends.get(arg2).getMyId();
				showDialog();
			}
		});
		backBtn = (ImageView) findViewById(R.id.echo_back);
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Button bBtn = (Button) findViewById(R.id.echo_back_btn);
		bBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void refData() {
		try {
			List<Friend> refFriends = friendBinder.getFriendApply(currentId);
			friends.clear();
			for (Friend friend : refFriends) {
				friends.add(friend);
			}
			adapter.notifyDataSetChanged();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ʾ�˳�ȷ�϶Ի���
	 */
	private void showDialog() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setMessage("�Ƿ�ͬ�Ⲣ��Ϊ���ѣ�")
				.setNegativeButton("ȡ��", null)
				.setNeutralButton("�ܾ�", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							friendBinder.echoFriend(shipId, 3);
							refData();
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				})
				.setPositiveButton("ͬ��",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								try {
									friendBinder.echoFriend(shipId, 2);
									friendBinder.addFriend(currentId, friendId,
											2);
									refData();
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}).show();
	}

	@Override
	protected void onDestroy() {
		unbindService(sc);
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			return false;
		}
		return false;
	}

}
