package com.sym.cms.service;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sym.cms.dao.ContactDao;
import com.sym.cms.dao.EntityUpdateHistoryDao;
import com.sym.cms.domain.Contact;
import com.sym.cms.exception.ContactMgmtSystemException;
import com.sym.cms.service.IContactManagement;


/**
 * Test representing all the test cases on Contact management service
 * @author ranveer
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestContactService {
	
	@Autowired
	private IContactManagement contactService;
	
	@Autowired
	private ContactDao contactDao;
	
	@Autowired
	private EntityUpdateHistoryDao historyDao;
	
	@Before
	public void setup() {
		contactDao.deleteAll();
	}

	@Test
	public void testAddContactValidData() {
		Contact contact = new Contact("firstName", "lastName", "email", "1234");
		contactService.addContact(contact);
		List<Contact> contactList = contactDao.fetchAllContacts();
		
		Assert.assertEquals(1, contactList.size());
		
		Contact addedContact = contactList.get(0);
		
		Assert.assertEquals("firstName", addedContact.getFirstName());
		Assert.assertEquals("lastName", addedContact.getLastName());
		Assert.assertEquals("email", addedContact.getEmail());
		Assert.assertEquals("1234", addedContact.getPhoneNumber());
		
		Assert.assertNotNull(addedContact.getCreatedDate());
		Assert.assertNotNull(addedContact.getLastUpdatedDate());
		Assert.assertNotNull(addedContact.getId());
	}
	
	@Test
	public void testAddMultipleContactValidData() {
		Contact contact1 = new Contact("firstName1", "lastName1", "email1", "1234");
		contactService.addContact(contact1);
		
		Contact contact2 = new Contact("firstName2", "lastName2", "email2", "1234");
		contactService.addContact(contact2);
		
		List<Contact> contactList = contactDao.fetchAllContacts();
		
		Assert.assertEquals(2, contactList.size());
		
	}
	
	@Test(expected=ContactMgmtSystemException.class)
	public void testDuplicateContactData() {
		Contact contact1 = new Contact("firstName1", "lastName1", "email1", "1234");
		contactService.addContact(contact1);
		
		Contact contact2 = new Contact("firstName1", "lastName1", "email2", "1234");
		contactService.addContact(contact2);
		
	}
	
	@Test
	public void testLookupContact() {
		Contact contact = new Contact("firstName", "lastName", "email", "1234");
		contactService.addContact(contact);
		Contact addedContact = contactService.lookupContact("firstName", "lastName");
		
		Assert.assertEquals("firstName", addedContact.getFirstName());
		Assert.assertEquals("lastName", addedContact.getLastName());
		Assert.assertEquals("email", addedContact.getEmail());
		Assert.assertEquals("1234", addedContact.getPhoneNumber());
		
		Assert.assertNotNull(addedContact.getCreatedDate());
		Assert.assertNotNull(addedContact.getLastUpdatedDate());
		Assert.assertNotNull(addedContact.getId());
	}
	
	@Test
	public void testLookupContactInvalidSearch() {
		Contact contact = new Contact("firstName", "lastName", "email", "1234");
		contactService.addContact(contact);
		Contact addedContact = contactService.lookupContact("firstName1", "lastName");
		
		Assert.assertNull(addedContact);
	}
	
	@Test
	public void testContactListValidData() {
		Contact contact1 = new Contact("firstName1", "lastName1", "email1", "1234");
		
		Contact contact2 = new Contact("firstName2", "lastName2", "email2", "1234");
		
		List<Contact> contactList = new LinkedList<>();
		contactList.add(contact1);
		contactList.add(contact2);
		contactService.addAllContacts(contactList);
		
		Assert.assertEquals(2, contactDao.fetchAllContacts().size());
		
	}
	
	@Test(expected=ContactMgmtSystemException.class)
	public void testContactListInvalidData() {
		Contact contact1 = new Contact("firstName1", "lastName1", "email1", "1234");
		
		Contact contact2 = new Contact("firstName1", "lastName1", "email2", "1234");
		
		List<Contact> contactList = new LinkedList<>();
		contactList.add(contact1);
		contactList.add(contact2);
		contactService.addAllContacts(contactList);
		
		
	}
	
	@Test
	public void testDeleteContactValidData() {
		Contact contact1 = new Contact("firstName1", "lastName1", "email1", "1234");
		contactService.addContact(contact1);
		
		Contact contact2 = new Contact("firstName2", "lastName2", "email2", "1234");
		contactService.addContact(contact2);
		
		List<Contact> contactList = contactDao.fetchAllContacts();
		
		Assert.assertEquals(2, contactList.size());
		
		
		contactService.deleteContact("firstName1", "lastName1");
		
		contactList = contactDao.fetchAllContacts();
		
		Assert.assertEquals(1, contactList.size());
		
		contactService.deleteContact("firstName2", "lastName2");
		
		contactList = contactDao.fetchAllContacts();
		
		Assert.assertEquals(0, contactList.size());
		
	}
	
	@Test
	public void testUpdateContactValidData() {
		Contact contact = new Contact("firstName", "lastName", "email", "1234");
		contactService.addContact(contact);
		List<Contact> contactList = contactDao.fetchAllContacts();
		
		Contact addedContact = contactList.get(0);
		
		Contact update = new Contact("firstName1", "lastName1", "email1", "12345");
		contactService.updateContact(update, addedContact);
		
		Contact updatedContact = contactDao.fetchAllContacts().get(0);
		
		Assert.assertEquals("firstName1", updatedContact.getFirstName());
		Assert.assertEquals("lastName1", updatedContact.getLastName());
		Assert.assertEquals("email1", updatedContact.getEmail());
		Assert.assertEquals("12345", updatedContact.getPhoneNumber());
		
		Assert.assertEquals(addedContact.getCreatedDate(), updatedContact.getCreatedDate());
		Assert.assertEquals(addedContact.getId(), updatedContact.getId());
		
		Assert.assertEquals(4, historyDao.getAllLog().size());
	}
	
	@Test(expected=ContactMgmtSystemException.class)
	public void testUpdateContactInvalidData() {
		Contact contact = new Contact("firstName", "lastName", "email", "1234");
		contactService.addContact(contact);
		
		Contact update = new Contact("firstName1", "lastName1", "email1", "12345");
		
		Contact invalidContact = new Contact("firstName2", "lastName2", "email", "1234");
		
		contactService.updateContact(update, invalidContact);
		
	}
	
	@Test
	public void testSyncContactValidData() {
		Contact contact1 = new Contact("firstName1", "lastName1", "email1", "1234");
		contactService.addContact(contact1);
		
		Contact contact2 = new Contact("firstName2", "lastName2", "email2", "1234");
		contactService.addContact(contact2);
		
		List<Contact> contactList = contactService.syncDevice("xyz");
		
		
		Assert.assertEquals(2, contactList.size());
		
	}
	

	@After
	public void tearDown() {
		contactDao.deleteAll();
	}
	
}
