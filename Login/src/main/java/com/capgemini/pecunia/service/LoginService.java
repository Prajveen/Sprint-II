package com.capgemini.pecunia.service;

import com.capgemini.pecunia.entity.LoginDetails;
import com.capgemini.pecunia.exceptions.User_NotFoundException;

public interface LoginService {

	LoginDetails validateEmail(String username, String password) throws User_NotFoundException;

	
}
