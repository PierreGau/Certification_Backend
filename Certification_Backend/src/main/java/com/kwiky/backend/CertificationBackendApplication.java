package com.kwiky.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CertificationBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertificationBackendApplication.class, args);
	}

}
