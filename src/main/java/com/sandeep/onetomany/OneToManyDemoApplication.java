package com.sandeep.onetomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OneToManyDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneToManyDemoApplication.class, args);
	}

}
