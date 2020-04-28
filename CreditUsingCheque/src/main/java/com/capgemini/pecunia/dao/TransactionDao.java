package com.capgemini.pecunia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.pecunia.entity.Account;

public interface TransactionDao extends JpaRepository<Account, String> {

}
