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
import com.capgemini.pecunia.entity.ChequeTransactions;
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
	public String debitUsingCheque(ChequeTransactions debit) throws Zero_balance_Exception {

		String getAccountUrl="http://localhost:1810/balance/getAccountbyID/"+debit.getPayeeAccountNo();
		Account account= restTemplate.getForObject(getAccountUrl, Account.class);

		if(account== null)
		{
			throw new Zero_balance_Exception("Dear Account holder,there is no bank account with "+debit.getPayeeAccountNo()+" ");
		}
		else  if(account.getBalance()==0)
		{
			throw new Zero_balance_Exception("Dear Account holder, your bank account with "+debit.getPayeeAccountNo()+" has insufficient balance to debit the required amount....!");
		}
		else 
		{
			debit.setTransactionDate(df.format(calobj.getTime()));
			debit.setChequeID(getRandomDoubleBetweenRange(200000,29999));
			dao.save(debit);
			double newbalance=account.getBalance()-debit.getAmount();
			Account updateAccount=new Account();
			updateAccount.setAccountID(debit.getPayeeAccountNo());
			updateAccount.setBalance(newbalance);
			updateBalance(updateAccount);	
			return "transaction succesfull ";	
		}
	}


	@Override
	public String updateBalance(Account balance) {
		rep.save(balance);
		return "updated successfully";
	}


	public static int getRandomDoubleBetweenRange(int min, int max){
		int x = (int) ((Math.random()*((max-min)+1))+min);
		return x;
	}
}
