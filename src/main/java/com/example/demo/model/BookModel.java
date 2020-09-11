package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "Booktable")
@Data
public class BookModel {
	
	@Id
	private String id; //library id and book name
	private String bookcode; //genarate with library
	private String name;
	private String authorname;
	private String blocation;
	private double price;
	private String categoryid;
	private String libraryid;
	private String borrowed;
	
	

}
