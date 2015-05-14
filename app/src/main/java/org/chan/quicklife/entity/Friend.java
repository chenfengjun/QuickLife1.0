package org.chan.quicklife.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Package com.example.entity.User.java
 * 
 * @ClassName User
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class Friend implements Parcelable {
	private long shipId;
	private long myId;
	private long friendId;
	private String loginName = "";
	private String name = "";
	private int sex;
	private byte[] photo;
	private double lat;
	private double lon;
	private int score;
	private String bindPhone = "";
	private int state;
	

	/**
	 * 
	 */
	public Friend() {
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
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(shipId);
		dest.writeLong(myId);
		dest.writeLong(friendId);
		dest.writeString(name);
		dest.writeString(loginName);
		dest.writeInt(sex);
		dest.writeByteArray(photo);
		dest.writeDouble(lat);
		dest.writeDouble(lon);
		dest.writeInt(score);
		dest.writeString(bindPhone);
		dest.writeInt(state);
	
	}

	public static final Parcelable.Creator<Friend> CREATOR = new Creator<Friend>() {

		@Override
		public Friend[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Friend[size];
		}

		@Override
		public Friend createFromParcel(Parcel source) {
			Friend user = new Friend();
			user.shipId = source.readLong();
			user.myId=source.readLong();
			user.friendId = source.readLong();
			user.name = source.readString();
			user.loginName = source.readString();
			user.sex = source.readInt();
			user.photo=source.createByteArray();
			user.lat = source.readDouble();
			user.lon = source.readDouble();
			user.score = source.readInt();
			user.bindPhone = source.readString();
			user.state=source.readInt();
			return user;
		}
	};

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getBindPhone() {
		return bindPhone;
	}

	public void setBindPhone(String bindPhone) {
		this.bindPhone = bindPhone;
	}


	/**
	 * @param _reply
	 */
	public void readFromParcel(Parcel _reply) {
		// TODO Auto-generated method stub

	}


	/**
	 * @return the shipId
	 */
	public long getShipId() {
		return shipId;
	}


	/**
	 * @param shipId the shipId to set
	 */
	public void setShipId(long shipId) {
		this.shipId = shipId;
	}


	/**
	 * @return the friendId
	 */
	public long getFriendId() {
		return friendId;
	}


	/**
	 * @param friendId the friendId to set
	 */
	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}


	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}


	/**
	 * @return the myId
	 */
	public long getMyId() {
		return myId;
	}


	/**
	 * @param myId the myId to set
	 */
	public void setMyId(long myId) {
		this.myId = myId;
	}

}
