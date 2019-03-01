package com.arpit04tripathi.authserver.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import com.arpit04tripathi.authserver.model.UserModel;

@Repository
public class UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public UserModel getUserDetails(String username) {
		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		String sqlQuery = "SELECT * FROM USERS WHERE USERNAME = ?";
//		List<UserModel> list = jdbcTemplate.query(sqlQuery, new String[] {username}, (ResultSet rs, int rowNum)->{
//			UserModel userModel = new UserModel();
//			
//			return userModel;
//		})
		return null;
	}
}
