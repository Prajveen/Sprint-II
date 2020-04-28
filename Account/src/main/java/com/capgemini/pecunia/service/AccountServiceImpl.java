package com.capgemini.pecunia.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.dao.AccountRepository;
import com.capgemini.pecunia.entity.Account;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository dao;
	
	
	@Override
	public Double getbalance(String accountID) {
		return  dao.getbalance(accountID);
	}
	@Override
	public String updatebalance(Account balance) {
		 dao.save(balance);
		 return " updated successfully";	
	}
	

	
	@Override
	public Optional<Account> getAccountbyID(String accountID) {
		
		return dao.getAccountbyID(accountID);
	}

	
}
