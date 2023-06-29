package com.chatter.services;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatter.dto.UserDTO;
import com.chatter.dto.UserLoginDTO;
import com.chatter.entity.User;
import com.chatter.exception.DuplicateUserFoundException;
import com.chatter.exception.UserNotFoundException;
import com.chatter.exception.WrongPasswordException;
import com.chatter.repository.UserRepository;




@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private Set<String> setOfEmailIds = new HashSet<>();
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	private String userNotFound = "User Not Found With email Id - ";

	@Override
	public User registerUser(UserDTO user) throws DuplicateUserFoundException {
		List<User> list = userRepository.findAll();
		for(User c : list)
			setOfEmailIds.add(c.getUserEmailId());
		if(setOfEmailIds.contains(user.getUserEmailId())) 
			throw new DuplicateUserFoundException("User already registered with email Id - " + user.getUserEmailId());
		User usr = new User(user.getUserEmailId(),user.getUserName(),user.getUserPhoneNo(),user.getUserPassword());
		return userRepository.save(usr);
	}

	@Override
	public User login(UserLoginDTO user) throws UserNotFoundException , WrongPasswordException {
		List<User> list = userRepository.findAll();
		for(User c : list)
			setOfEmailIds.add(c.getUserEmailId());
		if(!setOfEmailIds.contains(user.getEmailId()))
			throw new UserNotFoundException(userNotFound + user.getEmailId());
		User usr = userRepository.findByUserEmailId(user.getEmailId());
		if(!usr.getUserPassword().equals(user.getPassword()))
			throw new WrongPasswordException("Wrong Password Entered...");
		return usr;
	}

	@Override
	public User getUserByEmailId(String userEmail) throws UserNotFoundException {
		User user = userRepository.findByUserEmailId(userEmail);
		if(user == null)
			throw new UserNotFoundException(userNotFound + userEmail);
		return user;
	}

	@Override
	public String deleteUserByEmailId(String userEmail) throws UserNotFoundException {
		User user = userRepository.findByUserEmailId(userEmail);
		if(user == null)
			throw new UserNotFoundException(userNotFound + userEmail);
		userRepository.delete(user);
		return "User deleted with user email-id - " + userEmail;
	}

	@Override
	public User updateUserByEmailId(UserDTO user) throws UserNotFoundException {
		User usr = userRepository.findByUserEmailId(user.getUserEmailId());
		if(usr == null)
			throw new UserNotFoundException(userNotFound + user.getUserEmailId());
		usr.setUserName(user.getUserName());
		usr.setUserPhoneNo(user.getUserPhoneNo());
		usr.setUserPassword(user.getUserPassword());
		userRepository.save(usr);
		return usr;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


	
	
	
	
	
	
	
	
	
	

	
}

