package com.reesby.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import com.reesby.model.Deal;
import com.reesby.model.User;

@Repository
public class DealRepoImpl implements DealRepo {

	@Autowired
	private DynamoDBMapper mapper;
	private DynamoDBScanExpression scan;

	@Override
	public void addDeal(Deal deal) {
		mapper.save(deal);

	}

	@Override
	public Deal getDeal(String dealId, String tenantId) {
		return mapper.load(Deal.class,dealId,tenantId);
	}

	@Override
	public List<Deal> getAllDeals(String tenantId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(tenantId));
		scan = new DynamoDBScanExpression();
		scan.withFilterExpression("tenant_id = :val1").withExpressionAttributeValues(eav);
		return mapper.scan(Deal.class, scan);
	}

	@Override
	public void updateDeal(Deal deal) {
		mapper.save(deal);

	}

	@Override
	public void deleteDeal(String id) {
		// TODO Auto-generated method stub

	}

}
