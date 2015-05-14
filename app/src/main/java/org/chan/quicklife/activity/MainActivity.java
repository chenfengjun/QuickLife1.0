package org.chan.quicklife.activity;

import org.chan.quicklife.R;
import org.chan.quicklife.customview.MyMoveView;
import org.chan.quicklife.entity.User;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;
import org.chan.quicklife.util.DBCon;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends ActivityGroup implements OnClickListener {
	private TabActivity pager;
	private MyMoveView moveView;
	private SharedPreferences share;
	private Intent intent;
	private PopupWindow pop;
	private EditText popBindPhone;
	private EditText popPWD;
	private EditText popNewPWD;
	private EditText popPWDok;
	private View menuView;
	private Button btnBindPhone;
	private Button btnGPS;
	private Button btnPWD;
	private ToggleButton btnMyLoc;
	private View btnAbout;
	private ToggleButton btnShack;
	private Button btnExit;
	private int width;
	private IServer binder;
	private DBCon db;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("quicklife.LifeCycle ",
				"�������------------------------------------");
		Log.v("quicklife.LifeCycle ", "main.onCreate");
		super.onCreate(savedInstanceState);
		share = getSharedPreferences("state", MODE_PRIVATE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View v = LayoutInflater.from(this)
				.inflate(R.layout.activity_main, null);
		menuView = LayoutInflater.from(this)
				.inflate(R.layout.layout_menu, null);
		pager = new TabActivity(this, v);
		pager.addTab("��̬", R.drawable.main_index_search_normal,
				R.drawable.access_content_icon_bg_selector, R.id.main_lineBtn,
				new Intent(MainActivity.this, ActionActivity.class));
		pager.addTab("�Ż�", R.drawable.main_index_tuan_normal,
				R.drawable.access_content_icon_bg_selector, R.id.main_lineBtn,
				new Intent(MainActivity.this, CouponsActivity.class));
		pager.addTab("ǩ��", R.drawable.main_index_checkin_normal,
				R.drawable.access_content_icon_bg_selector, R.id.main_lineBtn,
				new Intent(MainActivity.this, CheckinActivity.class));
		pager.addTab("�ҵ�", R.drawable.main_index_my_normal,
				R.drawable.access_content_icon_bg_selector, R.id.main_lineBtn,
				new Intent(MainActivity.this, MyInfoActivity.class));
		pager.switchTab(0);
		// �����ƶ���ͼ
		moveView = new MyMoveView(this);
		Display display = getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		int Height = display.getHeight();
		initMenuView();
		moveView.initScreenSize(width, Height, menuView, v);
		setContentView(moveView);
		intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		startService(intent);
		bindService(intent, sc, BIND_AUTO_CREATE);

	}

	private void initMenuView() {
		btnBindPhone = (Button) menuView.findViewById(R.id.menu_bindPhone_btn);
		btnBindPhone.setOnClickListener(this);

		btnGPS = (Button) menuView.findViewById(R.id.menu_gps_set);
		btnGPS.setOnClickListener(this);

		btnPWD = (Button) menuView.findViewById(R.id.menu_pwd_set);
		btnPWD.setOnClickListener(this);

		btnMyLoc=(ToggleButton) menuView.findViewById(R.id.menu_tb_myLoc);
		btnMyLoc.setChecked(share.getBoolean("mylocState", true));
		btnMyLoc.setOnClickListener(this);
		
		btnShack = (ToggleButton) menuView.findViewById(R.id.menu_tb_shark);
		btnShack.setChecked(share.getBoolean("shackState", true));
		btnShack.setOnClickListener(this);

		btnAbout = menuView.findViewById(R.id.menu_tv_about);
		btnAbout.setOnClickListener(this);

		btnExit = (Button) menuView.findViewById(R.id.menu_btn_exit);
		btnExit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu_bindPhone_btn:
			boolean isLogin = share.getBoolean("loginState", false);
			if (isLogin) {
				showPopWindow();
			} else {
				displayToast("��û��¼�����¼����ֻ�");
			}
			break;
		case R.id.menu_gps_set:
			startActivity(new Intent(
					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
			break;

		case R.id.menu_pwd_set:
			showPwdWindow();
			break;

		case R.id.menu_tv_about:
			Intent intent = new Intent();
			intent.setClass(this, AboutActivity.class);
			startActivity(intent);
			break;
		case R.id.menu_tb_shark:
			Editor et = share.edit();
			et.putBoolean("shackState", !share.getBoolean("shackState", true));
			et.commit();
			btnShack.setChecked(share.getBoolean("shackState", true));
			break;
		case R.id.menu_btn_exit:
			showDialog();
			break;
		case R.id.menu_tb_myLoc:
			Editor et1 = share.edit();
			et1.putBoolean("mylocState", !share.getBoolean("mylocState", true));
			et1.commit();
			btnShack.setChecked(share.getBoolean("mylocState", true));
			break;
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		Log.v("quicklife.LifeCycle ", "main.onDestroy");
		unbindService(sc);
		stopService(intent);
		super.onDestroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		Log.v("quicklife.LifeCycle ", "main.onPause");
		super.onPause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPostResume()
	 */
	@Override
	protected void onPostResume() {
		Log.v("quicklife.LifeCycle ", "main.onPostResume");
		super.onPostResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		Log.v("quicklife.LifeCycle ", "main.onResume");
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "main.onStart");
		super.onStart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "main.onStop");
		super.onStop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	/**
	 * ��ʾ�˳�ȷ�϶Ի���
	 */
	private void showDialog() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setMessage("ȷ���˳���")
				.setNegativeButton("ȡ��", null)
				.setPositiveButton("�˳�",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						}).show();
	}

	/*
	 * ActivityGroup ��Ҫ��д�������
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (moveView.getNowState() == MyMoveView.RIGHT) {
				moveView.moveToMain((true));
				return true;
			} else {
				showDialog();
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	private void showPopWindow() {
		// ���봰�������ļ�
		View view = LayoutInflater.from(this).inflate(
				R.layout.layout_pop_bindphone, null);
		db = new DBCon(this);
		// ����PopupWindow����
		pop = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);
		popBindPhone = (EditText) view.findViewById(R.id.pop_bindphone_et);
		pop.setOutsideTouchable(true);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				if (db != null)
					db.close();
			}
		});
		Button btn1 = (Button) view.findViewById(R.id.pop_bindphone_btn);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				long id = share.getLong("currentUserId", -1);
				String num = popBindPhone.getText().toString();
				if (num.matches("^\\d{11}$")) {
					User u = new User();
					u.setId(id);
					u.setBindPhone(num);
					try {
						if (binder.modify(u)) {
							ContentValues cv = new ContentValues();
							cv.put("bind_phone", num);
							db.update("user_info", cv, "id = " + id, null);
							displayToast("�󶨳ɹ���");
						} else {
							displayToast("��ʧ�ܣ�");
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					displayToast("��������ȷ�ֻ�ţ�");
					return;
				}
				pop.dismiss();
			}
		});
		Button btn2 = (Button) view.findViewById(R.id.pop_bindphone_btn2);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		pop.showAsDropDown(btnBindPhone);
	}

	private void showPwdWindow() {
		// ���봰�������ļ�
		View view = LayoutInflater.from(this).inflate(R.layout.layout_pop_pwd,
				null);
		// ����PopupWindow����
		pop = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);
		db = new DBCon(this);
		popPWD = (EditText) view.findViewById(R.id.pop_pwd_etpwd);
		popNewPWD = (EditText) view.findViewById(R.id.pop_pwd_etnew);
		popPWDok = (EditText) view.findViewById(R.id.pop_pwd_etok);
		pop.setOutsideTouchable(true);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				if (db != null)
					db.close();
			}
		});
		Button btn1 = (Button) view.findViewById(R.id.pop_pwd_btnOK);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				long id = share.getLong("currentUserId", -1);
				String pwd1 = popPWD.getText().toString();
				try {
					if (!binder.canModify(id, pwd1)) {
						displayToast("�������");
						return;
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(popNewPWD);
				String pwd2 = popNewPWD.getText().toString();
				String pwd3 = popPWDok.getText().toString();
				if (pwd2.isEmpty()) {
					displayToast("���벻��Ϊ�գ�");
					return;
				}
				if (pwd2.equals(pwd3)) {
					User u = new User();
					u.setId(id);
					u.setPassword(pwd2);
					try {
						if (binder.modify(u)) {
							displayToast("�޸ĳɹ���");

						} else {
							displayToast("�޸�ʧ�ܣ�");
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else {
					displayToast("��������ͬ���룡");
				}
				pop.dismiss();
			}
		});
		Button btn2 = (Button) view.findViewById(R.id.pop_pwd_btnCancel);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		View v = menuView.findViewById(R.id.menu_title_tv);
		pop.showAsDropDown(v);
	}

	private void displayToast(String text) {
		// TODO Auto-generated method stub
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	
	public MyMoveView getMoveView() {
		return moveView;
	}

	
	public void setMoveView(MyMoveView moveView) {
		this.moveView = moveView;
	}
}
