package com.capgemini.pecunia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.entity.LoginDetails;
import com.capgemini.pecunia.exceptions.User_NotFoundException;
import com.capgemini.pecunia.service.LoginServiceImpl;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:4200")
public class LoginController {

	@Autowired
	private LoginServiceImpl service;

	@GetMapping("/validate/{username}/{password}")
	public ResponseEntity<LoginDetails> validateEmail(@PathVariable("username") String username,@PathVariable("password") String password ) throws User_NotFoundException{
		LoginDetails det=service.validateEmail(username,password);
		ResponseEntity<LoginDetails> response= new ResponseEntity<LoginDetails>(det,HttpStatus.OK);		
		return response;		
	
		
	}
}
