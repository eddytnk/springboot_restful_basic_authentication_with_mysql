package com.eddy.apisecurity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eddy.apisecurity.entities.UserInfo;
import com.eddy.apisecurity.repos.UserRepo;

@Service
public class UserService implements IUserService{
	
	@Autowired
	UserRepo userRepos;

	@Override
	public List<UserInfo> getUsers(){
		return userRepos.findAll();
	}
	
	@Override
	public Optional<UserInfo> getUser(Long id){
		return userRepos.findById(id);
	}
	
	
	public boolean isUserExist(UserInfo user) {
		Optional<UserInfo> u = this.getUser(user.getId());
		if(u.isPresent())return true;
		return false;
	}
	
	@Override
	public UserInfo add(UserInfo user) {
		UserInfo u = userRepos.save(user);
		return u;
	}
	@Override
	public void deleteUserById(Long id) {
		userRepos.deleteById(id);
	}
	
	
	public UserInfo getActiveUser(String username) {
		short enabled = 1;
		UserInfo user  = userRepos.findByUsernameAndEnabled(username, enabled);
		return user;
	}

	@Override
	public UserInfo update(UserInfo user) {
		UserInfo u = userRepos.save(user);
		return u;
	}
}
