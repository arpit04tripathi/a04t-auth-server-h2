package com.arpit04tripathi.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.arpit04tripathi.authserver.service.CustomDetailService;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Value("${oAuth2.clientId}")
	private String clientId = "client";
	@Value("${oAuth2.clientSecret}")
	private String clientSecret = "$2a$10$bLQbnfwXStFnUF8RcAjhFeUBPLcuL7CphbexAxfQZfnHtE1nuGtLW";
//	"secret";
	@Value("${oAuth2.key.private:default}")
	private String privateKey;
	@Value("${oAuth2.key.public:default}")
	private String publicKey;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	CustomDetailService userDetailsService;
	
	@Bean
	public JwtAccessTokenConverter jwtTokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		System.out.println(String.format("private key - %s", privateKey));
		System.out.println(String.format("public key - %s", publicKey));
		return converter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(jwtTokenEnhancer());
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		endpoints.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService)
		// Use JwtTokenStore and our jwtAccessTokenConverter
		.tokenStore(tokenStore())
		.accessTokenConverter(jwtTokenEnhancer());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory().withClient(clientId).secret(clientSecret)
		.scopes("read","write")
		.authorizedGrantTypes("password","refresh_token")
		.accessTokenValiditySeconds(60)
		.refreshTokenValiditySeconds(600);
	}
	
}
