package com.example.sg_kata_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.sg_kata_back")
public class SgKataBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgKataBackApplication.class, args);
	}

}
