package com.capgemini.pecunia.service;

import java.util.Optional;

import com.capgemini.pecunia.entity.Account;

public interface AccountService {

	
	Double getbalance(String accountID);

	String updatebalance(Account balance);

	Optional<Account> getAccountbyID(String accountID);

}
