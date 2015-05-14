/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.Friend;
import org.chan.quicklife.entity.User;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;
import org.chan.quicklife.util.DBCon;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @Package org.gread.quicklife.activity.LoginActivity.java
 * 
 * @ClassName LoginActivity
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class LoginActivity extends Activity implements OnClickListener {
	private IServer userBinder;
	private Intent intent;
	private EditText nameEt;
	private EditText pwdEt;
	private Button loginBtn;
	private Button regBtn;
	private View back;
	private DBCon db;
	private long currentId;
	private SharedPreferences sharedPreferences;
	private ServiceConnection sc = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			sc = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			userBinder = Stub.asInterface(service);
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		db = new DBCon(this);
		sharedPreferences = getSharedPreferences("state", Context.MODE_PRIVATE);

		nameEt = (EditText) findViewById(R.id.login_et_name);
		pwdEt = (EditText) findViewById(R.id.login_et_pwd);
		loginBtn = (Button) findViewById(R.id.login_logBtn);
		regBtn = (Button) findViewById(R.id.login_regBtn);
		back=findViewById(R.id.login_title_iv1);
		back.setOnClickListener(this);
		loginBtn.setOnClickListener(this);
		regBtn.setOnClickListener(this);

		intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		getApplicationContext().bindService(intent, sc, BIND_AUTO_CREATE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.login_logBtn:
			String name = nameEt.getText() + "";
			String pwd = pwdEt.getText() + "";

			User user = new User();
			user.setLoginName(name);
			user.setPassword(pwd);
			try {
				User reply = userBinder.login(user);
				if (reply.getId() != -1) {
					ContentValues cv = new ContentValues();
					currentId=reply.getId();
					cv.put("id", reply.getId());
					cv.put("login_name", reply.getLoginName());
					cv.put("name", reply.getName());
					cv.put("password", reply.getPassword());
					cv.put("sex", reply.getSex());
					cv.put("photo", reply.getPhoto());
					cv.put("lat", reply.getLat());
					cv.put("lon", reply.getLon());
					cv.put("score", reply.getScore());
					cv.put("bind_phone", reply.getBindPhone());
					cv.put("last_login_time", reply.getLastLocTime());
					cv.put("last_loc_time", reply.getLastLocTime());
					db.insert("user_info", cv);
					Editor edit = sharedPreferences.edit();
					edit.putBoolean("loginState", true);
					edit.putLong("currentUserId", reply.getId());
					edit.commit();
					Intent intent = getIntent();
					intent.putExtra("login", true);
					setResult(100, intent);
					getMessage();
					finish();
					overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				} else {
					displayToast("�û�����������");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		// TODO ע��
		case R.id.login_regBtn:
			Intent intent=new Intent();
			intent.setClass(this, RegisterActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		case R.id.login_title_iv1:
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		default:
			break;
		}
	}

	

	private void displayToast(String txt) {
		Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			return false;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		getApplicationContext().unbindService(sc);
		db.close();
	}
	private void getMessage() {
		String contentText;
		try {
			List<Friend> friends = userBinder.getFriendApply(currentId);
			
			if (friends.size() > 0) {
				if(friends.size()>1){
					contentText=friends.get(0).getName()+"��"+friends.get(0).getName()+"���û�����������";
				}else{
					contentText=friends.get(0).getName()+"����������";
				}
				NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				Notification nfc = new Notification();
				nfc.icon = R.drawable.ic_logo_s;
				nfc.defaults = Notification.DEFAULT_SOUND; // Ĭ��֪ͨ������
				nfc.tickerText = "��������Ϣ��";
				nfc.flags = Notification.FLAG_AUTO_CANCEL;
				PendingIntent pIntent = PendingIntent.getActivity(
						LoginActivity.this, 100,
						new Intent(LoginActivity.this,
								NotificationActivity.class),
						PendingIntent.FLAG_UPDATE_CURRENT);
				nfc.setLatestEventInfo(LoginActivity.this, "��������", contentText,
						pIntent);
				nm.notify(0, nfc);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
