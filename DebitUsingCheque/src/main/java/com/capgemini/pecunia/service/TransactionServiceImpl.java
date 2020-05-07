package com.capgemini.pecunia.service;


import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.dao.ChequeTransactionsDao;
import com.capgemini.pecunia.dao.TransactionsDao;
import com.capgemini.pecunia.dao.UpdateBalanceDao;
import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.ChequeTransactions;
import com.capgemini.pecunia.entity.Transactions;
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private ChequeTransactionsDao dao;
	@Autowired
	private UpdateBalanceDao repository;
	@Autowired
	private TransactionsDao transac;


	Transactions transaction=new Transactions();
	
	long millis=System.currentTimeMillis();  
	Date date=new Date(millis); 
	

	@Override
	public String debitUsingCheque(ChequeTransactions debit) {

		
			Account account= getAccountbyID(debit.getPayeeAccountNo());
			
			if(account.getAmount()>=debit.getAmount())
			{

				debit.setTransactionDate(date);
				debit.setChequeID(getRandomDoubleBetweenRange(200000,29999));
				dao.save(debit);
				

				transaction.setAccountId(debit.getPayeeAccountNo());
				transaction.setTransAmount(debit.getAmount());
				transaction.setTransDate(date);
				transaction.setTransFrom(debit.getPayeeAccountNo());
				transaction.setTransId(debit.getTransactionID());
				transaction.setTransTo(debit.getRecipientAccountNo());
				transaction.setTransType(debit.getTransactionType());
				transac.save(transaction);
				
				
				double newbalance=account.getAmount()-debit.getAmount();
				Account updateAccount=new Account();
				updateAccount.setAccountId(debit.getPayeeAccountNo());
				updateAccount.setAmount(newbalance);
				updateBalance(updateAccount);	
				return "transaction succesfull";	

			}
			else
			{
				return "Insufficient balance";	
			}
				
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
