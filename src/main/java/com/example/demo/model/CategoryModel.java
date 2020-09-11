package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "Categorytable")
@Data
public class CategoryModel {
	
	@Id
	private String id; //This id genarate with library is
	private  String categoryname;
	private String libraryid;
	private int numofbooks;
	private String  description;
	
}
