package com.chatter.entity;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chatter.controller.UserController;
import com.chatter.dto.UserDTO;
import com.chatter.dto.UserLoginDTO;
import com.chatter.entity.User;
import com.chatter.exception.DuplicateUserFoundException;
import com.chatter.exception.UserNotFoundException;
import com.chatter.exception.WrongPasswordException;
import com.chatter.services.UserService;

public class UserTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() throws DuplicateUserFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmailId("test@example.com");
        userDTO.setUserName("Test User");
        userDTO.setUserPhoneNo("1234567890");
        userDTO.setUserPassword("password");

        User user = new User();
        user.setUserEmailId(userDTO.getUserEmailId());
        user.setUserName(userDTO.getUserName());
        user.setUserPhoneNo(userDTO.getUserPhoneNo());
        user.setUserPassword(userDTO.getUserPassword());

        when(userService.registerUser(userDTO)).thenReturn(user);

        ResponseEntity<User> response = userController.registerUser(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testUserLogin() throws UserNotFoundException, WrongPasswordException {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmailId("test@example.com");
        userLoginDTO.setPassword("password");

        User user = new User();
        user.setUserEmailId(userLoginDTO.getEmailId());

        when(userService.login(userLoginDTO)).thenReturn(user);

        ResponseEntity<User> response = userController.userLogin(userLoginDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserById() throws UserNotFoundException {
        String userEmailId = "test@example.com";

        User user = new User();
        user.setUserEmailId(userEmailId);

        when(userService.getUserByEmailId(userEmailId)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(userEmailId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testDeleteUserById() throws UserNotFoundException {
        String userEmailId = "test@example.com";
        String message = "User with email ID " + userEmailId + " deleted successfully";

        when(userService.deleteUserByEmailId(userEmailId)).thenReturn(message);

        ResponseEntity<String> response = userController.deleteUserById(userEmailId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
    }


    @Test
    public void testUpdateUserById() throws UserNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmailId("test@example.com");
        userDTO.setUserName("Updated User");
        userDTO.setUserPhoneNo("1234567890");
        userDTO.setUserPassword("password");

        User user = new User();
        user.setUserEmailId(userDTO.getUserEmailId());
        user.setUserName(userDTO.getUserName());
        user.setUserPhoneNo(userDTO.getUserPhoneNo());
        user.setUserPassword(userDTO.getUserPassword());

        when(userService.updateUserByEmailId(userDTO)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUserById(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setUserEmailId("user1@example.com");

        User user2 = new User();
        user2.setUserEmailId("user2@example.com");

        userList.add(user1);
        userList.add(user2);

        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    public void testRegisterUser_DuplicateUserFoundException() throws DuplicateUserFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmailId("test@example.com");
        userDTO.setUserName("Test User");
        userDTO.setUserPhoneNo("1234567890");
        userDTO.setUserPassword("password");

        String errorMessage = "User already exists with email ID: " + userDTO.getUserEmailId();
        when(userService.registerUser(userDTO)).thenThrow(new RuntimeException(errorMessage));

        Exception exception = assertThrows(RuntimeException.class, () -> userController.registerUser(userDTO));
        assertEquals(errorMessage, exception.getMessage());
    }



    @Test
    public void testUserLogin_UserNotFoundException() throws UserNotFoundException, WrongPasswordException {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmailId("test@example.com");
        userLoginDTO.setPassword("password");

        when(userService.login(userLoginDTO)).thenThrow(new UserNotFoundException(null));

        assertThrows(UserNotFoundException.class, () -> userController.userLogin(userLoginDTO));
    }

    @Test
    public void testUserLogin_WrongPasswordException() throws UserNotFoundException, WrongPasswordException {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmailId("test@example.com");
        userLoginDTO.setPassword("password");

        when(userService.login(userLoginDTO)).thenThrow(new WrongPasswordException(null));

        assertThrows(WrongPasswordException.class, () -> userController.userLogin(userLoginDTO));
    }

    @Test
    public void testGetUserById_UserNotFoundException() throws UserNotFoundException {
        String userEmailId = "test@example.com";

        when(userService.getUserByEmailId(userEmailId)).thenThrow(new UserNotFoundException(("User not found with email ID: " + userEmailId)));

        assertThrows(UserNotFoundException.class, () -> userController.getUserById(userEmailId));
    }

    @Test
    public void testDeleteUserById_UserNotFoundException() throws UserNotFoundException {
        String userEmailId = "test@example.com";

        doThrow(UserNotFoundException.class).when(userService).deleteUserByEmailId(userEmailId);

        assertThrows(UserNotFoundException.class, () -> userController.deleteUserById(userEmailId));
    }


    @Test
    public void testUpdateUserById_UserNotFoundException() throws UserNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmailId("test@example.com");
        userDTO.setUserName("Updated User");
        userDTO.setUserPhoneNo("1234567890");
        userDTO.setUserPassword("password");

        when(userService.updateUserByEmailId(userDTO)).thenThrow(new UserNotFoundException((null)));

        assertThrows(UserNotFoundException.class, () -> userController.updateUserById(userDTO));
    }
}
