package com.symantec.cms.dao;

import java.util.Date;

import com.symantec.cms.domain.Device;

public class DeviceDaoImpl extends BaseInMemoryDao<Device> implements DeviceDao{

	@Override
	public void addDevice(String deviceToken) {
		Device device = new Device(deviceToken);
		Device existingDevice = super.get(device);
		if(null == existingDevice) {
			device.setDeviceRegdTime(new Date());
			super.insertOrReplace(device);
		}
	}
	
	@Override
	public void updateSyncTime(String deviceToken) {
		Device device = new Device(deviceToken);
		Device existingDevice = super.get(device);
		if(null == existingDevice) {
			device.setDeviceRegdTime(new Date());
		}else {
			device = existingDevice;
		}
		device.setLastSyncTime(new Date());
		super.insertOrReplace(device);
	}

}
