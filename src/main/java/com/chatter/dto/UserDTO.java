package com.chatter.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.chatter.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@ToString
public class UserDTO {

	private Long userId;
	@Email(message = "Please enter a valid email address")
	@NotBlank(message = "Email address is required")
	@Pattern(regexp = ".+\\.com$", message = "Email address must end with '.com'")
	private String userEmailId;
	@NotBlank
	@Size(min = 4, max = 30, message = "Enter min 4 and max 30 Characters Long Name")
	private String userName;
	@NotBlank
	@Pattern(regexp = "[0-9]{10}", message = "Enter 10 digit Numeric Mobile Number")
	private String userPhoneNo;
	@NotBlank
	@Size(min = 8, max = 8, message = "Enter 8 Character Alpha-Numeric Password")
	private String userPassword;

	public UserDTO(User user) {
		this.userEmailId = user.getUserEmailId();
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.userPhoneNo = user.getUserPhoneNo();
		this.userPassword = user.getUserPassword();

	}

}