package com.syneHealthInsurance.Aggregator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public RestTemplate restTemplate()
	{
		RestTemplate temp = new RestTemplate();
		return temp;
	}
	
	
}
