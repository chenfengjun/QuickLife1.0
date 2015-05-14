/**
 * 
 */
package org.chan.quicklife.activity;

import org.chan.quicklife.R;
import org.chan.quicklife.customview.MyMoveView;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;
import org.chan.quicklife.util.DBCon;

import android.app.Activity;
import android.content.ComponentName;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
public class MyInfoActivity extends Activity implements OnClickListener {

	private ImageView ivHead;

	private SharedPreferences sharedPreferences;

	private TextView tvLoginName;
	private TextView checkinTv;
	private TextView commentTv;
	private TextView shareTv;
	private TextView friendTv;
	private TextView bussTv;
	private TextView couponsTv;

	private View checkinListBtn;
	private View commentListBtn;
	private View headBtn;
	private View shareListBtn;
	private View friendListBtn;
	private View bussListBtn;
	private View couponsListBtn;
	private TextView menuBtn;
	private Button regBtn;
	private DBCon db;

	private IServer binder;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = Stub.asInterface(service);

			try {
				initRecord();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		Log.v("quicklife.LifeCycle ", "myinfo.onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		sharedPreferences = getSharedPreferences("state", Context.MODE_PRIVATE);
		db = new DBCon(this);
		initComponent();

		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		getApplicationContext().bindService(intent, sc, BIND_AUTO_CREATE);

	}

	private void initComponent() {
		menuBtn = (TextView) findViewById(R.id.main_myinfo_title_iv2);
		menuBtn.setOnClickListener(this);

		regBtn = (Button) findViewById(R.id.main_myinfo_title_reg);
		regBtn.setOnClickListener(this);
		ivHead = (ImageView) findViewById(R.id.myinfo_loginhead);
		// ͷ����ť
		headBtn = findViewById(R.id.myinfo_headList);
		headBtn.setOnClickListener(this);

		// �����б?ť
		friendListBtn = findViewById(R.id.myinfo_friend_list);
		friendListBtn.setOnClickListener(this);

		// �̻��ղذ�ť
		bussListBtn = findViewById(R.id.myinfo_buss_list);
		bussListBtn.setOnClickListener(this);

		// �Żݼ�¼��ť
		couponsListBtn = findViewById(R.id.myinfo_coupons_list);
		couponsListBtn.setOnClickListener(this);

		// ǩ����¼��ť
		checkinListBtn = findViewById(R.id.myinfo_checkin_list);
		checkinListBtn.setOnClickListener(this);

		// ���ۼ�¼��ť
		commentListBtn = findViewById(R.id.myinfo_comment_list);
		commentListBtn.setOnClickListener(this);

		// �����¼��ť
		shareListBtn = findViewById(R.id.myinfo_share_list);
		shareListBtn.setOnClickListener(this);

		tvLoginName = (TextView) findViewById(R.id.myinfo_nametv);

		checkinTv = (TextView) findViewById(R.id.myinfo_checkinNum);
		commentTv = (TextView) findViewById(R.id.myinfo_commentNum);
		shareTv = (TextView) findViewById(R.id.myinfo_tv_shareNum);
		couponsTv = (TextView) findViewById(R.id.myinfo_couponsNum);
		bussTv = (TextView) findViewById(R.id.myinfo_tv_bussNum);
		friendTv = (TextView) findViewById(R.id.myinfo_tv_friendNum);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.myinfo_buss_list:
			if (!sharedPreferences.getBoolean("loginState", false)) {
				displayToast("���ȵ�¼��鿴����ղأ�");
				return;
			}
			Intent i=new Intent();
			i.setClass(this, FavoriteBussActivity.class);
			startActivity(i);
			getParent()
			.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		case R.id.myinfo_headList:
			if (sharedPreferences.getBoolean("loginState", false)) {
				Intent intent = new Intent();
				intent.setClass(MyInfoActivity.this, DetailinfoActivity.class);
				startActivityForResult(intent, 110);
				getParent().overridePendingTransition(R.anim.zoomin,
						R.anim.zoomout);
			} else {
				Intent intent = new Intent(MyInfoActivity.this,
						LoginActivity.class);
				startActivityForResult(intent, 100);
				getParent().overridePendingTransition(R.anim.zoomin,
						R.anim.zoomout);
			}
			break;
		case R.id.main_myinfo_title_iv2:
			MainActivity main = (MainActivity) getParent();
			if (main.getMoveView().getNowState() == MyMoveView.MAIN)
				main.getMoveView().moveToRight(true);
			if (main.getMoveView().getNowState() == MyMoveView.RIGHT)
				main.getMoveView().moveToMain(true);
			break;
		case R.id.myinfo_friend_list:
			if (!sharedPreferences.getBoolean("loginState", false)) {
				displayToast("���ȵ�¼��鿴��ĺ��ѣ�");
				return;
			}
			Intent intent = new Intent();
			intent.setClass(MyInfoActivity.this, FriendsActivity.class);
			startActivity(intent);
			getParent()
					.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;

		case R.id.main_myinfo_title_reg:
			
			Intent intent1 = new Intent();
			intent1.setClass(this, RegisterActivity.class);
			startActivity(intent1);
			getParent()
					.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		default:
			break;
		}
	}

	/**
	 * @param string
	 */
	private void displayToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (resultCode) {
		case 100:

			if (data.getBooleanExtra("login", false))
				new Handler().post(new Runnable() {
					@Override
					public void run() {
						Cursor c = db.query(
								"select photo,name from user_info where id= "
										+ sharedPreferences.getLong(
												"currentUserId", -1), null);
						if (c.getCount() != 0) {
							c.moveToLast();
							String name = c.getString(c.getColumnIndex("name"));
							byte[] photo = c.getBlob(c.getColumnIndex("photo"));
							if (photo != null) {
								Bitmap head = BitmapFactory.decodeByteArray(
										photo, 0, photo.length);
								ivHead.setImageBitmap(head);
							}
							tvLoginName.setText(name);
							Editor edit = sharedPreferences.edit();
							edit.putBoolean("loginState", true);
							edit.commit();
						}
					}
				});
			break;
		case 110:
			if (!data.getBooleanExtra("detail", false))
				new Handler().post(new Runnable() {
					@Override
					public void run() {
						ivHead.setImageResource(R.drawable.icon_avatar_default);
						tvLoginName.setText(getResources().getText(
								R.string.myinfo_login));

					}
				});
			break;

		default:

			break;
		}
	}

	private void refData() {

		new Handler().post(new Runnable() {
			@Override
			public void run() {
				if (sharedPreferences.getBoolean("loginState", false)) {
					Cursor c = db.query(
							"select photo,name from user_info where id="
									+ sharedPreferences.getLong(
											"currentUserId", -1), null);
					c.moveToLast();
					String name = c.getString(c.getColumnIndex("name"));
					byte[] photo = c.getBlob(c.getColumnIndex("photo"));
					if (photo != null) {
						Bitmap head = BitmapFactory.decodeByteArray(photo, 0,
								photo.length);
						DisplayMetrics dm = getResources().getDisplayMetrics();
						int w = dm.widthPixels / 3;
						Bitmap h = Bitmap.createScaledBitmap(head, w, w, true);
						ivHead.setImageBitmap(h);
					}
					tvLoginName.setText(name);
					tvLoginName.setTextSize(22);
					Editor edit = sharedPreferences.edit();
					edit.putBoolean("loginState", true);
					edit.commit();
				}
			}
		});
	}

	private void initRecord() throws Exception {
		if (binder == null)
			return;
		if (!sharedPreferences.getBoolean("loginState", false)){
			checkinTv.setText("");
			commentTv.setText("");
			shareTv.setText("");
			couponsTv.setText("");
			bussTv.setText("");
			friendTv.setText("");
			return;
			}
		long id = sharedPreferences.getLong("currentUserId", -1);
		String checkinCount = binder.getCheckinCount(id) + "";
		String couponCount = binder.getCouponCount(id) + "";
		String shareCount = binder.getShareCount(id) + "";
		String friendCount = binder.getFriendCount(id) + "";
		String commentCount = binder.getCommentCount(id) + "";
		String bussCount = binder.getFavoriteCount(id) + "";

		checkinTv.setText(checkinCount);
		commentTv.setText(commentCount);
		shareTv.setText(shareCount);
		couponsTv.setText(couponCount);
		bussTv.setText(bussCount);
		friendTv.setText(friendCount);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "myinfo.onDestroy");
		db.close();
		super.onDestroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "myinfo.onPause");
		super.onPause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPostResume()
	 */
	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "myinfo.onPostResume");
		super.onPostResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "myinfo.onResume");
		refData();
		try {
			initRecord();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Log.v("quicklife.LifeCycle ", "myinfo.onStart");
		refData();
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
		Log.v("quicklife.LifeCycle ", "myinfo.onStop");

		super.onStop();
	}

}
