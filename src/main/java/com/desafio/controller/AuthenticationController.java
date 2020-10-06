package com.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.desafio.model.AuthenticationRequest;
import com.desafio.model.AuthenticationResponse;
import com.desafio.model.JwtUserDetails;
import com.desafio.service.JwtUserDetailsService;
import com.desafio.config.JwtUtil;

@RestController
@RequestMapping("/vi")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
    
    @PostMapping(value = "/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
	HttpHeaders responseHeaders = new HttpHeaders();
		
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
		);

		//if authentication was succesful else throw an exception
		final JwtUserDetails userDetails = (JwtUserDetails) userDetailsService
			.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse response = new AuthenticationResponse(jwt);

//		response.setId(userDetails.getId());
		response.setUsername(userDetails.getUsername());
		List<String> roles = new ArrayList<String>();
		userDetails.getAuthorities().forEach((a) -> roles.add(a.getAuthority()));
		response.setRoles(roles);

		return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
	
	}
    
	@GetMapping(value = "/api/hello")
	public ResponseEntity<?> greetHello() throws Exception {
	//public ResponseEntity<?> createAuthenticationToken() throws Exception {
	HttpHeaders responseHeaders = new HttpHeaders();
	return new ResponseEntity<>("Helloworld", responseHeaders, HttpStatus.OK);
	}


}