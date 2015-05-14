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
import android.content.ComponentName;
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
public class FriendsActivity extends Activity {
	private ListView friendLv;
	private Button addBtn;
	private FriendBaseAdapter adapter;
	private List<Friend> friends = new ArrayList<Friend>();
	private IServer friendBinder;
	private SharedPreferences shared;
	private long currentId;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

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
		setContentView(R.layout.activity_friendlist);
		initField();

		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");

		bindService(intent, sc, BIND_AUTO_CREATE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		refData();
		super.onResume();
	}

	private void refData() {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void initField() {
		shared = getSharedPreferences("state", MODE_PRIVATE);
		currentId = shared.getLong("currentUserId", -1);
		friendLv = (ListView) findViewById(R.id.friends_listView1);
		adapter = new FriendBaseAdapter(this, friends);
		friendLv.setAdapter(adapter);
		addBtn = (Button) findViewById(R.id.friends_add_btn);
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(FriendsActivity.this, ContactListActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
			}
		});
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
