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
import com.capgemini.pecunia.exceptions.Account_NotFoundException;
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



	@SuppressWarnings("unused")
	@Override
	public String creditUsingCheque(ChequeTransactions credit) throws Zero_balance_Exception, Account_NotFoundException {

		Account payeeaccount= getAccountbyID(credit.getPayeeAccountNo());
		Account recipientaccount= getAccountbyID(credit.getRecipientAccountNo());

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

	public static int getRandomDoubleBetweenRange(int min, int max){
		int x = (int) ((Math.random()*((max-min)+1))+min);
		return x;
	}
}
