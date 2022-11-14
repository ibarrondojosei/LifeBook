package com.ncs503.Babybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BabybookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabybookApplication.class, args);
	}

}
