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
	public String creditUsingSlip(SlipTransactions credit){
	
			Account account= getAccountbyID(credit.getAccountNo());
			credit.setTransactionDate(date);
			dao.save(credit);
			

			transaction.setAccountId(credit.getAccountNo());
			transaction.setTransDate(date);
			transaction.setTransAmount(credit.getAmount());
			transaction.setTransFrom(credit.getAccountNo());
			transaction.setTransTo("self");
			transaction.setTransId(credit.getTransactionID());
			transaction.setTransType(credit.getTransactionType());
			transac.save(transaction);
			
			double amount=account.getAmount();
			double payeenewbalance=amount+credit.getAmount();
			Account updateAccount=new Account();
			updateAccount.setAccountId(credit.getAccountNo());
			updateAccount.setAmount(payeenewbalance);
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

}
