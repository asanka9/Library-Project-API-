package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "Librarytable")
@Data
public class LibraryModel {

	@Id
	private String id;
	private String name;
	private String address;
	private String googlelocation;
	private String email;
	private String tel;
}
