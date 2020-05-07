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
import com.capgemini.pecunia.exceptions.UserNotFoundException;
import com.capgemini.pecunia.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:4200")
public class LoginController {

	@Autowired
	private LoginService service;

	@GetMapping("/validate/{username}/{password}")
	public ResponseEntity<LoginDetails> validateEmail(@PathVariable("username") String username,@PathVariable("password") String password ) throws UserNotFoundException{
		LoginDetails details=service.validateEmail(username,password);
		if(details==null) {
			throw new UserNotFoundException("no user found");
		}
		else {
			ResponseEntity<LoginDetails> response= new ResponseEntity<LoginDetails>(details,HttpStatus.OK);		
			return response;		

		}
	}

}
