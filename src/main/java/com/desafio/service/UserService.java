package com.desafio.service;

import java.util.List;
import com.desafio.model.User;

public interface UserService {

	User createUser(User user);

	User updateUser(User user);

	List<User> getAllUser();

	User getUserById(long userId);

	void deleteUser(long userId);
}