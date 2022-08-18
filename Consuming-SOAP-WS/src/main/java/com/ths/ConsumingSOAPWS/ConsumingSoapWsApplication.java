package com.ths.ConsumingSOAPWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.ths.ConsumingSOAPWS.wsdl.GetCountryResponse;


@SpringBootApplication
public class ConsumingSoapWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumingSoapWsApplication.class, args);
	}

//	@Bean
//	CommandLineRunner lookup(CountryClient quoteClient) {
//		return args -> {
//			String country = "Spain";
//
//			if (args.length > 0) {
//				country = args[0];
//			}
//			GetCountryResponse response = quoteClient.getCountry(country);
//			System.err.println(response.getCountry().getCurrency());
//		};
//	}

}
