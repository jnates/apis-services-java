package com.vobi.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankUserApplication.class, args);
	}

}
