package com.sym.cms.dao;

import java.util.Date;
import java.util.List;

import com.sym.cms.domain.Contact;

/**
 * Interface representing all the methods for the Contact entity Dao
 * @author ranveer
 *
 */
public interface ContactDao {
	
	Contact lookup(Contact contact);

	void removeContact(Contact contact);

	Contact addContact(Contact contact);

	Contact updateContact(Contact newContact, Contact oldContactEntity);

	List<Contact> fetchAllContacts();

	List<Contact> fetchAllUpdatedContacts(Date lastSyncDate);

	void deleteAll();

}
