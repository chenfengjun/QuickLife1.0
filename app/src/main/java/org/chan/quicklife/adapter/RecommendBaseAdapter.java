/**
 * 
 */
package org.chan.quicklife.adapter;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.Friend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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
public class RecommendBaseAdapter extends BaseAdapter {
	private Context context;
	private List<Friend> friends;
	private List<Friend> addFriends = new ArrayList<Friend>();

	/**
	 * @param context
	 * @param buss
	 */
	public RecommendBaseAdapter(Context context, List<Friend> buss) {
		super();
		this.context = context;
		this.friends = buss;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return friends.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return friends.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return friends.get(position).getShipId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Friend friend = friends.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_recommend, null);
		}
		TextView name = (TextView) convertView
				.findViewById(R.id.list_recommend_name);
		name.setText(friend.getName());
		CheckBox check = (CheckBox) convertView.findViewById(R.id.list_recommend_cb);
		// ����Ƴ���б�����ʾ�Ƿ�ѡ��
		if (addFriends.contains(friend)) {
			check.setChecked(true);
		} else {
			check.setChecked(false);
		}
		check.setTag(friend);
		// ��ѡ��ע�����
		check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CheckBox check = (CheckBox) v;
				Friend contact = (Friend) check.getTag();
				if (check.isChecked()) {
					addFriends.add(contact);
				} else {
					addFriends.remove(contact);
				}
			}
		});
		return convertView;
	}
	public List<Friend> getSelected() {
		return addFriends;
	}
}
