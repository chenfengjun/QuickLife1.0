/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.chan.quicklife.adapter.CheckinBaseAdapter;
import org.chan.quicklife.customview.MyMoveView;
import org.chan.quicklife.entity.Business;
import org.chan.quicklife.R;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;
import org.chan.quicklife.util.MyApplication;
import org.chan.quicklife.util.GeoUtils;
import org.chan.quicklife.util.GeoUtils.GaussSphere;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKLocationManager;

/**
 * @Package org.chan.quicklife.activity.ActionActivity.java
 * 
 * @ClassName ActionActivity
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class CheckinActivity extends Activity implements LocationListener,
		OnItemClickListener, OnClickListener {
//	�����ϴζ�λ�ľ�γ��
	private double lat = 0;
	private double lon = 0;
	private GeoUtils g;
	private List<Business> buss = new ArrayList<Business>();
	private ListView lv;
	private EditText searchEt;
	private TextView menuBtn;
	private BMapManager mBMapMan;
	private MKLocationManager mLocationManager;
	private CheckinBaseAdapter adapter;
	private IServer bussBinder;
	private SharedPreferences share;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			bussBinder = Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "checkin.onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);
		initComponet();

		startLoc();

	}

	public void stopLoc() {
		mBMapMan.getLocationManager().removeUpdates(this);
	}

	public void startLoc() {
		new Handler().post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MyApplication app = (MyApplication) CheckinActivity.this
						.getApplication();
				if (app.mBMapManager == null) {
					app.mBMapManager = new BMapManager(CheckinActivity.this);
					app.mBMapManager.init(MyApplication.strKey,
							new MyApplication.MyGeneralListener());
				}
				mLocationManager = app.mBMapManager.getLocationManager();
				// ����Ҫ
				mLocationManager
						.enableProvider(MKLocationManager.MK_GPS_PROVIDER);
				// ע�����
				mLocationManager.requestLocationUpdates(CheckinActivity.this);
				Intent intent = new Intent();
				intent.setAction("org.chan.quicklife.backstageService");
				getApplicationContext().bindService(intent, sc,
						BIND_AUTO_CREATE);
			}
		});

	}

	private void initComponet() {
		share = getSharedPreferences("state", MODE_PRIVATE);
		g = new GeoUtils();

		adapter = new CheckinBaseAdapter(this, buss);
		lv = (ListView) findViewById(R.id.checkin_listView1);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);

		searchEt = (EditText) findViewById(R.id.main_checkin_title_et);
		searchEt.setOnClickListener(this);

		menuBtn = (TextView) findViewById(R.id.main_checkin_title_iv2);
		menuBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_checkin_title_iv2:
			MainActivity main = (MainActivity) getParent();
			if (main.getMoveView().getNowState() == MyMoveView.MAIN)
				main.getMoveView().moveToRight(true);
			if (main.getMoveView().getNowState() == MyMoveView.RIGHT)
				main.getMoveView().moveToMain(true);
			break;
		case R.id.main_checkin_title_et:
			Intent intent = new Intent();
			intent.setClass(this, BussSearchActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long id) {
		if (share.getBoolean("loginState", false)) {
			Intent intent = new Intent();
			intent.putExtra("business", buss.get(arg2));
			intent.setClass(CheckinActivity.this, CheckinDetailActivity.class);
			startActivity(intent);
			getParent()
					.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		} else {
			Intent intent = new Intent(CheckinActivity.this,
					LoginActivity.class);
			startActivity(intent);
			getParent()
					.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
	}

	@Override
	public void onLocationChanged(Location loc) {
		if (loc != null) {
			double distance = g.DistanceOfTwoPoints(loc.getLongitude(),
					loc.getLatitude(), lon, lat, GaussSphere.WGS84);
			if (lat == 0 || distance > 30) {
				buss.clear();
				try {
					List<Business> bs = bussBinder.getBusinessesByLoc(
							loc.getLatitude(), loc.getLongitude());
					Collections.sort(bs, new Comparator<Business>() {

						@Override
						public int compare(Business lhs, Business rhs) {
							double dis1 = lhs.getDistance();
							double dis2 = rhs.getDistance();
							if (dis1 > dis2) {
								return 1;
							} else if (dis1 < dis2) {
								return -1;
							}
							return 0;
						}

					});
					for (Business business : bs) {
						buss.add(business);
					}
					adapter.notifyDataSetChanged();
					lat = loc.getLatitude();
					lon = loc.getLongitude();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	protected void onDestroy() {
		MyApplication app = (MyApplication) this.getApplication();

		if (app.mBMapManager != null) {
			app.mBMapManager.destroy();
			app.mBMapManager = null;
		}
		Log.v("quicklife.LifeCycle ", "checkin.onDestroy");

		super.onDestroy();
	}

	@Override
	protected void onPause() {
		MyApplication app = (MyApplication) this.getApplication();

		if (app.mBMapManager != null) {
			app.mBMapManager.stop();
			app.mBMapManager = null;
		}
		Log.v("quicklife.LifeCycle ", "checkin.onPause");
		super.onPause();
	}

	@Override
	protected void onResume() {
		Log.v("quicklife.LifeCycle ", "checkin.onResume");
		MyApplication app = (MyApplication) this.getApplication();
		if (app.mBMapManager != null) {
			 app.mBMapManager.start();
			app.mBMapManager = null;
		}
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPostResume()
	 */
	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "checkin.onPostResume");
		super.onPostResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.v("quicklife.LifeCycle ", "checkin.onStart");
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
		Log.v("quicklife.LifeCycle ", "checkin.onStop");

		super.onStop();
	}

}
