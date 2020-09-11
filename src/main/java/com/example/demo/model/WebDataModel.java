package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity(name = "Webdatatable")
public class WebDataModel {
	
	@Id
	private int id;
	private String categoryimagelist;
	
}
