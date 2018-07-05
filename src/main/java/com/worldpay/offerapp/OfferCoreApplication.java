package com.worldpay.offerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.worldpay.offerapp"})
@EnableJpaRepositories("com.worldpay.offerapp.repository")
@EntityScan("com.worldpay.offerapp.model")
public class OfferCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferCoreApplication.class, args);
	}
}
