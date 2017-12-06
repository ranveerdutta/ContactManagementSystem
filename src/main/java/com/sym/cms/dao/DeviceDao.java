package com.sym.cms.dao;

import com.sym.cms.domain.Device;

/**
 * Interface representing all the methods for the Device entity Dao
 * @author ranveer
 *
 */
public interface DeviceDao {
	
	Device addDevice(String deviceToken);

	void updateSyncTime(String deviceToken);

	Device getDeviceDetails(String deviceToken);

}
