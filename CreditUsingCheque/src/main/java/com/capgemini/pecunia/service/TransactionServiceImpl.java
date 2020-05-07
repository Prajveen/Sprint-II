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
	
	

	@SuppressWarnings("unused")
	@Override
	public String creditUsingCheque(ChequeTransactions credit) {

		Account payeeaccount= getAccountbyID(credit.getPayeeAccountNo());
		Account recipientaccount= getAccountbyID(credit.getRecipientAccountNo());
		
		if(payeeaccount.getAmount()>=credit.getAmount())
		{

			credit.setTransactionDate(date);
			credit.setChequeID(getRandomDoubleBetweenRange(200000,299999));
			dao.save(credit);
			
			transaction.setAccountId(credit.getPayeeAccountNo());
			transaction.setTransAmount(credit.getAmount());
			transaction.setTransDate(date);
			transaction.setTransTo(credit.getRecipientAccountNo());
			transaction.setTransFrom(credit.getPayeeAccountNo());
			transaction.setTransType(credit.getTransactionType());
			transaction.setTransId(credit.getTransactionID());
			transac.save(transaction);
			
			double payeeamount=payeeaccount.getAmount();
			double recipientamount=recipientaccount.getAmount();
			
			double payeenewbalance=payeeamount-credit.getAmount();
			Account updateAccount=new Account();
			updateAccount.setAccountId(credit.getPayeeAccountNo());
			updateAccount.setAmount(payeenewbalance);
			updateBalance(updateAccount);
			
			double recipientnewbalance=recipientamount+credit.getAmount();
			Account updateAccount1=new Account();
			updateAccount1.setAccountId(credit.getRecipientAccountNo());
			updateAccount1.setAmount(recipientnewbalance);
			updateBalance(updateAccount1);

			return "transaction succesfull ";	
		}
		else
		{
			return "Insufficient balance";	
		}

	}


	@Override
	public String updateBalance(Account balance){
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
