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
import com.capgemini.pecunia.entity.ChequeTransactions;
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
	

	
	@SuppressWarnings("unused")
	@Override
	public String creditUsingCheque(ChequeTransactions credit) throws Zero_balance_Exception, Account_NotFoundException {
		String getpayeeAccountUrl="http://localhost:1810/balance/getAccountbyID/"+credit.getPayeeAccountNo();
		Account payeeaccount= restTemplate.getForObject(getpayeeAccountUrl, Account.class);
		String getrecipientAccountUrl="http://localhost:1810/balance/getAccountbyID/"+credit.getRecipientAccountNo();
		Account recipientaccount= restTemplate.getForObject(getrecipientAccountUrl, Account.class);
		int compare=payeeaccount.getBalance().compareTo(credit.getAmount());

		if(payeeaccount== null)
		{
			throw new Zero_balance_Exception("Dear Account holder,there is no bank account with "+credit.getPayeeAccountNo()+" ");
		}
		else  if((payeeaccount.getBalance()==null )&&(compare<0))
		{
			throw new Zero_balance_Exception("Dear Account holder, your bank account with "+credit.getPayeeAccountNo()+" has insufficient balance to debit the required amount....!");
		}
		else 
		{
			credit.setTransactionDate((calobj.getTime()));
			credit.setChequeID(getRandomDoubleBetweenRange(200000,299999));
			dao.save(credit);
			double payeeamount=payeeaccount.getBalance();
			double recipientamount=recipientaccount.getBalance();
			double payeenewbalance=payeeamount-credit.getAmount();
			Account updateAccount=new Account();
			updateAccount.setAccountID(credit.getPayeeAccountNo());
			updateAccount.setBalance(payeenewbalance);
			updateBalance(updateAccount);
			double recipientnewbalance=recipientamount+credit.getAmount();
			Account updateAccount1=new Account();
			updateAccount1.setAccountID(credit.getRecipientAccountNo());
			updateAccount1.setBalance(recipientnewbalance);
			updateBalance(updateAccount1);
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
		return "updated successfully";
		}
	}


	public static int getRandomDoubleBetweenRange(int min, int max){
		int x = (int) ((Math.random()*((max-min)+1))+min);
		return x;
	}
}
