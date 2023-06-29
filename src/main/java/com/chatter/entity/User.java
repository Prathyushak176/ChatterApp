package com.chatter.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.lang.NonNull;

import com.chatter.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long userId;
	@NotBlank
	@Email
	private String userEmailId;
	@NotBlank
//	@Size(min = 4, max = 8, message = "Plz Enter 8 Characters Long Name...")
	private String userName;
	@NotBlank
	@Pattern(regexp = "[0-9]{10}", message = "Enter 10 digit Mobile Number")
	private String userPhoneNo;
	@NotBlank
	@Size(min = 7, max = 8, message = "Plz Enter 8 Characters Long Password...")
	private String userPassword;

	public User( String userEmailId,String userName,
		 String userPhoneNo,
			 String userPassword) {
		super();
		this.userEmailId = userEmailId;
		this.userName = userName;
		this.userPhoneNo = userPhoneNo;
		this.userPassword = userPassword;
	}
	public User(UserDTO userdto) {
		this.userEmailId=userdto.getUserEmailId();
		this.userId=userdto.getUserId();
		this.userName=userdto.getUserName();
		this.userPhoneNo=userdto.getUserPhoneNo();
		this.userPassword=userdto.getUserPassword();
		
	}

}
