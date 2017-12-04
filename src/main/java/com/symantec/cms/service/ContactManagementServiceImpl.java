package com.symantec.cms.service;

import com.symantec.cms.dao.ContactDao;
import com.symantec.cms.dao.ContactDaoImpl;
import com.symantec.cms.domain.Contact;

public class ContactManagementServiceImpl implements IContactManagement{
	
	private ContactDao contactDao = new ContactDaoImpl();
	
	@Override
	public Contact lookupContact(String firstName, String lastName) {
		Contact contact = new Contact(firstName, lastName);
		return contactDao.lookup(contact);
	}
	
	@Override
	public Contact addOrUpdateContact(Contact contact) {
		return contactDao.addOrUpdateContact(contact);
	}
	
	@Override
	public void deleteContact(String firstName, String lastName) {
		Contact contact = new Contact(firstName, lastName);
		contactDao.removeContact(contact);
	}

}
