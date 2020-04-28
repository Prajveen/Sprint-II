package com.capgemini.pecunia.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private TransactionDao rep;

	RestTemplate restTemplate = new RestTemplate();
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	Calendar calobj = Calendar.getInstance();
	
	@Override
	public String debitUsingSlip(SlipTransactions debit) throws Zero_balance_Exception, Account_NotFoundException {

		String getAccountUrl="http://localhost:1810/balance/getAccountbyID/"+debit.getAccountNo();
		Account account= restTemplate.getForObject(getAccountUrl, Account.class);
		
		if(account== null)
		{
			throw new Zero_balance_Exception("Dear Account holder,there is no bank account with "+debit.getAccountNo()+" ");
		}
		else  if(account.getBalance()==null)
		{
			throw new Zero_balance_Exception("Dear Account holder, your bank account with "+debit.getAccountNo()+" has insufficient balance to debit the required amount....!");
		}
		else 
		{	
			debit.setTransactionDate(df.format(calobj.getTime()));
			dao.save(debit);
			double newbalance=account.getBalance()-debit.getAmount();
			Account updateAccount=new Account();
			updateAccount.setAccountID(debit.getAccountNo());
			updateAccount.setBalance(newbalance);
			updateBalance(updateAccount);
		}
		return "transaction succesfull ";	
	}

	
	@Override
	public String updateBalance(Account balance) throws Account_NotFoundException {
		String getAccountUrl="http://localhost:1810/balance/getAccountbyID/"+balance.getAccountID();
		Account account= restTemplate.getForObject(getAccountUrl, Account.class);
		
		if(account==null)
		{
			throw new Account_NotFoundException("account with "+balance.getAccountID()+" doesn't exist....!");
		}
		else
		{
		rep.save(balance);
		}
		return "updated successfully";
	}

}
