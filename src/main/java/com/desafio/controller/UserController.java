package com.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.model.User;
import com.desafio.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("api/users") 
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok().body(userService.getAllUser());
	}

	@PostMapping("api/users")
	public ResponseEntity<User> createUser(@RequestBody User user){
		return ResponseEntity.ok().body(this.userService.createUser(user));
	}
	
	@GetMapping("api/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id){
		return ResponseEntity.ok().body(userService.getUserById(id));
	}

	@DeleteMapping("api/user/{id}")
	public HttpStatus deleteUser(@PathVariable long id){
		this.userService.deleteUser(id);
		return HttpStatus.OK;
	}	
	
	@PutMapping("api/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user){
		user.setId(id);
		return ResponseEntity.ok().body(this.userService.updateUser(user));
	}


}
	
