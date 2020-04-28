package com.capgemini.pecunia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.entity.LoginDetails;
@Repository
public interface LoginDAO extends JpaRepository<LoginDetails, Integer> {

	
	@Query("select d from LoginDetails d  where username =?1 and password=?2")
	LoginDetails validateEmail(String username, String password);

	
}
