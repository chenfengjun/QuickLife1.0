/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.adapter.CouponBaseAdapter;
import org.chan.quicklife.adapter.MyPagerAdapter;
import org.chan.quicklife.customview.MyMoveView;
import org.chan.quicklife.entity.Coupon;
import org.chan.quicklife.entity.Favorite;
import org.chan.quicklife.entity.Recommend;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class CouponsActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	/** ��ͼ�л�ҳ�� */
	private ViewPager viewPager;
	private List<View> viewList;
	private PagerActivity pager;

	/** ����ҳ�� */
	private View viewHot;
	/** susan˵ҳ�� */
	private View viewSusan;
	/** ����ҳ�� */
	private View viewRemind;
	private TextView menuBtn;

	/** ����listview */
	private ListView hotLV;
	/** susan˵listview */
	private ListView susanLV;
	/** ����listview */
	private ListView remindLV;

	private SharedPreferences share;
	/** ��ǰ��¼���ʺ� */
	private long currentId;

	// ���listview��������
	private CouponBaseAdapter hotAdapter;
	private CouponBaseAdapter susanAdapter;
	private CouponBaseAdapter remindAdapter;

	private List<Coupon> hotCoupons = new ArrayList<Coupon>();
	private List<Coupon> susanCoupons = new ArrayList<Coupon>();
	private List<Coupon> remindCoupons = new ArrayList<Coupon>();
	private IServer binder;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = Stub.asInterface(service);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					try {
						List<Coupon> coupons = binder.getAllCoupons();
						hotCoupons.clear();
						for (Coupon coupon : coupons) {
							hotCoupons.add(coupon);
						}
						hotAdapter.notifyDataSetChanged();

					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}, 100);
			 if (share.getBoolean("loginState", false)) {
			refData();
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
		Log.v("quicklife.LifeCycle ", "coupons.onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conpound);
		share = getSharedPreferences("state", MODE_PRIVATE);
		currentId = share.getLong("currentUserId", -1);
		initPage();
		initView();
		initComponent();
		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		getApplicationContext().bindService(intent, sc, BIND_AUTO_CREATE);
	}

	private void initPage() {
		viewList = new ArrayList<View>();
		viewPager = (ViewPager) findViewById(R.id.coupons_vPager);
		pager = new PagerActivity(this, viewPager, viewList);
		viewHot = LayoutInflater.from(this).inflate(
				R.layout.layout_coupons_hot, null);
		viewSusan = LayoutInflater.from(this).inflate(
				R.layout.layout_coupons_hot, null);
		viewRemind = LayoutInflater.from(this).inflate(
				R.layout.layout_coupons_hot, null);
		pager.addTab(R.id.main_coupons_tv1, viewHot);
		pager.addTab(R.id.main_coupons_tv2, viewSusan);
		pager.addTab(R.id.main_coupons_tv3, viewRemind);

		viewPager.setAdapter(new MyPagerAdapter(viewList));
		pager.initImageView(R.id.coupons_cursor);
		pager.setPageChangeListener();
		pager.switchTab(0);
	}

	private void initView() {
		hotLV = (ListView) viewHot.findViewById(R.id.layout_coupon_lv);
		hotAdapter = new CouponBaseAdapter(this, hotCoupons);
		hotLV.setAdapter(hotAdapter);
		hotLV.setOnItemClickListener(this);

		susanLV = (ListView) viewSusan.findViewById(R.id.layout_coupon_lv);
		susanAdapter = new CouponBaseAdapter(this, susanCoupons);
		susanLV.setAdapter(susanAdapter);
		susanLV.setOnItemClickListener(this);

		remindLV = (ListView) viewRemind.findViewById(R.id.layout_coupon_lv);
		remindAdapter = new CouponBaseAdapter(this, remindCoupons);
		remindLV.setAdapter(remindAdapter);
		remindLV.setOnItemClickListener(this);

	}

	private void initComponent() {
		share = getSharedPreferences("state", MODE_PRIVATE);
		menuBtn = (TextView) findViewById(R.id.main_conpound_title_iv2);
		menuBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_conpound_title_iv2:
			MainActivity main = (MainActivity) getParent();
			if (main.getMoveView().getNowState() == MyMoveView.MAIN)
				main.getMoveView().moveToRight(true);
			if (main.getMoveView().getNowState() == MyMoveView.RIGHT)
				main.getMoveView().moveToMain(true);
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long id) {
		if (share.getBoolean("loginState", false)) {
			Intent intent = new Intent();
			intent.setClass(CouponsActivity.this, CouponDetailActivity.class);
			intent.putExtra("couponId", id);
			startActivity(intent);
			getParent()
					.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		} else {
			Intent intent = new Intent(CouponsActivity.this,
					LoginActivity.class);
			startActivity(intent);
			getParent()
					.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
	}

	private void refData() {
		if (binder == null) {
			return;
		}
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				try {
					List<Recommend> recs = binder.getRecommend(currentId);
					susanCoupons.clear();
					for (Recommend recommend : recs) {
						Coupon coupon = binder.getCouponById(recommend
								.getCouponId());
						susanCoupons.add(coupon);
					}
					susanAdapter.notifyDataSetChanged();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 100);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				try {
					List<Favorite> favs = binder.getFavorites(currentId);
					remindCoupons.clear();
					for (Favorite favorite : favs) {
						List<Coupon> cou = binder.getCouponsByBuss(favorite
								.getBussId());
						for (Coupon coupon : cou) {
							remindCoupons.add(coupon);
						}
					}
					remindAdapter.notifyDataSetChanged();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 100);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		Log.v("quicklife.LifeCycle ", "coupons.onDestroy");
		getApplicationContext().unbindService(sc);
		super.onDestroy();
	}


	@Override
	protected void onPause() {
		Log.v("quicklife.LifeCycle ", "coupons.onPause");
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
		Log.v("quicklife.LifeCycle ", "coupons.onPostResume");
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
		Log.v("quicklife.LifeCycle ", "coupons.onResume");
	
		TextView tv1 = (TextView) viewRemind
				.findViewById(R.id.layout_coupon_tv);
		TextView tv2 = (TextView) viewSusan.findViewById(R.id.layout_coupon_tv);
		if (share.getBoolean("loginState", false)) {
			currentId = share.getLong("currentUserId", -1);
			tv1.setVisibility(View.INVISIBLE);
			tv2.setVisibility(View.INVISIBLE);
			refData();
		} else {
			susanCoupons.clear();
			remindCoupons.clear();
			susanAdapter.notifyDataSetChanged();
			remindAdapter.notifyDataSetChanged();
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);

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
		Log.v("quicklife.LifeCycle ", "coupons.onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "coupons.onStop");
		super.onStop();
	}

}
