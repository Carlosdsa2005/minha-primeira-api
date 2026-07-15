package com.CarlosDaniel.minhaprimeriaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // <-- ESSA É A MÁGICA
public class MinhaprimeriaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhaprimeriaApiApplication.class, args);
	}

}
