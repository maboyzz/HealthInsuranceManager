package com.nthuy.healthinsurancemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HealthInsuranceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthInsuranceManagerApplication.class, args);
	}

}
