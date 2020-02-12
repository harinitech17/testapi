package com.reesby.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.reesby.model.Contact;

@Repository
public class ContactRepoImpl implements ContactRepo {

	@Autowired
	private DynamoDBMapper mapper;
	private DynamoDBScanExpression scan;

	@Override
	public void addContact(Contact contact) {
		mapper.save(contact);

	}

	@Override
	public Contact getContact(String contactId, String tenantId) {
		return mapper.load(Contact.class, contactId, tenantId);
	}

	@Override
	public List<Contact> getAllContacts(String tenantId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(tenantId));
		scan = new DynamoDBScanExpression();
		scan.withFilterExpression("tenant_id = :val1").withExpressionAttributeValues(eav);
		return mapper.scan(Contact.class, scan);

	}

	@Override
	public void updateContact(Contact contact) {
		mapper.save(contact);

	}

	@Override
	public void deleteContact(String id) {
		// TODO Auto-generated method stub

	}
}