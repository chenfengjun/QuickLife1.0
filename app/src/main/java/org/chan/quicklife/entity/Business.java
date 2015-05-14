/**
 * 
 */
package org.chan.quicklife.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Package org.great.quicklife.entity.Bussiness.java
 * 
 * @ClassName Business
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class Business implements Parcelable {
	private long id;
	private String type;
	private String name;
	private String address;
	private byte[] logo;
	private double distance;
	private double lon;
	private double lat;
	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}



	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	private int score;

	/**
	 * 
	 */
	public Business() {
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
		dest.writeLong(id);
		dest.writeString(type);
		dest.writeString(name);
		dest.writeString(address);
		dest.writeByteArray(logo);
		dest.writeDouble(distance);
		dest.writeInt(score);
		dest.writeDouble(lon);
		dest.writeDouble(lat);
	}

	public static final Parcelable.Creator<Business> CREATOR = new Creator<Business>() {

		@Override
		public Business[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Business[size];
		}

		@Override
		public Business createFromParcel(Parcel source) {
			Business buss = new Business();
			buss.id = source.readLong();
			buss.type = source.readString();
			buss.name = source.readString();
			buss.address = source.readString();
			buss.logo = source.createByteArray();
			buss.distance = source.readDouble();
			buss.score = source.readInt();
			buss.lon=source.readDouble();
			buss.lat=source.readDouble();
			return buss;
		}
	};

	/**
	 * @param id
	 */
	public Business(long id) {
		super();
		this.id = id;
	}

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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the logo
	 */
	public byte[] getLogo() {
		return logo;
	}

	/**
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}



	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}



	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}



	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}



	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

}
