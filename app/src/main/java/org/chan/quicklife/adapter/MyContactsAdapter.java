/**
 * 
 */
package org.chan.quicklife.adapter;

import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.entity.Contact;
import org.chan.quicklife.entity.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Package com.contact.adapter.MyContactsAdapter.java
 * 
 * @ClassName MyContactsAdapter
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class MyContactsAdapter extends BaseAdapter {
	private Context context;
	private List<User> contacts;

	/**
	 * @param context
	 * @param contacts
	 */
	public MyContactsAdapter(Context context, List<User> contacts) {
		super();
		this.context = context;
		this.contacts = contacts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contacts.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return contacts.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return contacts.get(position).getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		User contact = contacts.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.contact_list, null);
		}
		TextView tv = (TextView) convertView
				.findViewById(R.id.contactlist_textView1);
		tv.setText(contact.getName());
		
		return convertView;
	}

}
