package com.reesby.service;

import java.util.List;

import com.reesby.model.Contact;

public interface ContactService {
	
	void addContact(Contact contact);
	Contact getContact(String contactId,String tenantId);
	List<Contact> getAllContacts(String tenantId);
	void updateContact(Contact contact);
	void deleteContact(String contactId);
}

