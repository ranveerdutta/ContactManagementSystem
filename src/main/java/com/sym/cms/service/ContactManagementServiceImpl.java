package com.sym.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sym.cms.dao.ContactDao;
import com.sym.cms.dao.DeviceDao;
import com.sym.cms.domain.Contact;
import com.sym.cms.domain.Device;
import com.sym.cms.exception.ContactMgmtSystemException;
import com.sym.cms.exception.ErrorCodes;

/**
 * Service layer implementation class representing all the operation on Contact management service
 * @author ranveer
 *
 */
@Component
public class ContactManagementServiceImpl implements IContactManagement{
	
	@Autowired
	private ContactDao contactDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public Contact lookupContact(String firstName, String lastName) {
		Contact contact = new Contact(firstName, lastName);
		return contactDao.lookup(contact);
	}
	
	@Override
	public List<Contact> getAllContacts() {
		return contactDao.fetchAllContacts();
	}
	
	@Override
	public Contact addContact(Contact contact) {
		Contact existingContactEntity = contactDao.lookup(contact);
		if(existingContactEntity != null) {
			throw new ContactMgmtSystemException(ErrorCodes.CONTACT_ALREADY_EXIST);
		}
		return contactDao.addContact(contact);
	}
	
	@Override
	public void addAllContacts(List<Contact> contactList) {
		for(Contact contact : contactList) {
			this.addContact(contact);
		}
	}
	
	@Override
	public Contact updateContact(Contact newContact, Contact oldContact) {
		Contact oldContactEntity = contactDao.lookup(oldContact);
		if(null == oldContactEntity) {
			throw new ContactMgmtSystemException(ErrorCodes.CONTACT_DOES_NOT_EXIST);
		}
		
		if(null == oldContact.getLastUpdatedDate() 
				|| !oldContact.getLastUpdatedDate().equals(oldContactEntity.getLastUpdatedDate())) {
			throw new ContactMgmtSystemException(ErrorCodes.CONTACT_NOT_SYNCED);
		}
		return contactDao.updateContact(newContact, oldContactEntity);
	}
	
	@Override
	public void deleteContact(String firstName, String lastName) {
		Contact contact = new Contact(firstName, lastName);
		contactDao.removeContact(contact);
	}
	
	@Override
	public List<Contact> syncDevice(String deviceToken){
		
		Device device = deviceDao.getDeviceDetails(deviceToken);
		List<Contact> contactUpdatedSinceLastSync = null;
		if(null == device) {
			device = deviceDao.addDevice(deviceToken);
			contactUpdatedSinceLastSync = contactDao.fetchAllContacts();
		}
		
		contactUpdatedSinceLastSync = contactDao.fetchAllUpdatedContacts(device.getLastSyncTime());
		deviceDao.updateSyncTime(deviceToken);
		
		return contactUpdatedSinceLastSync;
	}

}
