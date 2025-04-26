package com.counters.www;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

public class DriveinProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriveinProjectApplication.class, args);
	}
	@Bean
	public ModelMapper ModelMapper()
	{
		return new ModelMapper();
	}
	
}
