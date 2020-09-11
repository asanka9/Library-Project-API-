package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LibraryModel;
import com.example.demo.model.repo.LibraryModelRepo;

@Service
public class AdminService {
	
	@Autowired
	LibraryModelRepo repo;
	
	public List<LibraryModel> getAllLibraries() {
		return repo.findAll();
	}
	
}
