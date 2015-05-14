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
public class User implements Parcelable {
	private long id;
	private String loginName = "";
	private String name = "";
	private String password = "";
	private int sex;
	private byte[] photo = new byte[1];
	private float lat;
	private float lon;
	private int score;
	private String bindPhone = "";
	private long lastLoginTime;
	private long lastLocTime;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param loginName
	 * @param name
	 * @param password
	 */
	public User(long id, String loginName, String name, String password) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.password = password;
	}

	/**
	 * @param id
	 * @param loginName
	 * @param name
	 * @param password
	 * @param sex
	 * @param photo
	 * @param lat
	 * @param lon
	 * @param score
	 * @param bindPhone
	 * @param lastLoginTime
	 * @param lastLocTime
	 */
	public User(long id, String loginName, String name, String password,
			int sex, byte[] photo, float lat, float lon, int score,
			String bindPhone, long lastLoginTime, long lastLocTime) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.photo = photo;
		this.lat = lat;
		this.lon = lon;
		this.score = score;
		this.bindPhone = bindPhone;
		this.lastLoginTime = lastLoginTime;
		this.lastLocTime = lastLocTime;
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
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(loginName);
		dest.writeString(password);
		dest.writeInt(sex);
		dest.writeByteArray(photo);
		dest.writeFloat(lat);
		dest.writeFloat(lon);
		dest.writeInt(score);
		dest.writeString(bindPhone);
		dest.writeLong(lastLoginTime);
		dest.writeLong(lastLocTime);
	}

	public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {

		@Override
		public User[] newArray(int size) {
			// TODO Auto-generated method stub
			return new User[size];
		}

		@Override
		public User createFromParcel(Parcel source) {
			User user = new User();
			user.id = source.readLong();
			user.name = source.readString();
			user.loginName = source.readString();
			user.password = source.readString();
			user.sex = source.readInt();
			user.photo = source.createByteArray();
//			 source.readByteArray(user.photo);
			user.lat = source.readFloat();
			user.lon = source.readFloat();
			user.score = source.readInt();
			user.bindPhone = source.readString();
			user.lastLoginTime = source.readLong();
			user.lastLocTime = source.readLong();
			return user;
		}
	};

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
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

	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public long getLastLocTime() {
		return lastLocTime;
	}

	public void setLastLocTime(long lastLocTime) {
		this.lastLocTime = lastLocTime;
	}

	/**
	 * @param _reply
	 */
	public void readFromParcel(Parcel _reply) {
		// TODO Auto-generated method stub

	}

}
