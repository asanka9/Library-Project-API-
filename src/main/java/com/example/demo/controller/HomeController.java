package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.NotFoundingException;
import com.example.demo.model.UserModel;
import com.example.demo.service.HomeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	HomeService service;
	
	@GetMapping("/numOfUsers")
	public int numOfUsers() {
		return service.numOfusers();
	}
	
	@GetMapping("/numOfLibraries")
	public int numOflibraries() {
		return service.numOfLibraries();
	}
	
	@GetMapping("/login/{email}/{password}")
	public UserModel loginUser(@PathVariable String email,@PathVariable String password) {
		UserModel model= service.loginVerification(email,password);

		if (model==null) {
			throw new NotFoundingException("No User Finding !!");
		} else {
			return model;
		}
	}
	
	@PostMapping("/externalUserCreate")
	public UserModel createExternalUser(@RequestBody  UserModel model) {
		return service.createExternalUser(model);
	}
}
