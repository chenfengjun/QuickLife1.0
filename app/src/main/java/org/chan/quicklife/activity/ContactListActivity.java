package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.R;
import org.chan.quicklife.adapter.MyContactsAdapter;
import org.chan.quicklife.entity.User;
import org.chan.quicklife.service.aidl.IServer;
import org.chan.quicklife.service.aidl.IServer.Stub;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @Package org.gread.quicklife.activity.ContactListActivity.java
 * 
 * @ClassName ContactListActivity
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class ContactListActivity extends Activity {

	private ListView listView;
	private MyContactsAdapter contactsAdapter;
	private View back;
	private List<User> phoneNumUsers = new ArrayList<User>();
	private IServer userBinder;
	private long currentId;
	private long friendId;
	private SharedPreferences shared;
	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			userBinder = Stub.asInterface(service);
			getContacts();
		}
	};

	private void getContacts() {
		if (userBinder == null) {
			return;
		}
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				List<String> phoneNum = getAllPhoneNum();
				List<User> users;
				phoneNumUsers.clear();
				try {
					users = userBinder.getNotFriendUser(currentId);
					for (String num : phoneNum) {
						char[] chNum = num.toCharArray();
						StringBuffer n = new StringBuffer();
						for (char c : chNum) {
							if ((c + "").matches("^[0-9]$")) {
								n.append(c);
							}
						}
						String phone = n.toString();
						for (User user : users) {
							if (user.getId() == currentId) {
								continue;
							}
							if ((user.getBindPhone().trim()).equals(phone
									.trim())) {
								phoneNumUsers.add(user);
							}
						}
					}
					contactsAdapter.notifyDataSetChanged();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 10);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_contacts);
		back=findViewById(R.id.contact_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		listView = (ListView) findViewById(R.id.contact_listView1);
		contactsAdapter = new MyContactsAdapter(this, phoneNumUsers);
		listView.setAdapter(contactsAdapter);
		shared = getSharedPreferences("state", MODE_PRIVATE);
		currentId = shared.getLong("currentUserId", -1);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				friendId = id;
				new AlertDialog.Builder(ContactListActivity.this)
						.setMessage("ȷ�ϼ�Ϊ���ѣ�")
						.setNegativeButton("ȡ��", null)
						.setPositiveButton("���",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										try {
											if (userBinder.addFriend(currentId,
													friendId, 1)) {
												displayToast("�ɹ��������룡");
											}
										} catch (RemoteException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}).show();

			}
		});

		Intent intent = new Intent();
		intent.setAction("org.chan.quicklife.backstageService");
		getApplicationContext().bindService(intent, sc, BIND_AUTO_CREATE);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		getContacts();
		super.onResume();
	}

	/**
	 * ��ȡ��ϵ�˶�Ӧ�ĵ绰����
	 * 
	 * @param contactId
	 *            ��ϵID
	 * @return
	 */
	private List<String> getAllPhoneNum() {
		List<String> phoneNums = new ArrayList<String>();
		Cursor phoneNumCursor = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		while (phoneNumCursor.moveToNext()) {
			String num = phoneNumCursor
					.getString(phoneNumCursor
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			phoneNums.add(num);
		}
		phoneNumCursor.close();
		return phoneNums;
	}

	private void displayToast(String text) {
		// TODO Auto-generated method stub
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
}