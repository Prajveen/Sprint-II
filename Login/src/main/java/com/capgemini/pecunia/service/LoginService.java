package com.capgemini.pecunia.service;

import com.capgemini.pecunia.entity.LoginDetails;

public interface LoginService {

	LoginDetails validateEmail(String username, String password);

	
}
