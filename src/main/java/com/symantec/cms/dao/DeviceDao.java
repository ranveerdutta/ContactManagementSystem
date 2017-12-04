package com.symantec.cms.dao;

public interface DeviceDao {
	
	void addDevice(String deviceToken);

	void updateSyncTime(String deviceToken);

}
