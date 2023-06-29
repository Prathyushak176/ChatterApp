package com.chatter.services;

import java.util.List;

import com.chatter.dto.UserDTO;
import com.chatter.dto.UserLoginDTO;
import com.chatter.entity.User;
import com.chatter.exception.DuplicateUserFoundException;
import com.chatter.exception.UserNotFoundException;
import com.chatter.exception.WrongPasswordException;



public interface UserService {
	
	public User registerUser(UserDTO user) throws DuplicateUserFoundException;
	public User getUserByEmailId(String userEmail) throws UserNotFoundException;
	public String deleteUserByEmailId(String userEmail) throws UserNotFoundException;
	public User updateUserByEmailId(UserDTO user) throws UserNotFoundException; 
	public List<User> getAllUsers();
	
	
	public User login(UserLoginDTO user) throws UserNotFoundException , WrongPasswordException;

	
	
	
}
