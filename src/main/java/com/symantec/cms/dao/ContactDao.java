package com.symantec.cms.dao;

import com.symantec.cms.domain.Contact;

public interface ContactDao {
	
	Contact lookup(Contact contact);

	void removeContact(Contact contact);

	Contact addOrUpdateContact(Contact contact);

}
