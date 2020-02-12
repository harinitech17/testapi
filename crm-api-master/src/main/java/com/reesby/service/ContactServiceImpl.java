package com.reesby.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reesby.model.Contact;
import com.reesby.repository.ContactRepo;


@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	ContactRepo contactRepo;
	
	@Override
	public void addContact(Contact contact) {
		contactRepo.addContact(contact);
		
	}

	@Override
	public Contact getContact(String contactId,String tenantId) {
		
		return contactRepo.getContact(contactId,tenantId);
	}

	@Override
	public List<Contact> getAllContacts(String tenantId) {
		
		return contactRepo.getAllContacts(tenantId);
	}

	@Override
	public void updateContact(Contact contact) {
		
		contactRepo.updateContact(contact);
		
	}

	@Override
	public void deleteContact(String contactId) {
		// TODO Auto-generated method stub
		
	}

}
