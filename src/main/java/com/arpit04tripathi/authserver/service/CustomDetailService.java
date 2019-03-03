package com.arpit04tripathi.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arpit04tripathi.authserver.model.CustomUser;
import com.arpit04tripathi.authserver.model.UserModel;
import com.arpit04tripathi.authserver.repository.UserDAO;

@Service
public class CustomDetailService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			UserModel userModel = userDAO.getUserDetails(username);
			CustomUser customUser = new CustomUser(userModel);
			return customUser;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}

}
