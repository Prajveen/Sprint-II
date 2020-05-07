package com.capgemini.pecunia;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.pecunia.entity.ChequeTransactions;
import com.capgemini.pecunia.service.TransactionService;



@SpringBootTest
class CreditUsingChequeApplicationTests {

	@Autowired
	private TransactionService service;

	
	@Test
	public void CreditUsingChequeWithSufficientBalance() 
	{
	    ChequeTransactions cheque = new ChequeTransactions();
       cheque.setAmount(5000);
       cheque.setBankName("Indian Bank");
       cheque.setChequeNo("456987");
       cheque.setIfsc("ASKA123456");
       cheque.setIssueDate(new Date(18-04-2020));
       cheque.setPayeeAccountNo("851990559411");
       cheque.setRecipientAccountNo("851990559412");
       cheque.setTransactionType("credit");
       
       String result=service.creditUsingCheque(cheque);
        Assertions.assertEquals("transaction succesfull ",result);
	}
	
	@Test
	public void CreditUsingChequeWithInSufficientBalance() 
	{
	    ChequeTransactions cheque = new ChequeTransactions();
       cheque.setAmount(5000000);
       cheque.setBankName("Indian Bank");
       cheque.setChequeNo("456987");
       cheque.setIfsc("ASKA123456");
       cheque.setIssueDate(new Date(18-04-2020));
       cheque.setPayeeAccountNo("851990559411");
       cheque.setRecipientAccountNo("851990559412");
       cheque.setTransactionType("credit");
       
       String result=service.creditUsingCheque(cheque);
       Assertions.assertEquals("Insufficient balance",result);
	}

}
