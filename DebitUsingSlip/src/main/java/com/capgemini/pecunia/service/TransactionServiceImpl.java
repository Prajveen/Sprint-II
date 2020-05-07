package com.capgemini.pecunia.service;


import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.dao.SlipTransactionsDao;
import com.capgemini.pecunia.dao.TransactionsDao;
import com.capgemini.pecunia.dao.UpdateBalanceDao;
import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.SlipTransactions;
import com.capgemini.pecunia.entity.Transactions;
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private SlipTransactionsDao dao;
	@Autowired
	private UpdateBalanceDao repository;
	@Autowired
	private TransactionsDao transac;


	Transactions transaction=new Transactions();

	long millis=System.currentTimeMillis();  
	Date date=new Date(millis); 


	@Override
	public String debitUsingSlip(SlipTransactions debit)  {

		Account account= getAccountbyID(debit.getAccountNo());
		
		if(account.getAmount()>=debit.getAmount())
		{
			debit.setTransactionDate(date);
			dao.save(debit);
			

			transaction.setAccountId(debit.getAccountNo());
			transaction.setTransAmount(debit.getAmount());
			transaction.setTransDate(date);
			transaction.setTransFrom(debit.getAccountNo());
			transaction.setTransId(debit.getTransactionID());
			transaction.setTransTo("self");
			transaction.setTransType(debit.getTransactionType());
			transac.save(transaction);

			
			double newbalance=account.getAmount()-debit.getAmount();
			Account updateAccount=new Account();
			updateAccount.setAccountId(debit.getAccountNo());
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
	public String updateBalance(Account balance)  {
		repository.save(balance);
		return "updated successfully";
	}



	@Override
	public Account getAccountbyID(String accountID) {
		return repository.getAccountbyID(accountID);
	}
}
