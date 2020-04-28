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
	private TransactionDao repository;
	

	RestTemplate restTemplate = new RestTemplate();
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	Calendar calobj = Calendar.getInstance();
	

	@Override
	public String creditUsingSlip(SlipTransactions credit) throws Zero_balance_Exception, Account_NotFoundException {
		String getAccountUrl="http://localhost:1810/balance/getAccountbyID/"+credit.getAccountNo();
		Account account= restTemplate.getForObject(getAccountUrl, Account.class);
		
		if(account== null)
		{
			throw new Zero_balance_Exception("Dear Account holder,there is no bank account with "+credit.getAccountNo()+" ");
		}
		else  if(account.getBalance()==null)
		{
			throw new Zero_balance_Exception("Dear Account holder, your bank account with "+credit.getAccountNo()+" has insufficient balance to debit the required amount....!");
		}
		else 
		{
			credit.setTransactionDate(df.format(calobj.getTime()));
			dao.save(credit);
			double amount=account.getBalance();
			double payeenewbalance=amount+credit.getAmount();
			Account updateAccount=new Account();
			updateAccount.setAccountID(credit.getAccountNo());
			updateAccount.setBalance(payeenewbalance);
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
			repository.save(balance);
		}
		
		return "updated successfully";
	}
	
	

}
