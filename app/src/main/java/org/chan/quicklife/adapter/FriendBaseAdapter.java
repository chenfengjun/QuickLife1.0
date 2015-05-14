/**
 * 
 */
package org.chan.quicklife.adapter;

import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.Friend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
public class FriendBaseAdapter extends BaseAdapter {
	private Context context;
	private List<Friend> friends;
	private DisplayMetrics dm;

	public FriendBaseAdapter(Context context, List<Friend> buss) {
		super();
		this.context = context;
		this.friends = buss;
		dm = context.getResources().getDisplayMetrics();
	}

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

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return friends.get(position).getShipId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Friend friend = friends.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_friends, null);
		}

		int w = dm.widthPixels / 4;// ��ȡ�ֱ��ʿ��
		TextView name = (TextView) convertView
				.findViewById(R.id.list_friend_name);
		TextView state = (TextView) convertView
				.findViewById(R.id.list_friend_state);
		ImageView head = (ImageView) convertView
				.findViewById(R.id.list_friend_image);
		ImageView sex = (ImageView) convertView
				.findViewById(R.id.list_friend_sex);
		if (friend.getPhoto() != null) {
			Bitmap bit = BitmapFactory.decodeByteArray(friend.getPhoto(), 0,
					friend.getPhoto().length);
			Bitmap img = Bitmap.createScaledBitmap(bit, w, w, true);
			head.setImageBitmap(img);
		} else {
			Bitmap b = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.icon_avatar_default);
			Bitmap bit = Bitmap.createScaledBitmap(b, w, w, true);
			head.setImageBitmap(bit);
		}
		name.setText(friend.getName());
		switch (friend.getState()) {
		case 1:
			state.setText("������");
			break;
		case 2:
			state.setText("˫����ͬ��");
			break;
		case 3:
			state.setText("δͨ������");
			break;
		default:
			break;
		}
		switch (friend.getSex()) {
		case 0:
			sex.setImageResource(R.drawable.icon_sex_female_normal);
			break;
		case 1:
			sex.setImageResource(R.drawable.icon_sex_male_normal);
		default:
			break;
		}

		return convertView;
	}

}
