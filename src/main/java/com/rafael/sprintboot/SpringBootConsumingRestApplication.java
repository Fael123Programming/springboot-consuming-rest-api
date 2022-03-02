package com.rafael.sprintboot;

import com.rafael.sprintboot.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootConsumingRestApplication {
//	A logger, to send output to the log (the console, in this example).
	private static final Logger log = LoggerFactory.getLogger(SpringBootConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConsumingRestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		A RestTemplate uses the Jackson JSON processing library to process the incoming data.
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		A CommandLineRunner is responsible for running our RestTemplate
//		(and, consequently, to fetch our quotation) on startup.
		return args -> {
			Quote quote =  restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
			if (quote != null)
				log.info(quote.toString());
			else
				log.info("Quote is null");
		};
	}
}
