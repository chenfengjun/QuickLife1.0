/**
 * 
 */
package org.chan.quicklife.entity;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Package com.contact.entity.Contact.java
 * 
 * @ClassName Contact
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class Contact implements Parcelable {
	
	private String name;
	private Long contactId;
	private List<String> phoneNumber;

	/**
	 * 
	 */
	public Contact() {
		super();
	}

	

	/**
	 * @param name
	 * @param contactId
	 * @param photo
	 * @param phoneNumber
	 * @param email
	 * @param logs
	 */
	public Contact(String name, Long contactId, Long photo,
			List<String> phoneNumber) {
		super();
		this.name = name;
		this.contactId = contactId;
		this.phoneNumber = phoneNumber;
		
	
	}



	/**
	 * @param name
	 * @param contactId
	 * @param photo
	 */
	public Contact(String name, Long contactId, Long photo) {
		super();
		this.name = name;
		this.contactId = contactId;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeLong(contactId);
		dest.writeList(phoneNumber);
	
	}

	public static final Parcelable.Creator<Contact> CREATOR = new Creator<Contact>() {

		@Override
		public Contact[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Contact[size];
		}

		@Override
		public Contact createFromParcel(Parcel source) {
			String name = source.readString();
			Long id=source.readLong();
			Long photo = source.readLong();
			@SuppressWarnings("unchecked")
			List<String> phoneNumber = source.readArrayList(ClassLoader
					.getSystemClassLoader());
			
			return new Contact(name, id, photo, phoneNumber);
		}
	};

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
	 * @return the phoneNumber
	 */
	public List<String> getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	/**
	 * @return the contactId
	 */
	public Long getContactId() {
		return contactId;
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

}
