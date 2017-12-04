package com.symantec.cms.service;

import com.symantec.cms.domain.Contact;

public interface IContactManagement {

	Contact lookupContact(String firstName, String lastName);

	Contact addOrUpdateContact(Contact contact);

	void deleteContact(String firstName, String lastName);

}
