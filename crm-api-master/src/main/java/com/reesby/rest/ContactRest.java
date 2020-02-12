package com.reesby.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reesby.exception.ResourceNotFoundException;
import com.reesby.model.Contact;
import com.reesby.service.ContactService;

@RestController
@RequestMapping("/api/v1")
public class ContactRest {

	@Autowired
	ContactService contactService;

	@GetMapping("/tenant/{tenantId}/contact/{contactId}")
	public ResponseEntity<Contact> getUser(@PathVariable String tenantId, @PathVariable String contactId)
			throws ResourceNotFoundException {
		Contact contact = contactService.getContact(contactId, tenantId);

		if (contact == null) {
			throw new ResourceNotFoundException("Contact not found for this id :: " + contactId);
		}

		return new ResponseEntity<Contact>(contact, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/addcontact")
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
		contactService.addContact(contact);
		return new ResponseEntity<Contact>(contactService.getContact(contact.getContactId(), contact.getTenantId()),
				new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/updatecontact")
	public void updateContact(@RequestBody Contact contact) throws ResourceNotFoundException, Exception {
		if ((contact.getContactId() == null || contact.getContactId().equals(""))
				|| (contact.getTenantId() == null || contact.getTenantId().equals(""))
				|| (contact.getcName() == null || contact.getcName().equals(""))
				|| (contact.getClientorcandidate() == null || contact.getClientorcandidate().equals(""))
				|| (contact.getcEmail() == null || contact.getcEmail().equals(""))
				|| (contact.getcPersonalph() == null || contact.getcPersonalph().equals(""))) {

			throw new Exception("Required fields are missing");
		}

		if (contactService.getContact(contact.getContactId(), contact.getTenantId()) == null) {
			throw new ResourceNotFoundException("Contact not found for this id - " + contact.getContactId());
		}
		contactService.updateContact(contact);
	}
}
