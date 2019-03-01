package com.arpit04tripathi.authserver.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.arpit04tripathi.authserver.model.UserModel;

@Repository
public class UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public UserModel getUserDetails(String username) {
		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		String sqlQuery = "SELECT * FROM USERS WHERE USERNAME = ?";
		
		List<UserModel> list = jdbcTemplate.queryForList(sqlQuery, new String[] {username}, UserModel.class);
		
		if(CollectionUtils.isEmpty(list)) {
			GrantedAuthority grantedAuthority= new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
			grantedAuthoritiesList.add(grantedAuthority);
			list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
			return list.get(0);
		}
		
		return null;
	}
}
