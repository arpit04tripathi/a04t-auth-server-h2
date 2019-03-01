package com.arpit04tripathi.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	private String clientId = "arpit04tripathi";
	private String clientSecret = "my-secret";
	
	private String privateKey = "";
	private String publicKey = "";
	
	
}
