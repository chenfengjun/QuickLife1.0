/**
 * 
 */
package org.chan.quicklife.activity;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.User;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @Package org.gread.quicklife.activity.RegisterActivity.java
 * 
 * @ClassName RegisterActivity
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class RegisterActivity extends Activity implements OnClickListener {
	private Button regBtn;
	private Button backBtn;
	private EditText loginNameEt;
	private EditText pwdEt;
	private EditText nameEt;
	private ImageView ivBack;
	private IServer userBinder;
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		initComponnet();

		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		bindService(intent, sc, BIND_AUTO_CREATE);
	}

	private void initComponnet() {
		regBtn = (Button) findViewById(R.id.register_registerBtn);
		regBtn.setOnClickListener(this);

		backBtn = (Button) findViewById(R.id.register_backBtn);
		backBtn.setOnClickListener(this);

		loginNameEt = (EditText) findViewById(R.id.register_et_name);
		nameEt = (EditText) findViewById(R.id.register_et_myname);
		pwdEt = (EditText) findViewById(R.id.register_et_pwd);

		ivBack = (ImageView) findViewById(R.id.register_title_iv1);
		ivBack.setOnClickListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_registerBtn:
			String loginName = loginNameEt.getText().toString();
			String name = nameEt.getText().toString();
			String pwd = pwdEt.getText().toString();
			if (loginName.isEmpty()) {
				displayToast("�û�����Ϊ�գ�");
				return;
			}
			if (name.isEmpty()) {
				displayToast("������Ϊ�գ�");
				return;
			}
			if (pwd.isEmpty()) {
				displayToast("���벻��Ϊ�գ�");
				return;
			}
			try {
				if (userBinder.canRegister(loginName)) {
					User user = new User();
					user.setLoginName(loginName);
					user.setName(name);
					user.setPassword(pwd);
					if (userBinder.register(user)) {
						displayToast("ע��ɹ���");
						finish();
						overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
					}
				}

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.register_backBtn:
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		case R.id.register_title_iv1:
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
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		unbindService(sc);
		super.onDestroy();
	}

}
