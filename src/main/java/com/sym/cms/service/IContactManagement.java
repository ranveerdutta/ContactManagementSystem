package com.sym.cms.service;

import java.util.List;

import com.sym.cms.domain.Contact;


/**
 * Service layer class representing all the operation on Contact management service
 * @author ranveer
 *
 */
public interface IContactManagement {

	/**
	 * Method for lookup of contact
	 * @param Contact having first name and last name
	 * @return Contact
	 */
	Contact lookupContact(String firstName, String lastName);

	/**
	 * Method for adding a contact
	 * @param Contact
	 * @return Contact
	 */
	Contact addContact(Contact contact);

	/**
	 * Method for deleting a contact
	 * @param first name and last name
	 * @return Contact
	 */
	void deleteContact(String firstName, String lastName);

	/**
	 * Method for updating a contact
	 * @param new Contact and old contact
	 * @return Contact
	 */
	Contact updateContact(Contact newContact, Contact oldContact);

	/**
	 * Method for syncing a device and returns all the updated contacts
	 * @param device token
	 * @return List<Contact>
	 */
	List<Contact> syncDevice(String deviceToken);

	/**
	 * Method for fetching all the contacts
	 * @param
	 * @return List<Contact>
	 */
	List<Contact> getAllContacts();

	/**
	 * Method for adding a list of contacts
	 * @param List<Contact>
	 * @return
	 */
	void addAllContacts(List<Contact> contactList);

}
