package org.chan.quicklife.activity;

import org.chan.quicklife.R;
import org.chan.quicklife.util.MyApplication;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKLocationManager;
import com.baidu.mapapi.MKOLUpdateElement;
import com.baidu.mapapi.MKOfflineMap;
import com.baidu.mapapi.MKOfflineMapListener;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.baidu.mapapi.Overlay;

public class MyMapActivity extends MapActivity implements LocationListener {
	private MapView mMapView = null;
	private MKOfflineMap mOffline = null;
	private double myLon;
	private double myLat;
	private double bussLon;
	private double bussLat;
	private GeoPoint bussPoint;
	private String bussName;
	private MapController mMapController;
	private MyLocationOverlay locationOverlay;
	boolean isRequest = false;// �Ƿ��ֶ���������λ
	boolean isFirstLoc = true;// �Ƿ��״ζ�λ
	private MKLocationManager mLocationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("quicklife.LifeCycle ", "map.onCreate");
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_map);

		bussLon = getIntent().getDoubleExtra("lon", 119);
		bussLat = getIntent().getDoubleExtra("lat", 26);
		bussName = getIntent().getStringExtra("name");
		bussPoint = new GeoPoint((int) (bussLat * 1E6), (int) (bussLon * 1E6));
		
		
		MyApplication app = (MyApplication) this.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(this);
			app.mBMapManager.init(MyApplication.strKey,
					new MyApplication.MyGeneralListener());
		}
		super.initMapActivity(app.mBMapManager);

		// ��λ���
		mLocationManager = app.mBMapManager.getLocationManager();
		// ����Ҫ
		mLocationManager.enableProvider(MKLocationManager.MK_GPS_PROVIDER);
		   mLocationManager.requestLocationUpdates(this);

		// �����������õ����ſؼ�
		mMapView = (MapView) findViewById(R.id.map_view);
		mMapView.setBuiltInZoomControls(true);
		initLocationMan();

		mOffline = new MKOfflineMap();
		mOffline.init(app.mBMapManager, new MKOfflineMapListener() {
			public void onGetOfflineMapState(int type, int state) {
				switch (type) {
				case MKOfflineMap.TYPE_DOWNLOAD_UPDATE: {
					MKOLUpdateElement update = mOffline.getUpdateInfo(state);
				}
					break;
				case MKOfflineMap.TYPE_NEW_OFFLINE:
					Log.d("OfflineDemo",
							String.format("add offlinemap num:%d", state));
					break;
				case MKOfflineMap.TYPE_VER_UPDATE:
					Log.d("OfflineDemo", String.format("new offlinemap ver"));
					break;
				}
			}
		});
		int num = mOffline.scan();
		Toast.makeText(this, "�������ߵ�ͼ��" + num, Toast.LENGTH_SHORT).show();
	}

	private void initLocationMan() {
		locationOverlay = new MyLocationOverlay(this, mMapView);
		locationOverlay.enableMyLocation();
		// �õ�mMapView�Ŀ���Ȩ,����������ƺ���ƽ�ƺ�����
		mMapController = mMapView.getController();
		mMapController.setZoom(14); // ���õ�ͼzoom����
		mMapView.getOverlays().add(new MyOverlay());
		mMapView.getOverlays().add(locationOverlay);
		mMapController.animateTo(bussPoint);
		mMapController.zoomIn();
		mMapController.setCenter(bussPoint);
		
		Button locBtn=(Button) findViewById(R.id.map_location);
		locBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				requestLocClick();
			}
		});
		
		ImageView backBtn=(ImageView) findViewById(R.id.map_back);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	 /**
     * �ֶ�����һ�ζ�λ����
     */
    private void requestLocClick(){
    	isRequest = true;
     
        Toast.makeText(MyMapActivity.this, "���ڶ�λ����", Toast.LENGTH_SHORT).show();
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected boolean isLocationDisplayed() {
		// TODO Auto-generated method stub
		return super.isLocationDisplayed();
	}

	@Override
	protected void onDestroy() {
		MyApplication app = (MyApplication) this.getApplication();
		if (app.mBMapManager != null) {
			app.mBMapManager.stop();
			mLocationManager.removeUpdates(this);
		}
		
		Log.v("quicklife.LifeCycle ", "checkin.onDestroy");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		MyApplication app = (MyApplication) this.getApplication();
		if (app.mBMapManager != null) {
			app.mBMapManager.stop();
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
		}
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.baidu.mapapi.LocationListener#onLocationChanged(android.location.
	 * Location)
	 */
	@Override
	public void onLocationChanged(Location location) {
		if (location == null)
			return;
		myLat = location.getLatitude();
		myLon = location.getLongitude();
		GeoPoint point = new GeoPoint((int) (myLat * 1e6), (int) (myLon * 1e6));
		if (isRequest) {
			// �ƶ���ͼ����λ��
			Log.d("LocationOverlay", "receive location, animate to it");
			mMapController.animateTo(point);
			mMapController.zoomIn();
			mMapController.setCenter(point); // ���õ�ͼ���ĵ�
			isRequest = false;
		}
	
	}

	public class MyOverlay extends Overlay {

		Paint paint = new Paint();
		Paint paint1 = new Paint();

		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {
			Point point = mapView.getProjection().toPixels(bussPoint, null);
			canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
					R.drawable.icon_gcoding), point.x, point.y, paint1);
			paint.setTextSize(30);
			paint.setARGB(255, 255, 0, 0);
			Typeface font = Typeface.create("΢���ź�", Typeface.BOLD);
			paint.setTypeface(font);
			canvas.drawText(bussName, point.x, point.y, paint);
		}
	}

}