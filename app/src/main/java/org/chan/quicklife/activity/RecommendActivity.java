/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.adapter.FriendBaseAdapter;
import org.chan.quicklife.adapter.RecommendBaseAdapter;
import org.chan.quicklife.entity.ActionLog;
import org.chan.quicklife.entity.Friend;
import org.chan.quicklife.entity.Recommend;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

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
public class RecommendActivity extends Activity {
	private ListView friendLv;
	private Button addBtn;
	private View back;
	private View tBack;
	private RecommendBaseAdapter adapter;
	private List<Friend> friends = new ArrayList<Friend>();
	private IServer friendBinder;
	private SharedPreferences shared;
	private long currentId;
	private long couponId;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			friendBinder = Stub.asInterface(service);
			refDate();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recommend);
		couponId = getIntent().getLongExtra("coupon", -1);
		initField();

		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		bindService(intent, sc, BIND_AUTO_CREATE);
	}

	private void refDate() {
		if (friendBinder == null) {
			return;
		}
		try {
			List<Friend> refFriends = friendBinder.getFriends(currentId);
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
	 * 
	 */
	private void initField() {
		// TODO Auto-generated method stub
		shared = getSharedPreferences("state", MODE_PRIVATE);
		currentId = shared.getLong("currentUserId", -1);
		friendLv = (ListView) findViewById(R.id.recommend_listView1);
		adapter = new RecommendBaseAdapter(this, friends);
		friendLv.setAdapter(adapter);

		back = findViewById(R.id.recommend_button2);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		tBack = findViewById(R.id.recommend_title_back);
		tBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		addBtn = (Button) findViewById(R.id.recommend_button1);
		addBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					List<Friend> addFris = adapter.getSelected();
					if (addFris.size() < 1) {
						displayToast("�ף���ûѡ����Ҫ�Ƽ��ĺ���Ŷ��\n�����û��þ͵�����أ�");
						return;
					}
					for (Friend friend : addFris) {
						Recommend rec = new Recommend();
						rec.setUserId(currentId);
						rec.setCouponId(couponId);
						rec.setToFriendId(friend.getFriendId());
						if (friendBinder.addRecommend(rec)) {
							displayToast("����ɹ���");
							finish();
						} else {
							displayToast("�ף����Ѿ���" + friend.getName()
									+ "������Ҫ�ٷ����ˣ�");
						}
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		refDate();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		unbindService(sc);
		super.onDestroy();
	}

	private void displayToast(String text) {
		// TODO Auto-generated method stub
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
}
