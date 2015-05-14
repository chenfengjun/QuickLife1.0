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
public class Recommend implements Parcelable {
	private long id;
	private long userId;
	private long toFriendId;
	private long couponId;
	private long upTime;
	private String comment;

	/**
	 * 
	 */
	public Recommend() {
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
		dest.writeLong(userId);
		dest.writeLong(toFriendId);
		dest.writeLong(couponId);
		dest.writeString(comment);
		dest.writeLong(upTime);
	}

	public static final Parcelable.Creator<Recommend> CREATOR = new Creator<Recommend>() {

		@Override
		public Recommend[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Recommend[size];
		}

		@Override
		public Recommend createFromParcel(Parcel source) {
			Recommend ac = new Recommend();
			ac.id = source.readLong();
			ac.userId = source.readLong();
			ac.toFriendId = source.readLong();
			ac.couponId = source.readLong();
			ac.comment = source.readString();
			ac.upTime = source.readLong();
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
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the toFriendId
	 */
	public long getToFriendId() {
		return toFriendId;
	}

	/**
	 * @param toFriendId the toFriendId to set
	 */
	public void setToFriendId(long toFriendId) {
		this.toFriendId = toFriendId;
	}

	/**
	 * @return the couponId
	 */
	public long getCouponId() {
		return couponId;
	}

	/**
	 * @param couponId the couponId to set
	 */
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	/**
	 * @return the upTime
	 */
	public long getUpTime() {
		return upTime;
	}

	/**
	 * @param upTime the upTime to set
	 */
	public void setUpTime(long upTime) {
		this.upTime = upTime;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
