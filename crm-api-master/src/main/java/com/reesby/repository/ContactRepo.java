package com.reesby.repository;

import java.util.List;

import com.reesby.model.Contact;

public interface ContactRepo  {
	
	void addContact(Contact contact);
	Contact getContact(String contactId,String tenantId);
	List<Contact> getAllContacts(String tenantId);
	void updateContact(Contact contact);
	void deleteContact(String contactId);
}