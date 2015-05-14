/**
 * 
 */
package org.chan.quicklife.activity;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.User;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;
import org.chan.quicklife.util.DBCon;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Package org.gread.quicklife.activity.DetailinfoActivity.java
 * 
 * @ClassName DetailinfoActivity
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class DetailinfoActivity extends Activity implements OnClickListener {
	private ImageView ivHead;
	private EditText etLoginName;
	private EditText etName;
	private EditText etPhone;
	private TextView tvSex;
	private Button btnModify;
	private Button btnBack;
	private TextView tvLogout;
	private ImageView ivBack;
	private DBCon db;
	private SharedPreferences sharedPreferences;
	private boolean modifyState = false;
	private IServer userBinder;

	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			userBinder = Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

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
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detail_myinfo);
		db = new DBCon(DetailinfoActivity.this);
		sharedPreferences = getSharedPreferences("state", Context.MODE_PRIVATE);
		initComponent();
		getData();
		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		getApplicationContext().bindService(intent, sc, BIND_AUTO_CREATE);

	}

	private void getData() {
		new Handler().post(new Runnable() {
			@Override
			public void run() {
				Cursor c = db
						.query("select photo,name,login_name ,bind_phone,password,sex,score from user_info where id= "
								+ sharedPreferences
										.getLong("currentUserId", -1), null);
				c.moveToLast();
				String name = c.getString(c.getColumnIndex("name"));
				byte[] photo = c.getBlob(c.getColumnIndex("photo"));
				String loginName = c.getString(c.getColumnIndex("login_name"));
				String bind_phone = c.getString(c.getColumnIndex("bind_phone"));
				int sex = c.getInt(c.getColumnIndex("sex"));
				if (photo != null) {
					Bitmap head = BitmapFactory.decodeByteArray(photo, 0,
							photo.length);
					DisplayMetrics dm = getResources().getDisplayMetrics();
					int w = dm.widthPixels / 3;
					Bitmap h = Bitmap.createScaledBitmap(head, w, w, true);
					ivHead.setImageBitmap(h);
				}
				etLoginName.setText(loginName);
				etName.setText(name);
				etPhone.setText(bind_phone);
				switch (sex) {
				case 1:
					tvSex.setText("��");
					break;
				case 0:
					tvSex.setText("Ů");
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * 
	 */
	private void initComponent() {

		ivHead = (ImageView) findViewById(R.id.detail_info_ivhead);
		ivHead.setOnClickListener(this);

		ivBack = (ImageView) findViewById(R.id.detail_info_title_back);
		ivBack.setOnClickListener(this);

		etLoginName = (EditText) findViewById(R.id.detail_info_et_loginname);
		etLoginName.setOnClickListener(this);

		etName = (EditText) findViewById(R.id.detail_info_et_name);
		etName.setOnClickListener(this);

		etPhone = (EditText) findViewById(R.id.detail_info_et_phonenum);
		etPhone.setOnClickListener(this);

		tvSex = (TextView) findViewById(R.id.detail_info_sex);
		tvSex.setOnClickListener(this);

		btnModify = (Button) findViewById(R.id.detail_info_btn_modify);
		btnModify.setOnClickListener(this);

		btnBack = (Button) findViewById(R.id.detail_info_backbtn);
		btnBack.setOnClickListener(this);

		tvLogout = (TextView) findViewById(R.id.detail_info_title_tv);
		tvLogout.setOnClickListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.detail_info_ivhead:

			break;
		case R.id.detail_info_title_back:
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		case R.id.detail_info_btn_modify:
			if (!modifyState) {
				etLoginName.setEnabled(true);
				etLoginName.setInputType(InputType.TYPE_CLASS_TEXT);

				etName.setEnabled(true);
				etName.setInputType(InputType.TYPE_CLASS_TEXT);

				etPhone.setEnabled(true);
				etPhone.setInputType(InputType.TYPE_CLASS_TEXT);
				modifyState = true;
				btnBack.setText("ȡ��");
			} else {
				long id = getSharedPreferences("state", MODE_PRIVATE).getLong(
						"currentUserId", -1);
				String loginName = etLoginName.getText().toString();
				String name = etName.getText().toString();
				String bindPhone = etPhone.getText().toString();
				User user = new User();
				user.setId(id);
				user.setLoginName(loginName);
				user.setName(name);
				user.setBindPhone(bindPhone);
				try {
					if (userBinder.modify(user)) {
						etLoginName.setEnabled(false);

						etName.setEnabled(false);

						etPhone.setEnabled(false);
						ContentValues cv = new ContentValues();
						cv.put("login_name", loginName);
						cv.put("name", name);
						cv.put("bind_phone", bindPhone);
						db.update("user_info", cv, "id = " + id, null);
						displayToast("�޸ĳɹ���");
						btnBack.setText("����");
						modifyState = false;
					} else {
						displayToast("�޸�ʧ�ܣ�");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case R.id.detail_info_backbtn:
			if (modifyState) {
				etLoginName.setEnabled(false);

				etName.setEnabled(false);

				etPhone.setEnabled(false);
				btnBack.setText("����");
				modifyState = false;
				getData();
			} else {
				finish();
				overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}
			break;
		case R.id.detail_info_title_tv:
			long id = getSharedPreferences("state", MODE_PRIVATE).getLong(
					"currentUserId", -1);
			db.delete("user_info", "id = " + id, null);
			Editor edit = sharedPreferences.edit();
			edit.putBoolean("loginState", false);
			edit.remove("currentUserId");
			edit.commit();
			Intent intent = getIntent();
			intent.putExtra("detail", false);
			setResult(110, intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		default:
			break;
		}
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

	private void displayToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}

}
