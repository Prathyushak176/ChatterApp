package com.chatter.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.WrongClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatter.dto.UserDTO;
import com.chatter.dto.UserLoginDTO;
import com.chatter.entity.User;
import com.chatter.exception.DuplicateUserFoundException;
import com.chatter.exception.UserNotFoundException;
import com.chatter.exception.WrongPasswordException;

import com.chatter.services.UserService;



@RequestMapping("user_section")
@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class UserController {
	
	@Autowired
	private UserService userServices;
	
	
	@PostMapping("user_registration")
	public ResponseEntity<User> registerUser(@Valid@RequestBody UserDTO user) throws DuplicateUserFoundException {
		User usr = userServices.registerUser(user);
		return new ResponseEntity<>(usr,HttpStatus.OK);
	}
	
	@PostMapping("user_login")
	public ResponseEntity<User> userLogin(@Valid@RequestBody UserLoginDTO user) throws UserNotFoundException, WrongPasswordException {
		User usr = userServices.login(user);
		return new ResponseEntity<User>(usr ,HttpStatus.OK);
	}
	
	@GetMapping("get_user_by_id/{userEmailId}")
	public ResponseEntity<User> getUserById(@Valid@PathVariable("userEmailId") String userEmailId) throws UserNotFoundException {
		User usr = userServices.getUserByEmailId(userEmailId);
		return new ResponseEntity<User>(usr,HttpStatus.OK);
	}
	
	@DeleteMapping("delete_user_by_id/{userEmailId}")
	public ResponseEntity<String> deleteUserById(@Valid@PathVariable("userEmailId") String emailId) throws UserNotFoundException {
		String message = userServices.deleteUserByEmailId(emailId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@PutMapping("update_user/{userEmailId}")
	public ResponseEntity<User> updateUserById(@Valid@RequestBody UserDTO user) throws UserNotFoundException {
		User usr = userServices.updateUserByEmailId(user);
		return new ResponseEntity<User>(usr,HttpStatus.OK);
	}
	
	@GetMapping("get_all_users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> user = userServices.getAllUsers();
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}
	
//	@GetMapping("get_user_by_userId/{userId}")
//	public User getUserByUserId(@PathVariable("userId")Long userId) {
//		return userServices.getUserById(userId);
//	}
}