package com.spring.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringHazelCastMultiNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHazelCastMultiNodeApplication.class, args);
	}

}
