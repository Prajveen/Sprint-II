package com.capgemini.pecunia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ChequeTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChequeTransactionApplication.class, args);
	}

}
