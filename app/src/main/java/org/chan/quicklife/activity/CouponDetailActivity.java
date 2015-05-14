/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.ActionLog;
import org.chan.quicklife.entity.Coupon;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;
import org.chan.quicklife.util.TimeFormat;

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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Package org.gread.quicklife.activity.CouponDetailActivity.java
 * 
 * @ClassName CouponDetailActivity
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class CouponDetailActivity extends Activity implements OnClickListener {
	private ImageView ivCouponImage;
	private TextView tvCouponName;
	private TextView tvCouponDetail;
	private TextView tvBussName;
	private TextView tvBussAddr;
	private ImageView back;
	private Button useCouponBtn;
	private Button houhouBtn;
	private RatingBar rbScore;
	private LinearLayout containView;
	private Coupon currentCoupon;
	private PopupWindow pop;
	private EditText etComment;
	private RatingBar ratingBar;

	private long couponId;
	private IServer binder;
	private int screenW;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = Stub.asInterface(service);
			refInfo();
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
		setContentView(R.layout.activity_detail_coupons);
		couponId = getIntent().getLongExtra("couponId", -1);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		screenW = dm.widthPixels - 10;
		initComponnet();

		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		bindService(intent, sc, BIND_AUTO_CREATE);
	}

	private void initComponnet() {
		ivCouponImage = (ImageView) findViewById(R.id.detail_coupons_photo);
		tvCouponName = (TextView) findViewById(R.id.detail_coupons_name);
		tvCouponDetail = (TextView) findViewById(R.id.detail_coupons_detail);
		tvBussName = (TextView) findViewById(R.id.detail_coupons_bussName);
		tvBussAddr = (TextView) findViewById(R.id.detail_coupons_bussDetail);
		rbScore = (RatingBar) findViewById(R.id.detail_coupons_ratingBar1);
		containView = (LinearLayout) findViewById(R.id.detail_coupons_containLine);
		useCouponBtn = (Button) findViewById(R.id.detail_coupons_btn_use);
		useCouponBtn.setOnClickListener(this);
		back = (ImageView) findViewById(R.id.detail_coupons_title_back);
		back.setOnClickListener(this);
		houhouBtn = (Button) findViewById(R.id.detail_coupons_title_houhou_btn);
		houhouBtn.setOnClickListener(this);
	}

	/**
	 * �ӷ�������ȡ��Ϣ��ˢ�½���
	 */
	private void refInfo() {
		if (binder == null)
			return;
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					currentCoupon = binder.getCouponById(couponId);

					byte[] imgBit = currentCoupon.getImage();
					if (imgBit != null) {
						Bitmap bit = BitmapFactory.decodeByteArray(imgBit, 0,
								imgBit.length);
						Bitmap image = Bitmap.createScaledBitmap(bit, screenW,
								screenW * 9 / 16, true);
						ivCouponImage.setImageBitmap(image);
					}
					String name = currentCoupon.getName() + "";
					String detail = currentCoupon.getContext() + "";
					String bussName = currentCoupon.getBuss();
					String bussAddr = currentCoupon.getAddress();
					float score = currentCoupon.getScore();
					tvCouponName.setText(name);
					tvCouponDetail.setText(detail);
					tvBussName.setText(bussName);
					tvBussAddr.setText(bussAddr);
					rbScore.setRating(score);
					List<ActionLog> action = binder.getActionByBuss(
							currentCoupon.getBussId(), 2);
					if (action.size() > 0) {
						containView.removeAllViews();
						for (ActionLog actionLog : action) {
							View v = LayoutInflater.from(
									CouponDetailActivity.this).inflate(
									R.layout.layout_coupons_comment, null);
							TextView tvUser = (TextView) v
									.findViewById(R.id.coupon_comment_userName);
							TextView tvTime = (TextView) v
									.findViewById(R.id.coupon_comment_time);
							TextView tvDetail = (TextView) v
									.findViewById(R.id.coupon_comment_detail);
							TextView tvScore = (TextView) v
									.findViewById(R.id.coupon_comment_score);
							tvUser.setText(actionLog.getUserName());
							tvTime.setText(TimeFormat.formatTime(actionLog
									.getAcTime()));
							String comment = actionLog.getComment();
							if (comment != null && (!comment.isEmpty()))
								tvDetail.setText(comment);
							tvScore.setText(actionLog.getScore() + "��");
							containView.addView(v);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 10);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.detail_coupons_btn_use:
			showCheckinWindow();
			break;
		case R.id.detail_coupons_title_houhou_btn:
			Intent intent = new Intent();
			intent.putExtra("coupon", couponId);
			intent.setClass(CouponDetailActivity.this, RecommendActivity.class);
			startActivity(intent);
			break;
		case R.id.detail_coupons_title_back:
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		default:
			break;
		}
	}

	private void showCheckinWindow() {
		// ���봰�������ļ�
		View view = LayoutInflater.from(this).inflate(
				R.layout.layout_pop_checkin, null);
		// ����PopupWindow����
		pop = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);
		View v = findViewById(R.id.detail_coupons_title);
		pop.setOutsideTouchable(true);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		etComment = (EditText) view.findViewById(R.id.pop_checkin_et);
		ratingBar = (RatingBar) view.findViewById(R.id.pop_checkin_ratingBar1);
		// ˢ�°�ť
		Button btn1 = (Button) view.findViewById(R.id.pop_checkin_btn1);
		btn1.setText("ʹ���Ż�");
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				long userId = getSharedPreferences("state", MODE_PRIVATE)
						.getLong("currentUserId", -1);
				ActionLog action = new ActionLog();
				action.setUserID(userId);
				action.setBussId(currentCoupon.getBussId());
				action.setCouponId(couponId);
				String comment = etComment.getText().toString() + "";
				float score = ratingBar.getRating();
				action.setScore(score);
				if (!comment.isEmpty()) {
					action.setComment(comment);
				}
				System.out.println("��ȡ���֣�" + action.getScore());
				try {
					if (binder.consume(action)) {
						pop.dismiss();
						displayToast("ʹ���Żݳɹ�����10��");
						refInfo();
						showPopWindow();
					} else {
						pop.dismiss();
						displayToast("�ף����Ѿ��ù���");
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

	private void showPopWindow() {
		// ���봰�������ļ�
		View view = LayoutInflater.from(this).inflate(
				R.layout.layout_pop_houhou, null);
		int w = (getResources().getDisplayMetrics().widthPixels) * 2 / 3;
		// ����PopupWindow����
		pop = new PopupWindow(view, w, w * 9 / 16, false);
		pop.setOutsideTouchable(true);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		// ˢ�°�ť
		Button btn1 = (Button) view.findViewById(R.id.houhou_btn1);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
				Intent intent = new Intent();
				intent.putExtra("coupon", couponId);
				intent.setClass(CouponDetailActivity.this,
						RecommendActivity.class);
				startActivity(intent);
			}
		});

		// ˢ�°�ť
		Button btn2 = (Button) view.findViewById(R.id.houbou_btn2);

		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		pop.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
	}

	private void displayToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
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
