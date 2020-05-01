package com.capgemini.pecunia.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.ChequeTransactions;
import com.capgemini.pecunia.exceptions.Account_NotFoundException;
import com.capgemini.pecunia.exceptions.Zero_balance_Exception;
import com.capgemini.pecunia.service.TransactionService;

@RestController
@RequestMapping("/debit-using-cheque")
@CrossOrigin("http://localhost:4200")
public class TransactionController {

	@Autowired
	private TransactionService service;

	RestTemplate restTemplate = new RestTemplate();
	

	@PutMapping("/debit-amount")
	public ResponseEntity<String> debitUsingCheque(@RequestBody ChequeTransactions Debit) throws Zero_balance_Exception, Account_NotFoundException {
			ResponseEntity< String> details = new ResponseEntity<String>(service.debitUsingCheque(Debit),HttpStatus.OK);
			return details;
		
	}
	
		@PutMapping("/updateBalance")
		public ResponseEntity<String> updateBalance(@RequestBody Account balance) throws Account_NotFoundException{

			String getAccountUrl="http://localhost:1810/balance/getAccountbyID/"+balance.getAccountID();
			Account account= restTemplate.getForObject(getAccountUrl, Account.class);
			
			if(account==null)
			{
				throw new Account_NotFoundException("account with "+balance.getAccountID()+" doesn't exist....!");
			}
			else
			{
				ResponseEntity< String> response = new ResponseEntity<String>(service.updateBalance(balance),HttpStatus.OK);
				return response;
			}
		}
}



