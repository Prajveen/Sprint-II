package com.capgemini.pecunia.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


	@Query("select amount from Account   where accountId =?1 ")
	Double getbalance(String accountID);
	
	@Query("select f from Account f  where accountId =?1")
	Optional<Account> getAccountbyID(String accountID);
	
}
