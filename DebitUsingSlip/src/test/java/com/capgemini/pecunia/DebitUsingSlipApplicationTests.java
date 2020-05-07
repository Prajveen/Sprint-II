package com.capgemini.pecunia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.pecunia.entity.SlipTransactions;
import com.capgemini.pecunia.service.TransactionService;

@SpringBootTest
class DebitUsingSlipApplicationTests {

	@Autowired
	private TransactionService service;


	@Test
	public void DebitUsingSlipWithSufficientBalance() 
	{
		SlipTransactions slip = new SlipTransactions();
		slip.setAmount(5000);
		slip.setAccountNo("851990559412");
		slip.setTransactionType("Debit");

		String result=service.debitUsingSlip(slip);
		Assertions.assertEquals("transaction succesfull",result);
	}

	@Test
	public void DebitUsingSlipWithInSufficientBalance() 
	{
		SlipTransactions slip = new SlipTransactions();
		slip.setAmount(5000000);
		slip.setAccountNo("851990559412");
		slip.setTransactionType("Debit");

		String result=service.debitUsingSlip(slip);
		Assertions.assertEquals("Insufficient balance",result);
	}

}
