package com.capgemini.pecunia.service;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.dao.TransactionDao;
import com.capgemini.pecunia.dao.TransactionRepository;
import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.ChequeTransactions;
import com.capgemini.pecunia.exceptions.Zero_balance_Exception;
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository dao;
	@Autowired
	private TransactionDao repository;

	Date dateobj = new Date();
	Calendar calobj = Calendar.getInstance();


	@Override
	public String debitUsingCheque(ChequeTransactions debit) throws Zero_balance_Exception {

		
			Account account= getAccountbyID(debit.getPayeeAccountNo());
		
			debit.setTransactionDate((calobj.getTime()));
			debit.setChequeID(getRandomDoubleBetweenRange(200000,29999));
			dao.save(debit);
			
			double newbalance=account.getBalance()-debit.getAmount();
			Account updateAccount=new Account();
			updateAccount.setAccountID(debit.getPayeeAccountNo());
			updateAccount.setBalance(newbalance);
			updateBalance(updateAccount);	
			return "transaction succesfull ";	
		
	}


	@Override
	public String updateBalance(Account balance) {
		repository.save(balance);
		return "updated successfully";
	}


	@Override
	public Account getAccountbyID(String accountID) {
		return repository.getAccountbyID(accountID);
	}
	
	public static int getRandomDoubleBetweenRange(int min, int max){
		int x = (int) ((Math.random()*((max-min)+1))+min);
		return x;
	}
}
