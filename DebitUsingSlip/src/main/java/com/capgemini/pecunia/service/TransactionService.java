package com.capgemini.pecunia.service;

import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.SlipTransactions;
import com.capgemini.pecunia.exceptions.Account_NotFoundException;
import com.capgemini.pecunia.exceptions.Zero_balance_Exception;

public interface TransactionService {


	String debitUsingSlip(SlipTransactions debit) throws Zero_balance_Exception, Account_NotFoundException;
	

	String updateBalance(Account balance) throws Account_NotFoundException;


	Account getAccountbyID(String accountID);

}
