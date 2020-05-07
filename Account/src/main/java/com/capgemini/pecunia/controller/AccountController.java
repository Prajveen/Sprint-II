package com.capgemini.pecunia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.service.AccountService;

@RestController
@RequestMapping("/balance")
@CrossOrigin("http://localhost:4200")
public class AccountController {

	@Autowired
	private AccountService service;


	@GetMapping("/getbalance/{accountID}")
	public ResponseEntity<Double> getbalance(@PathVariable String accountID) {
		Double balance=service.getbalance(accountID);
		ResponseEntity<Double> response= new ResponseEntity<Double>(balance,HttpStatus.OK);		
		return response;		
	}

	@GetMapping("/getAccountbyID/{accountID}")
	public Optional<Account> getAccountbyID(@PathVariable String accountID) throws AccountNotFoundException {
		return service.getAccountbyID(accountID);
	}

	@PutMapping("/updatebalance")
	public ResponseEntity<String> updatebalance(@RequestBody Account balance) throws AccountNotFoundException{
		service.getAccountbyID(balance.getAccountId());
		ResponseEntity< String> response = new ResponseEntity<String>(service.updatebalance(balance),HttpStatus.OK);
		return response;

	}

}
