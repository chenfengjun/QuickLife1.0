/**
 * 
 */
package org.chan.quicklife.adapter;

import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.ActionLog;
import org.chan.quicklife.util.TimeFormat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class ActionBaseAdapter extends BaseAdapter {
	private Context context;
	private List<ActionLog> actions;

	/**
	 * @param context
	 * @param buss
	 */
	public ActionBaseAdapter(Context context, List<ActionLog> buss) {
		super();
		this.context = context;
		this.actions = buss;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return actions.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return actions.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return actions.get(position).getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ActionLog action = actions.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_action, null);
		}
		TextView name = (TextView) convertView
				.findViewById(R.id.list_action_user);
		TextView time = (TextView) convertView
				.findViewById(R.id.list_action_time);
		TextView buss = (TextView) convertView
				.findViewById(R.id.list_action_buss);
		TextView acType = (TextView) convertView
				.findViewById(R.id.list_action_actype);
		ImageView head = (ImageView) convertView
				.findViewById(R.id.list_action_head);
		TextView comment = (TextView) convertView
				.findViewById(R.id.list_action_comment);
		name.setText(action.getUserName());
		time.setText(TimeFormat.formatTime(action.getAcTime()));
		buss.setText(action.getBussName());
		acType.setText(action.getActionType());
		String com = action.getComment() + "";
		if (!com.isEmpty())
			comment.setText(action.getComment());
		byte[] photo = action.getUserPhoto();
		if (photo != null) {
			int w = head.getDrawable().getIntrinsicWidth();
			int h = head.getDrawable().getIntrinsicHeight();
			Bitmap bit = BitmapFactory.decodeByteArray(photo, 0, photo.length);
			Bitmap img = Bitmap.createScaledBitmap(bit, w, h, true);
			head.setImageBitmap(img);
		}else{
			head.setImageResource(R.drawable.icon_default_profile);
		}
		return convertView;
	}

}
