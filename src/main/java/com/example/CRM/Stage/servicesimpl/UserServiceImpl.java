package com.example.CRM.Stage.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRM.Stage.models.User;
import com.example.CRM.Stage.repositories.UserRepository;
import com.example.CRM.Stage.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		Optional<User> m = userRepo.findById(id);
		return m.isPresent() ? m.get() : null;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
	}

	@Override
	public User addOneUser(User m) {
		// TODO Auto-generated method stub
		return userRepo.save(m);
	}

	@Override
	public User updateOneUser(User m) {
		// TODO Auto-generated method stub
		return userRepo.save(m);
	}
}
