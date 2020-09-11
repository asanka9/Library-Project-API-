package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name="Ordertable")
@Data 
public class OrderModel {
	
	@Id
	private String id; //this is same with book id
	private String bookname;
	private String authorname;
	private String libraryid;
	private String userid;
	private String date01;
	private String date02;
	private int countdays;

}
