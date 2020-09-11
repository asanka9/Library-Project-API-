package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.UserModel;
import com.example.demo.model.config.PasswordGenarator;
import com.example.demo.model.repo.LibraryModelRepo;
import com.example.demo.model.repo.UserModelRepo;

@Service
public class HomeService {
	
	@Autowired
	UserModelRepo repo;
	
	@Autowired
	LibraryModelRepo lrepo;
	
	
	public int numOfusers() {
		return repo.findAll().size();
	}
	
	public int numOfLibraries() {
		return lrepo.findAll().size();
	}
	
	public UserModel loginVerification(String email,String pass) {
		System.out.println("++++++++++++++++++++++");
		System.out.println(email);
		System.out.println(pass);
		System.out.println("++++++++++++++++++++++");
		UserModel model01 = (repo.findById(email).orElse(new UserModel()));
		String password01 = model01.getPassword();
		password01 = PasswordGenarator.passwordGenaratorMethod(password01);
		if (password01.equals(pass)) {
			return model01;
		} else {
			return null;
		}
	}
	
	public UserModel createExternalUser(UserModel model) {
		model.setRole("external");
		UserModel uModel=repo.findById(model.getId()).orElse(null);
		if (uModel!=null) {
			throw new BadRequestException("already have account");
		}else {
			model.setName(model.getId());
			repo.save(model);
			return model;
		}
		
	}
}
