package com.symantec.cms.dao;

import com.symantec.cms.domain.Contact;

public class ContactDaoImpl  extends BaseInMemoryDao<Contact> implements ContactDao{

	@Override
	public Contact lookup(Contact contact) {
		return super.get(contact);
	}
	
	@Override
	public void removeContact(Contact contact) {
		super.remove(contact);
	}
	
	@Override
	public Contact addOrUpdateContact(Contact contact) {
		return super.insertOrReplace(contact);
	}

}
