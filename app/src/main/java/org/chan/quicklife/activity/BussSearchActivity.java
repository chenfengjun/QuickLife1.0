/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.adapter.BusinessBaseAdapter;
import org.chan.quicklife.entity.Business;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * @Package org.chan.quicklife.activity.BussSearchActivity.java
 * 
 * @ClassName BussSearchActivity
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class BussSearchActivity extends Activity implements OnClickListener {
	private List<Business> buss = new ArrayList<Business>();
	private ImageView back;
	private ListView lv;
	private EditText searchEt;
	private TextView tvNone;
	private Button searchBtn;
	private BusinessBaseAdapter adapter;
	private IServer bussBinder;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			bussBinder = Stub.asInterface(service);
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
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search);
		bindService();
	}

	private void bindService() {
		new Handler().post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				initComponnet();
				Intent intent = new Intent();
				intent.setAction("org.chan.quicklife.backstageService");
				bindService(intent, sc, BIND_AUTO_CREATE);
			}
		});
	}

	private void initComponnet() {
		back = (ImageView) findViewById(R.id.checkin_search_back);
		back.setOnClickListener(this);
		tvNone = (TextView) findViewById(R.id.checkin_search_none);

		lv = (ListView) findViewById(R.id.checkin_search_lv);
		adapter = new BusinessBaseAdapter(this, buss);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				Business b = buss.get(arg2);
				b.setDistance(-1);
				intent.putExtra("business", b);
				intent.setClass(BussSearchActivity.this,
						CheckinDetailActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}
		});
		searchBtn = (Button) findViewById(R.id.checkin_search_btn);
		searchBtn.setOnClickListener(this);

		searchEt = (EditText) findViewById(R.id.checkin_search_et);
		searchEt.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				String keyname = v.getText().toString().trim();
				search(keyname);
				return true;
			}
		});
	}

	private void search(String keyname) {
		try {
			List<Business> busses = bussBinder.getBussByKeyname(keyname);
			buss.clear();
			if (busses.size() < 1) {
				tvNone.setVisibility(View.VISIBLE);
				adapter.notifyDataSetChanged();
				return;
			} else {
				tvNone.setVisibility(View.INVISIBLE);
			}
			for (Business business : busses) {
				buss.add(business);
			}
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.checkin_search_back:
			finish();
			break;
		case R.id.checkin_search_btn:
			String keyname = searchEt.getText().toString().trim();
			search(keyname);
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
		unbindService(sc);
		super.onDestroy();
	}

}
