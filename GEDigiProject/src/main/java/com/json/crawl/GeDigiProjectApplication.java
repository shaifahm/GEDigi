package com.json.crawl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeDigiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeDigiProjectApplication.class, args);
		
		JsonParser ob = new JsonParser();
		ob.readFromJsonFile();
	}

}
