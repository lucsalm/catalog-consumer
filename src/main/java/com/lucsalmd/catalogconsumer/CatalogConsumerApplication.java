package com.lucsalmd.catalogconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CatalogConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogConsumerApplication.class, args);
	}

}
