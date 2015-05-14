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
public class ActionLog implements Parcelable {
	private long id;
	private int acType;
	private long userID;
	private long bussId;
	private long couponId;
	private String userName;
	private byte[] userPhoto;
	private String bussName;
	private String couponName;
	private byte[] couponImage;
	private String actionType;
	private long acTime;
	private String comment;
	private float score;

	/**
	 * 
	 */
	public ActionLog() {
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
		dest.writeInt(acType);
		dest.writeLong(userID);
		dest.writeLong(bussId);
		dest.writeLong(couponId);
		dest.writeString(userName);
		dest.writeString(bussName);
		dest.writeString(couponName);
		dest.writeString(actionType);
		dest.writeString(comment);
		dest.writeByteArray(userPhoto);
		dest.writeByteArray(couponImage);
		dest.writeLong(acTime);
		dest.writeFloat(score);
	}

	public static final Parcelable.Creator<ActionLog> CREATOR = new Creator<ActionLog>() {

		@Override
		public ActionLog[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ActionLog[size];
		}

		@Override
		public ActionLog createFromParcel(Parcel source) {
			ActionLog ac = new ActionLog();
			ac.id = source.readLong();
			ac.acType = source.readInt();
			ac.userID = source.readLong();
			ac.bussId = source.readLong();
			ac.couponId = source.readLong();
			ac.userName = source.readString();
			ac.bussName = source.readString();
			ac.couponName = source.readString();
			ac.actionType = source.readString();
			ac.comment = source.readString();
			ac.userPhoto = source.createByteArray();
			ac.couponImage = source.createByteArray();
			ac.acTime = source.readLong();
			ac.score=source.readFloat();
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
	 * @return the acType
	 */
	public int getAcType() {
		return acType;
	}

	/**
	 * @param acType
	 *            the acType to set
	 */
	public void setAcType(int acType) {
		this.acType = acType;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPhoto
	 */
	public byte[] getUserPhoto() {
		return userPhoto;
	}

	/**
	 * @param userPhoto
	 *            the userPhoto to set
	 */
	public void setUserPhoto(byte[] userPhoto) {
		this.userPhoto = userPhoto;
	}

	/**
	 * @return the bussName
	 */
	public String getBussName() {
		return bussName;
	}

	/**
	 * @param bussName
	 *            the bussName to set
	 */
	public void setBussName(String bussName) {
		this.bussName = bussName;
	}

	/**
	 * @return the couponName
	 */
	public String getCouponName() {
		return couponName;
	}

	/**
	 * @param couponName
	 *            the couponName to set
	 */
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	/**
	 * @return the couponImage
	 */
	public byte[] getCouponImage() {
		return couponImage;
	}

	/**
	 * @param couponImage
	 *            the couponImage to set
	 */
	public void setCouponImage(byte[] couponImage) {
		this.couponImage = couponImage;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType
	 *            the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the acTime
	 */
	public long getAcTime() {
		return acTime;
	}

	/**
	 * @param acTime
	 *            the acTime to set
	 */
	public void setAcTime(long acTime) {
		this.acTime = acTime;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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

	/**
	 * @return the couponId
	 */
	public long getCouponId() {
		return couponId;
	}

	/**
	 * @param couponId
	 *            the couponId to set
	 */
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	/**
	 * @return the score
	 */
	public float getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(float score) {
		this.score = score;
	}

}
