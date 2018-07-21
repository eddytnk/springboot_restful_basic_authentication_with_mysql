package com.eddy.apisecurity.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eddy.apisecurity.entities.UserInfo;
import com.eddy.apisecurity.services.UserService;

@Service
public class UserDeatailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userService.getActiveUser(username);
		
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		
		UserDetails userDetails = (UserDetails) new User(user.getUsername(),
				user.getPassword(), Arrays.asList(authority));
		System.out.println(userDetails);
		return userDetails;
	}
	
}
