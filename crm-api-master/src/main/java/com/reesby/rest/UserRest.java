package com.reesby.rest;

import java.util.List;

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
import com.reesby.model.User;
import com.reesby.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserRest {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/tenant/{tenantId}/user/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String tenantId,@PathVariable String userId)
			throws ResourceNotFoundException {
		User user=userService.getUser(userId,tenantId);
		if(user==null)
		{
		throw new ResourceNotFoundException("User not found for this id - " + userId);
		}
		return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/tenant/{tenantId}")
	public List<User> getAllUsers(@PathVariable String tenantId)
			throws ResourceNotFoundException {
		List<User> user=userService.getAllUsers(tenantId);
		
		return user;
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestBody User user) throws Exception
	{
		if((user.getUserId()==null||user.getUserId().equals(""))||
				(user.getTenantId()==null||user.getTenantId().equals(""))||
				(user.getEmail()==null||user.getEmail().equals(""))||
				(user.getfName()==null||user.getfName().equals(""))||
				(user.getlName()==null||user.getlName().equals(""))||
				(user.getWorkPhone()==null||user.getWorkPhone().equals("")))
		{
			throw new Exception ("Required fields are missing");
		}
		userService.addUser(user);
		return new ResponseEntity<User>(userService.getUser(user.getUserId(),user.getTenantId()), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/updateuser")
	public void updateUser(@RequestBody User user) throws ResourceNotFoundException ,Exception
	{
		if((user.getEmail()==null||user.getEmail().equals(""))||
				(user.getfName()==null||user.getfName().equals(""))||
				(user.getlName()==null||user.getlName().equals(""))||
				(user.getWorkPhone()==null||user.getWorkPhone().equals("")))
		{
			throw new Exception ("Required fields are missing");
		}
	
		if(userService.getUser(user.getUserId(),user.getTenantId())==null)
		{
		throw new ResourceNotFoundException("User not found for this id - " + user.getUserId());
		}
		userService.updateUser(user);
	}

}
