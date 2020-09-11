package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.LibraryModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.AdminService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService service;
	
	@GetMapping("/getLibrary")
	public List<LibraryModel> getLibrary(){
		return service.getAllLibraries();
	}
	
	@PostMapping("/createAdmin")
	public UserModel createAdmin() {
		return null;
	}
		
	
	
}
