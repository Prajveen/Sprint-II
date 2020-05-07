package com.capgemini.pecunia;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.pecunia.entity.ChequeTransactions;
import com.capgemini.pecunia.service.TransactionService;

@SpringBootTest
class DebitUsingChequeApplicationTests {
	
	@Autowired
	private TransactionService service;


	@Test
	public void DebitUsingChequeWithSufficientBalance() 
	{
	    ChequeTransactions cheque = new ChequeTransactions();
       cheque.setAmount(5000);
       cheque.setBankName("Pecunia Bank");
       cheque.setChequeNo("456987");
       cheque.setIfsc("ASKA123456");
       cheque.setIssueDate(new Date(18-04-2020));
       cheque.setPayeeAccountNo("851990559411");
       cheque.setRecipientAccountNo("self");
       cheque.setTransactionType("debit");
       
       String result=service.debitUsingCheque(cheque);
        Assertions.assertEquals("transaction succesfull",result);
	}
	
	@Test
	public void DebitUsingChequeWithInSufficientBalance() 
	{
	    ChequeTransactions cheque = new ChequeTransactions();
       cheque.setAmount(5000000);
       cheque.setBankName("Pecunia Bank");
       cheque.setChequeNo("456987");
       cheque.setIfsc("ASKA123456");
       cheque.setIssueDate(new Date(18-04-2020));
       cheque.setPayeeAccountNo("851990559411");
       cheque.setRecipientAccountNo("self");
       cheque.setTransactionType("debit");
       
       String result=service.debitUsingCheque(cheque);
       Assertions.assertEquals("Insufficient balance",result);
	}


}
