/**
 * 
 */
package org.chan.quicklife.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @Package org.great.quicklife.provider.DBCon.java
 * 
 * @ClassName DBCon
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class DBCon {
	SQLiteDatabase db;
	DBHelper helper;

	/**
	 * @param db
	 */
	public DBCon(Context context) {
		super();
		helper = new DBHelper(context);
		this.db = helper.getReadableDatabase();
	}

	public Cursor query(String sql, String[] params) {
		return db.rawQuery(sql, params);
	}

	public boolean insert(String table, ContentValues values) {
		return db.insert(table, null, values) != -1;
	}

	public int delete(String table, String selection, String[] selectionArgs) {

		return db.delete(table, selection, selectionArgs);
	}

	public int update(String table, ContentValues values, String selection,
			String[] selectionArgs) {
		return db.update(table, values, selection, selectionArgs);
	}

	public void dropTable(String tableName) {
		db.execSQL("drop table if exists " + tableName);
	}

	public void close() {
		db.close();
	}
}
