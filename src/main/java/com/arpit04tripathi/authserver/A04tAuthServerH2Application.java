package com.arpit04tripathi.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class A04tAuthServerH2Application {

	public static void main(String[] args) {
		SpringApplication.run(A04tAuthServerH2Application.class, args);
	}

}
