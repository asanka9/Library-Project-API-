package com.example.demo.model;


import javax.persistence.Entity;

import javax.persistence.Id;

import lombok.Data;


@Entity(name = "Usertable")
@Data
public class UserModel {
	
	 
	@Id
	private String id;
	private String email;
	private String name;
	private String password;
	private String role;
	private String mnumber;
	private String lid;
	private String address;
	private String nic;
	private String gender;
	 
	
	
	

	

	
	

}
