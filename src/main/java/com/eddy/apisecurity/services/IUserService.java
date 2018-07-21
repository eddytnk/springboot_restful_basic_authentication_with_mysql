package com.eddy.apisecurity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.annotation.Secured;

import com.eddy.apisecurity.entities.UserInfo;

public interface IUserService {
	
     @Secured ({"ROLE_ADMIN"})
     List<UserInfo> getUsers();
     
     @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     Optional<UserInfo> getUser(Long id);
     
     @Secured ({"ROLE_ADMIN"})
     UserInfo add(UserInfo user);
     
     @Secured ({"ROLE_ADMIN"})
     UserInfo update(UserInfo user);
     
     @Secured ({"ROLE_ADMIN"})
     void deleteUserById(Long id);
} 