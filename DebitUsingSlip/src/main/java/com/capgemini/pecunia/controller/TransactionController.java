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
import com.capgemini.pecunia.entity.SlipTransactions;
import com.capgemini.pecunia.exceptions.Account_NotFoundException;
import com.capgemini.pecunia.exceptions.Zero_balance_Exception;
import com.capgemini.pecunia.service.TransactionService;

@RestController
@RequestMapping("/debit-using-slip")
@CrossOrigin("http://localhost:4200")
public class TransactionController {

	@Autowired
	private TransactionService service;

	

	@PutMapping("/debit-amount")
	public ResponseEntity<String> debitUsingCheque(@RequestBody SlipTransactions Debit) throws Zero_balance_Exception, Account_NotFoundException {
			ResponseEntity< String> details = new ResponseEntity<String>(service.debitUsingSlip(Debit),HttpStatus.OK);
			return details;
		}
	
	
		@PutMapping("/updateBalance")
		public ResponseEntity<String> updateBalance(@RequestBody Account balance) throws Account_NotFoundException{
				ResponseEntity< String> response = new ResponseEntity<String>(service.updateBalance(balance),HttpStatus.OK);
				return response;
			}
		}




