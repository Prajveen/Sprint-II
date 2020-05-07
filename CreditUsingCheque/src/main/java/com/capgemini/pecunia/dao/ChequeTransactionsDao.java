package com.capgemini.pecunia.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.entity.ChequeTransactions;

@Repository
public interface ChequeTransactionsDao extends JpaRepository<ChequeTransactions, String> {

	
	
	
}
