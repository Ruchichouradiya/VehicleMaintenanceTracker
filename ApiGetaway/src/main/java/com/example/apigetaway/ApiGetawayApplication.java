package com.example.apigetaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;


@SpringBootApplication
public class ApiGetawayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGetawayApplication.class, args);
	}
	
	@Bean
	public HttpClient httpClient() {
	    return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
	}

}
