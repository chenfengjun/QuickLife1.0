/**
 * 
 */
package org.chan.quicklife.adapter;

import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.Coupon;

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
public class CouponBaseAdapter extends BaseAdapter {
	private Context context;
	private List<Coupon> coupons;
	private DisplayMetrics dm;

	/**
	 * @param context
	 * @param buss
	 */
	public CouponBaseAdapter(Context context, List<Coupon> buss) {
		super();
		this.context = context;
		this.coupons = buss;
		dm = context.getResources().getDisplayMetrics();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return coupons.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return coupons.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return coupons.get(position).getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Coupon coupon = coupons.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_coupons, null);
		}

		int w = dm.widthPixels / 3;// ��ȡ�ֱ��ʿ��
		TextView name = (TextView) convertView
				.findViewById(R.id.list_coupons_name);
		TextView type = (TextView) convertView
				.findViewById(R.id.list_coupons_type);
		TextView bussName = (TextView) convertView
				.findViewById(R.id.list_coupons_buss);
		ImageView image = (ImageView) convertView
				.findViewById(R.id.list_coupons_image);
		if (coupon.getImage() != null) {
			Bitmap bit = BitmapFactory.decodeByteArray(coupon.getImage(), 0,
					coupon.getImage().length);
			Bitmap img = Bitmap.createScaledBitmap(bit, w, (int) (w * 9 / 16),
					true);
			image.setImageBitmap(img);
		}else{
			Bitmap bit=BitmapFactory.decodeResource(context.getResources(), R.drawable.img_hotapp_default);
			Bitmap img=Bitmap.createScaledBitmap(bit, w, (int) (w * 9 / 16), true);
			image.setImageBitmap(img);
		}
		name.setText(coupon.getName());
		type.setText(coupon.getType());
		bussName.setText(coupon.getBuss());

		return convertView;
	}

}
