package com.sym.cms.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.sym.cms.domain.Device;

/**
 * Implementation class for Device entity Dao
 * @author ranveer
 *
 */
@Component
public class DeviceDaoImpl extends BaseInMemoryDao<Device> implements DeviceDao{

	@Override
	public Device getDeviceDetails(String deviceToken) {
		Device device = new Device(deviceToken);
		return super.get(device);
	}
	
	@Override
	public Device addDevice(String deviceToken) {
		Device device = new Device(deviceToken);
		Device existingDevice = super.get(device);
		if(null == existingDevice) {
			device.setDeviceRegdTime(new Date());
			return super.insert(device);
		}
		
		return existingDevice;
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
		super.insert(device);
	}

}
