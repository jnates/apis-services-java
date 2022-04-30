package com.vobi.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankBalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankBalanceApplication.class, args);
	}

}
