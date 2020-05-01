package com.capgemini.pecunia.service;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capgemini.pecunia.dao.TransactionDao;
import com.capgemini.pecunia.dao.TransactionRepository;
import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.SlipTransactions;
import com.capgemini.pecunia.exceptions.Account_NotFoundException;
import com.capgemini.pecunia.exceptions.Zero_balance_Exception;
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository dao;
	@Autowired
	private TransactionDao repository;

	RestTemplate restTemplate = new RestTemplate();
	Date dateobj = new Date();
	Calendar calobj = Calendar.getInstance();
	
	@Override
	public String debitUsingSlip(SlipTransactions debit) throws Zero_balance_Exception, Account_NotFoundException {

		Account account= getAccountbyID(debit.getAccountNo());
			debit.setTransactionDate((calobj.getTime()));
			dao.save(debit);
			double newbalance=account.getBalance()-debit.getAmount();
			Account updateAccount=new Account();
			updateAccount.setAccountID(debit.getAccountNo());
			updateAccount.setBalance(newbalance);
			updateBalance(updateAccount);
		return "transaction succesfull ";	
	}

	
	@Override
	public String updateBalance(Account balance) throws Account_NotFoundException {
			repository.save(balance);
		return "updated successfully";
	}



	@Override
	public Account getAccountbyID(String accountID) {
		return repository.getAccountbyID(accountID);
	}
}
