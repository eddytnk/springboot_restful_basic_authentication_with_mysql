package com.eddy.apisecurity.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eddy.apisecurity.entities.UserInfo;
import com.eddy.apisecurity.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserInfo>> getUsers(){
		List<UserInfo> user =  userService.getUsers();
		if(user.isEmpty()) {
			return new ResponseEntity<List<UserInfo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserInfo>>(user,HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserInfo> getUser(@PathVariable Long  id){
		Optional<UserInfo> user = userService.getUser(id);
		if(!user.isPresent()) {
			return new ResponseEntity<UserInfo>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserInfo>(user.get(),HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> addUser(@RequestBody UserInfo user, UriComponentsBuilder ucBuilder){
		
		if(userService.isUserExist(user)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		UserInfo u = userService.add(user);
		
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("users/{id}").buildAndExpand(u.getId()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserInfo> updateUser(@PathVariable Long id, @RequestBody UserInfo user){
		Optional<UserInfo> currentUser = userService.getUser(id);
		if(!currentUser.isPresent()) {
			return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
		}
		currentUser.get().setName(user.getName());
		userService.add(currentUser.get());
		
		return new ResponseEntity<UserInfo>(HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<UserInfo> deleteUser(@PathVariable Long id){
		Optional<UserInfo> user = userService.getUser(id);
		if(!user.isPresent()) {
			return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<UserInfo>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
}
