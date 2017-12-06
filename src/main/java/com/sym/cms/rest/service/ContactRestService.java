package com.sym.cms.rest.service;

import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sym.cms.domain.Contact;
import com.sym.cms.exception.ContactMgmtSystemException;
import com.sym.cms.exception.ErrorCodes;
import com.sym.cms.service.IContactManagement;


/**
 * Rest layer class having all the Rest API for Contact resource
 * @author ranveer
 *
 */
@RestController
@RequestMapping("/contact")
public class ContactRestService {
	
	@Autowired
	private IContactManagement contactService;
	
	/**
	 * Rest API to add a new Contact
	 * @param Contact
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED)
	public Contact addContact(@RequestBody Contact contact) {
		return contactService.addContact(contact);
	}
	
	/**
	 * Rest API to update contact
	 * 
	 * @param first name and last name of old contact. New contact details
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK)
	public Contact updateContact(@RequestBody Contact contact, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName, @PathParam("lastUpdatedDate") String lastUpdatedDate) {
		Contact oldContact = new Contact(firstName, lastName);
		oldContact.setLastUpdatedDate(new Date(Long.parseLong(lastUpdatedDate)));
		return contactService.updateContact(contact, oldContact);
	}
	
	/**
	 * Returns the details of contact from the passed first name and last name
	 * @param first name
	 * @param last name
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
	@ResponseStatus( HttpStatus.OK)
	public Contact lookupContact(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
		if(StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
			throw new ContactMgmtSystemException(ErrorCodes.INSUFFICIENT_PARAMS);
		}
		return contactService.lookupContact(firstName, lastName);
	}
	
	/**
	 * Rest API to update Contact
	 * @param shop
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK)
	public void updateContact(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
		contactService.deleteContact(firstName, lastName);
	}
	
	/**
	 * Rest API to Sync the device and returns all the updated Contacts
	 * @param deviceToken
	 * @return
	 */
	@RequestMapping(value="/device", method = RequestMethod.GET)
	@ResponseStatus( HttpStatus.OK)
	public List<Contact> syncContact(@PathParam("deviceToken") String deviceToken) {
		return contactService.syncDevice(deviceToken);
	}
}
