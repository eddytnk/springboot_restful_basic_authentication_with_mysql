package com.eddy.apisecurity.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilsFunctions {

	public static String bCryptPasswordEncode(String rawPassword) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(rawPassword);
	}
	
	/*public static void main(String[] args) {
		System.out.println(UtilsFunctions.bCryptPasswordEncode("pppp"));
	}*/
	
}
