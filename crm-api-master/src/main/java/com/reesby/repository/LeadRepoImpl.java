package com.reesby.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.reesby.model.Lead;

@Repository
public class LeadRepoImpl implements LeadRepo  {
	
	@Autowired
	private DynamoDBMapper mapper;
	private DynamoDBScanExpression scan;
	
	
	@Override
	public void addLead(Lead lead) {
		mapper.save(lead);
		
	}

	@Override
	public Lead getLead(String leadId,String tenantId) {
		return mapper.load(Lead.class,leadId,tenantId);
	}

	@Override
	public List<Lead> getAllLeads(String tenantId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(tenantId));
		scan = new DynamoDBScanExpression();
		scan.withFilterExpression("tenant_id = :val1").withExpressionAttributeValues(eav);
		return mapper.scan(Lead.class, scan);
	}

	@Override
	public void updateLead(Lead lead) {
		
		mapper.save(lead);
		
	}

	@Override
	public void deleteLead(String leadId) {
		// TODO Auto-generated method stub
		
	}

}
