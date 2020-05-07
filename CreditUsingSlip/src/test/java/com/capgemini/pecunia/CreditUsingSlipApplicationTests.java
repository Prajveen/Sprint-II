package com.capgemini.pecunia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.pecunia.entity.SlipTransactions;
import com.capgemini.pecunia.service.TransactionService;

@SpringBootTest
class CreditUsingSlipApplicationTests {

	@Autowired
	private TransactionService service;

	
	@Test
	public void CreditUsingSlipWithSufficientBalance() 
	{
	    SlipTransactions slip = new SlipTransactions();
	    slip.setAmount(5000);
	    slip.setAccountNo("851990559412");
	    slip.setTransactionType("Credit");
	    
       String result=service.creditUsingSlip(slip);
        Assertions.assertEquals("transaction succesfull ",result);
	}
	
}
