package com.capgemini.pecunia.service;

import com.capgemini.pecunia.entity.LoginDetails;
import com.capgemini.pecunia.exceptions.UserNotFoundException;

public interface LoginService {

	LoginDetails validateEmail(String username, String password) throws UserNotFoundException;

	
}
