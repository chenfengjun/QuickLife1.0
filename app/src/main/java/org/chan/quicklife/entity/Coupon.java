/**
 * 
 */
package org.chan.quicklife.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Package org.great.quicklife.entity.Coupon.java
 * 
 * @ClassName Coupon
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class Coupon implements Parcelable {
	private long id;
	private long bussId;
	private String name;
	private String type;
	private String buss;
	private String context;
	private String address;
	private byte[] image;
	private long area;
	private long pubTime;
	private long endTime;
	private float score;

	/**
	 * 
	 */
	public Coupon() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param type
	 */
	public Coupon(long id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
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
		dest.writeLong(bussId);
		dest.writeString(name);
		dest.writeString(type);
		dest.writeString(buss);
		dest.writeString(context);
		dest.writeString(address);
		dest.writeByteArray(image);
		dest.writeLong(area);
		dest.writeLong(pubTime);
		dest.writeLong(endTime);
		dest.writeFloat(score);
	}

	public static final Parcelable.Creator<Coupon> CREATOR = new Creator<Coupon>() {

		@Override
		public Coupon[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Coupon[size];
		}

		@Override
		public Coupon createFromParcel(Parcel source) {
			Coupon coupon = new Coupon();
			coupon.id = source.readLong();
			coupon.bussId = source.readLong();
			coupon.name = source.readString();
			coupon.type = source.readString();
			coupon.buss = source.readString();
			coupon.context = source.readString();
			coupon.address = source.readString();
			coupon.image = source.createByteArray();
			coupon.area = source.readLong();
			coupon.pubTime = source.readLong();
			coupon.endTime = source.readLong();
			coupon.score = source.readFloat();
			return coupon;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the buss
	 */
	public String getBuss() {
		return buss;
	}

	/**
	 * @param buss
	 *            the buss to set
	 */
	public void setBuss(String buss) {
		this.buss = buss;
	}

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @return the area
	 */
	public long getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(long area) {
		this.area = area;
	}

	/**
	 * @return the pubTime
	 */
	public long getPubTime() {
		return pubTime;
	}

	/**
	 * @param pubTime
	 *            the pubTime to set
	 */
	public void setPubTime(long pubTime) {
		this.pubTime = pubTime;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the score
	 */
	public float getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(float score) {
		this.score = score;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
