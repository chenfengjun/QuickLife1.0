/**
 * 
 */
package org.chan.quicklife.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Package org.great.quicklife.provider.DBHelper.java
 * 
 * @ClassName DBHelper
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class DBHelper extends SQLiteOpenHelper {

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DBHelper(Context context) {
		super(context, "local.db3", null, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists user_info("
				+ "id integer(12) ,login_name text(40),name text(40), "
				+ "password text(10),sex text(1),photo blob,lat real, lon real,score integer(12),"
				+ " bind_phone text(13),last_login_time text,last_loc_time text) ");
		db.execSQL("create table if not exists my_action(id integer(12) primary key,"
				+ "user text,buss text,coupon text,"
				+ "ac_type integer,ac_time integer,comment text,"
				+ "price integer)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
