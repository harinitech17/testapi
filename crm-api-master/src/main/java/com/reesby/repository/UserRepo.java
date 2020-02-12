package com.reesby.repository;

import java.util.List;

import com.reesby.model.User;

public interface UserRepo {
	
	void addUser(User user);
	User getUser(String userId,String tenantId);
	List<User> getAllUsers(String tenantId);
	void updateUser(User user);
	void deleteUser(String id);

}
