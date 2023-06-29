//package com.chatter.controller;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Component;
//
//import com.chatter.dto.UserDTO;
//import com.chatter.entity.User;
//
//
//
//@Component
//public class Converter {
//
//	public User convertToUserEntity(User userdto) {
//		User user = new User();
//				
//		if(userdto != null) {
//			BeanUtils.copyProperties(userdto, user);
//		}
//		
//		return user;
//	}
//	
//	public UserDTO convertToUserTO(User user) {
//		UserDTO userdto = new UserDTO();
//		
//		if(user != null) {
//			BeanUtils.copyProperties(user, userdto);			
//		}
//		
//		return userdto;
//	}
//}
//
