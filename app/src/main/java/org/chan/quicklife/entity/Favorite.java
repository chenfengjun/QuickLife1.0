/**
 * 
 */
package org.chan.quicklife.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Package org.great.quicklife.entity.ActionLog.java
 * 
 * @ClassName ActionLog
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class Favorite implements Parcelable {
	private long id;
	private long userID;
	private long bussId;

	/**
	 * 
	 */
	public Favorite() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel dest, int flag) {
		dest.writeLong(id);
		dest.writeLong(userID);
		dest.writeLong(bussId);
	}

	public static final Parcelable.Creator<Favorite> CREATOR = new Creator<Favorite>() {

		@Override
		public Favorite[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Favorite[size];
		}

		@Override
		public Favorite createFromParcel(Parcel source) {
			Favorite ac = new Favorite();
			ac.id = source.readLong();
			ac.userID = source.readLong();
			ac.bussId = source.readLong();
			return ac;
		}
	};

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userID
	 */
	public long getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

	/**
	 * @return the bussId
	 */
	public long getBussId() {
		return bussId;
	}

	/**
	 * @param bussId
	 *            the bussId to set
	 */
	public void setBussId(long bussId) {
		this.bussId = bussId;
	}

}
