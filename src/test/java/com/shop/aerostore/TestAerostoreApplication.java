package com.shop.aerostore;

import org.springframework.boot.SpringApplication;

public class TestAerostoreApplication {

	public static void main(String[] args) {
		SpringApplication.from(AerostoreApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
