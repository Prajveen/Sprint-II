package com.capgemini.pecunia.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.ChequeTransactions;
import com.capgemini.pecunia.service.TransactionService;

@RestController
@RequestMapping("/credit-using-cheque")
@CrossOrigin("http://localhost:4200")
public class TransactionController {

	@Autowired
	private TransactionService service;



	
	@PutMapping("/credit-amount")
	public ResponseEntity<String> creditUsingCheque(@RequestBody ChequeTransactions credit) {
		ResponseEntity< String> details = new ResponseEntity<String>(service.creditUsingCheque(credit),HttpStatus.OK);
		return details;
	}

	@PutMapping("/updateBalance")
	public ResponseEntity<String> updateBalance(@RequestBody Account balance){
		ResponseEntity< String> response = new ResponseEntity<String>(service.updateBalance(balance),HttpStatus.OK);
		return response;
	}

}



