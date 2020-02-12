package com.reesby.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reesby.model.User;
import com.reesby.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public void addUser(User user) {
		userRepo.addUser(user);
		
	}

	@Override
	public User getUser(String userId,String tenantId) {
		
		return userRepo.getUser(userId,tenantId);
	}

	@Override
	public List<User> getAllUsers(String tenantId) {

		return userRepo.getAllUsers(tenantId);
	}

	@Override
	public void updateUser(User user) {
		userRepo.updateUser(user);
		
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		
	}

}
