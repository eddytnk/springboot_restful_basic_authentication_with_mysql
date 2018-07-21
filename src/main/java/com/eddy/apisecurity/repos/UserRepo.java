package com.eddy.apisecurity.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eddy.apisecurity.entities.UserInfo;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, Long>{

	public UserInfo findByUsernameAndEnabled(String username, short enabled); 
	
}
