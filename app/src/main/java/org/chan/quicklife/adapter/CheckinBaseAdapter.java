/**
 * 
 */
package org.chan.quicklife.adapter;

import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.Business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @Package org.gread.quicklife.adapter.CheckinBaseAdapter.java
 * 
 * @ClassName CheckinBaseAdapter
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class CheckinBaseAdapter extends BaseAdapter {
	private Context context;
	private List<Business> buss;

	/**
	 * @param context
	 * @param buss
	 */
	public CheckinBaseAdapter(Context context, List<Business> buss) {
		super();
		this.context = context;
		this.buss = buss;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return buss.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return buss.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return buss.get(position).getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Business busi = buss.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_checkin, null);
		}
		TextView name = (TextView) convertView.findViewById(R.id.list_tv_name);
		TextView type = (TextView) convertView.findViewById(R.id.list_tv_type);
		TextView addr = (TextView) convertView
				.findViewById(R.id.list_tv_address);
		TextView distans = (TextView) convertView
				.findViewById(R.id.list_tv_distans);
		name.setText(busi.getName());
		type.setText(busi.getType());
		addr.setText(busi.getAddress());
		distans.setText(busi.getDistance() + "m");
		return convertView;
	}

}
