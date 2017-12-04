package com.symantec.cms.domain;

import java.util.Date;

public class Device extends BaseEntity {
	
	private String deviceToken;
	
	private Date deviceRegdTime;
	
	private Date lastSyncTime;

	public Device(String deviceToken) {
		super();
		this.deviceToken = deviceToken;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public Date getDeviceRegdTime() {
		return deviceRegdTime;
	}

	public void setDeviceRegdTime(Date deviceRegdTime) {
		this.deviceRegdTime = deviceRegdTime;
	}

	public Date getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceToken == null) ? 0 : deviceToken.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (deviceToken == null) {
			if (other.deviceToken != null)
				return false;
		} else if (!deviceToken.equals(other.deviceToken))
			return false;
		return true;
	}
	
	
}
