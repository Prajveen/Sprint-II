package com.capgemini.pecunia.service;

import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.ChequeTransactions;
import com.capgemini.pecunia.exceptions.Zero_balance_Exception;

public interface TransactionService {


	String debitUsingCheque(ChequeTransactions debit) throws Zero_balance_Exception;
	

	String updateBalance(Account balance);


	Account getAccountbyID(String accountID);

}
