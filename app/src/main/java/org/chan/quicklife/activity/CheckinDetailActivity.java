/**
 * 
 */
package org.chan.quicklife.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.ActionLog;
import org.chan.quicklife.entity.Business;
import org.chan.quicklife.entity.Favorite;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Package org.gread.quicklife.activity.ActionActivity.java
 * 
 * @ClassName ActionActivity
 * 
 * @Description ǩ������
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class CheckinDetailActivity extends Activity implements OnClickListener {
	private TextView tvName;
	private TextView tvScore;
	private TextView tvDistance;
	private TextView tvType;
	private TextView tvAddr;
	private ImageView ivLogo;
	private ImageView loc;
	private ImageView favoritBtn;
	private Button btnCheckin;
	private ImageView backBtn;
	private IServer bussBinder;
	private PopupWindow pop;
	private Business business;
	private long currentId;
	private EditText etComment;
	private RatingBar ratingBar;
	private LinearLayout containView;

	private ProgressBar pb;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			bussBinder = Stub.asInterface(service);
			refComment();

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detail_checkin);

		business = getIntent().getParcelableExtra("business");
		currentId = getSharedPreferences("state", MODE_PRIVATE).getLong(
				"currentUserId", -1);
		initCompenent();

		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		getApplicationContext().bindService(intent, sc, BIND_AUTO_CREATE);

	}

	/**
	 * 
	 */
	protected void refComment() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				try {
					List<ActionLog> actions = bussBinder.getActionByBuss(
							business.getId(), 1);
					List<Favorite> favs = bussBinder.getFavorites(currentId);
					for (Favorite favorite : favs) {
						if (favorite.getBussId() == business.getId()) {
							favoritBtn.setSelected(true);
							favoritBtn.setEnabled(false);
						}
					}
					if (actions.size() > 0) {
						containView.removeAllViews();
						for (ActionLog actionLog : actions) {
							View v = LayoutInflater.from(
									CheckinDetailActivity.this).inflate(
									R.layout.layout_checkin_comment, null);
							TextView userNameTv = (TextView) v
									.findViewById(R.id.checkin_comment_userName);
							TextView timeTv = (TextView) v
									.findViewById(R.id.checkin_comment_time);
							TextView detailTv = (TextView) v
									.findViewById(R.id.checkin_comment_detail);
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							String time = sdf.format(new Date(actionLog
									.getAcTime()));
							userNameTv.setText(actionLog.getUserName());
							timeTv.setText(time);
							String comment = actionLog.getComment();
							if (comment != null)
								detailTv.setText(actionLog.getComment());

							containView.addView(v);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 10);
	}

	/**
	 * 
	 */
	private void initCompenent() {
		containView = (LinearLayout) findViewById(R.id.detail_checkin_lineLayout);
		tvName = (TextView) findViewById(R.id.detail_checkin_tv_name);
		tvName.setText(business.getName());

		favoritBtn = (ImageView) findViewById(R.id.detail_checkin_iv_favoirite);
		favoritBtn.setOnClickListener(this);

		tvType = (TextView) findViewById(R.id.detail_checkin_tv_type);
		tvType.setText(business.getType());

		tvAddr = (TextView) findViewById(R.id.detail_checkin_tv_addr);
		tvAddr.setText(business.getAddress());

		tvScore = (TextView) findViewById(R.id.detail_checkin_tv_score);
		tvScore.setText(business.getScore() + "��");

		pb = (ProgressBar) findViewById(R.id.detail_checkin_progressBar1);
		// TODO ����
		loc = (ImageView) findViewById(R.id.detail_location);
		loc.setOnClickListener(this);

		tvDistance = (TextView) findViewById(R.id.detail_checkin_tv_dist);
		if (business.getDistance() == -1) {
			tvDistance.setText("������Ϣ");
		} else
			tvDistance.setText(business.getDistance() + "m");

		ivLogo = (ImageView) findViewById(R.id.detail_checkin_iv_logo);
		if (business.getLogo() != null) {
			DisplayMetrics dm = getResources().getDisplayMetrics();
			int w = dm.widthPixels / 3;
			Bitmap bit = BitmapFactory.decodeByteArray(business.getLogo(), 0,
					business.getLogo().length);
			Bitmap img = Bitmap.createScaledBitmap(bit, w, w * 9 / 16, true);
			ivLogo.setImageBitmap(img);
		}
		btnCheckin = (Button) findViewById(R.id.detail_checkin_btn_checkin);
		btnCheckin.setOnClickListener(this);

		backBtn = (ImageView) findViewById(R.id.detail_checkin_title_back);
		backBtn.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.detail_checkin_btn_checkin:
			showCheckinWindow();
			break;
		case R.id.detail_checkin_iv_favoirite:
			Favorite fav = new Favorite();
			fav.setUserID(currentId);
			fav.setBussId(business.getId());
			try {
				if (bussBinder.addFavorite(fav)) {
					ImageView iv = (ImageView) findViewById(R.id.detail_checkin_favorite_anim);
					Animation anim = AnimationUtils.loadAnimation(this,
							R.anim.scale_translate_rotate);
					iv.startAnimation(anim);
					favoritBtn.setSelected(true);
				} else {
					displayToast("�ף����Ѿ��ղع��ˣ�");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case R.id.detail_checkin_title_back:
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		case R.id.detail_location:
			Intent intent = new Intent();
			intent.setClass(this, MyMapActivity.class);
			intent.putExtra("lon", business.getLon());
			intent.putExtra("lat", business.getLat());
			intent.putExtra("name", business.getName());
			pb.setVisibility(View.VISIBLE);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (pop != null && pop.isShowing()) {
				pop.dismiss();
			} else {
				finish();
				overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}
			return false;
		}
		return false;
	}

	/**
	 * ����ǩ������
	 */
	private void showCheckinWindow() {
		// ���봰�������ļ�
		View view = LayoutInflater.from(this).inflate(
				R.layout.layout_pop_checkin, null);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int h = dm.heightPixels * 3 / 5;
		// ����PopupWindow����
		pop = new PopupWindow(view, LayoutParams.WRAP_CONTENT, h, false);
		View v = findViewById(R.id.detail_checkin_title);
		pop.setOutsideTouchable(true);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);

		etComment = (EditText) view.findViewById(R.id.pop_checkin_et);
		ratingBar = (RatingBar) view.findViewById(R.id.pop_checkin_ratingBar1);
		// ˢ�°�ť
		Button btn1 = (Button) view.findViewById(R.id.pop_checkin_btn1);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				long bussId = business.getId();
				ActionLog action = new ActionLog();
				action.setUserID(currentId);
				action.setBussId(bussId);
				String comment = etComment.getText().toString();
				float score = ratingBar.getRating();
				if (!comment.isEmpty()) {
					action.setComment(comment);
				}
				action.setScore(score);
				try {
					if (bussBinder.checkin(action)) {
						pop.dismiss();
						displayToast("ǩ���ɹ�����5��");
						refComment();
						// TODO ���㲥
					} else {
						pop.dismiss();
						displayToast("�ף������ϴ�ǩ��������6СʱŶ��");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// ˢ�°�ť
		Button btn2 = (Button) view.findViewById(R.id.pop_checkin_btn2);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		pop.showAsDropDown(v);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		pb.setVisibility(View.INVISIBLE);

		super.onResume();
	}

	private void displayToast(String text) {
		// TODO Auto-generated method stub
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
}
