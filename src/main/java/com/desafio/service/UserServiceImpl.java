package com.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.exception.ResourceNotFoundException;

import com.desafio.model.User;
import com.desafio.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		user.getCars().forEach(c -> c.setUser(user));
		return userRepository.save(user);		
	}

	@Override
	public User updateUser(User user) {
		Optional<User> userDb = this.userRepository.findById(user.getId());
		
		if(userDb.isPresent()) {
			User userUpdate = userDb.get();
			userUpdate.setFirstName(user.getFirstName());
			userUpdate.setLastName(user.getLastName());
			userUpdate.setBirthday(user.getBirthday());
			userUpdate.setEmail(user.getEmail());
			userUpdate.setPhone(user.getPhone());
			userRepository.save(userUpdate);
			return userUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + user.getId());
		}		
	}

	@Override
	public List<User> getAllUser() {
		return this.userRepository.findAll();
	}

	@Override
	public User getUserById(long userId) {

		Optional<User> UserDb = this.userRepository.findById(userId);
		
		if(UserDb.isPresent()) {
			return UserDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + userId);
		}
	}

	@Override
	public void deleteUser(long userId) {

		Optional<User> userDb = this.userRepository.findById(userId);
		
		if(userDb.isPresent()) {
			this.userRepository.delete(userDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + userId);
		}
		
	}
	
}
