package com.sym.cms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.sym.cms.domain.Contact;

/**
 * Implementation class for Contact entity Dao
 * @author ranveer
 *
 */
@Component
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
	public Contact addContact(Contact contact) {
		return super.insert(contact);
	}
	
	@Override
	public Contact updateContact(Contact newContact, Contact oldContactEntity) {
		return super.update(newContact, oldContactEntity);
	}
	
	@Override
	public List<Contact> fetchAllContacts(){
		return new ArrayList<>(super.getAll());
	}
	
	@Override
	public List<Contact> fetchAllUpdatedContacts(Date lastSyncDate){
		Set<Contact> contactSet = super.getAll();
		List<Contact> contactList = new LinkedList<>();
		for(Contact contact : contactSet) {
			if(null == lastSyncDate || lastSyncDate.before(contact.getLastUpdatedDate())) {
				contactList.add(contact);
			}
		}
		return contactList;
	}

	@Override
	public void deleteAll() {
		super.cleanAll();
		
	}

}
