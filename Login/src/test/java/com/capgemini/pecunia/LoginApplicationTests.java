package com.capgemini.pecunia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.pecunia.entity.LoginDetails;
import com.capgemini.pecunia.service.LoginService;

@SpringBootTest

class LoginApplicationTests {

	@Autowired
	private LoginService service;


	@Test
	public void ValidDetails() 
	{
		LoginDetails result = new LoginDetails();
		LoginDetails details = new LoginDetails();

		result.setEmployeeID("87654321");
		result.setUsername("prajveen07@gmail.com");
		result.setPassword("prajju@12");

		details=service.validateEmail("prajveen07@gmail.com","prajju@12");
		
		Assertions.assertEquals(result.toString(), details.toString());
	}

	@Test
	public void InValidDetails() throws Exception
	{		

		LoginDetails details = new LoginDetails();

		String username="prajveen@gmail.com";
		String password="prajveen";
		details=service.validateEmail(username,password);
		Assertions.assertEquals(null, details);

	}

}
