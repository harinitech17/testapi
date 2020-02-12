package com.reesby.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.reesby.model.User;

@Repository
public class UserRepoImpl implements UserRepo {

	@Autowired
	private DynamoDBMapper mapper;

	private DynamoDBScanExpression scan;
	
	@Override
	public void addUser(User user) {
		mapper.save(user);
	}

	@Override
	public User getUser(String userId,String tenantId) {
		return mapper.load(User.class,userId,tenantId);
	}

	@Override
	public List<User> getAllUsers(String tenantId) {
		 Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
	        eav.put(":val1", new AttributeValue().withS(tenantId));
	        scan= new DynamoDBScanExpression();
		scan.withFilterExpression("tenant_id = :val1").withExpressionAttributeValues(eav);
		return mapper.scan(User.class, scan);
	}

	@Override
	public void updateUser(User user) {
		
			mapper.save(user);	
		
	}

	
	
	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		
	}

}
