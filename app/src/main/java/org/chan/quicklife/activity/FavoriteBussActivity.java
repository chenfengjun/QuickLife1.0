/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.adapter.BusinessBaseAdapter;
import org.chan.quicklife.adapter.FriendBaseAdapter;
import org.chan.quicklife.entity.Business;
import org.chan.quicklife.entity.Favorite;
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
public class FavoriteBussActivity extends Activity {
	private ListView friendLv;
	private BusinessBaseAdapter adapter;
	private List<Business> friends = new ArrayList<Business>();
	private IServer binder;
	private SharedPreferences shared;
	private long currentId;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = Stub.asInterface(service);
			
			try {
				List<Favorite> favorites=binder.getFavorites(currentId);
				friends.clear();
				for (Favorite favorite : favorites) {
					Business buss=binder.getBusiness(favorite.getBussId());
					friends.add(buss);
				}
				adapter.notifyDataSetChanged();
			} catch (RemoteException e) {
				e.printStackTrace();
			}	
			
			
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_record);
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
		friendLv = (ListView) findViewById(R.id.record_listView1);
		adapter = new BusinessBaseAdapter(this, friends);
		friendLv.setAdapter(adapter);
		
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
