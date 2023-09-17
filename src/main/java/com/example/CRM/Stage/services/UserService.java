package com.example.CRM.Stage.services;

import java.util.List;

import com.example.CRM.Stage.models.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public User getUserById(Long id);
	
	public void deleteUser(Long id);
	
	public User updateOneUser(User p);

	public User addOneUser(User p);
	
}