package com.capgemini.pecunia.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.dao.LoginDAO;
import com.capgemini.pecunia.entity.LoginDetails;
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO dao;
	
	@Override
	public LoginDetails validateEmail(String username, String password)  {
		return dao.validateEmail(username,password);
	}

}
