package com.swapichallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class SwapichallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapichallengeApplication.class, args);
	}
}
