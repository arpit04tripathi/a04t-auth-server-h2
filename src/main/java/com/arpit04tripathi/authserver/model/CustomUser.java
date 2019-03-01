package com.arpit04tripathi.authserver.model;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User{
	
	private static final long serialVersionUID = 1L;

	public CustomUser(UserModel user) {
		super(user.getUsername(), user.getPassword(), user.getGrantedAuthoritiesList());
	}
}
