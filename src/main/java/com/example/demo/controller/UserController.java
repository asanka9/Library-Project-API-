package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LibraryModel;
import com.example.demo.model.OrderModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserControllerService;


@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserControllerService service;
	
	@PutMapping("/updateUser/{id}")
	public UserModel updateUser(@PathVariable String id,@RequestBody UserModel  model) {
		return service.updateUserDetails(id,model);
	}
	
	@GetMapping("/getLibraryName/{id}")
	public String getLibraryName(@PathVariable String id) {
		return service.getLibraryname(id);
	}
	
	@PostMapping("/addLibraries/{userId}/{password}")
	public List<LibraryModel> addLibraryToUserAccount(@PathVariable String userId,@PathVariable String password,@RequestBody UserModel model){
		return service.addLibraryToExternalUserAccount(userId, password, model);
	}
	
	@GetMapping("/returnLibs/{libs}")
	public List<LibraryModel> returnLibs(@PathVariable String libs ){
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return service.getListOfLibraries(libs);
	}
	
	@PostMapping("/returnOrderBooks")
	public List<OrderModel> returnAllOrderBooks(@RequestBody UserModel model){
		return service.returnAllBooks(model);
	}
	
	@GetMapping("/getUser/{userId}")
	public UserModel returnUserDetails(@PathVariable String userId) {
		return service.returnuserDetails(userId);
	}
	
}
