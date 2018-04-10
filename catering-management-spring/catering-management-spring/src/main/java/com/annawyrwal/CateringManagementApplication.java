package com.annawyrwal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
public class CateringManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CateringManagementApplication.class, args);
	}
}
