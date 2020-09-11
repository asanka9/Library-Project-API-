package com.example.demo.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
public interface UserModelRepo extends JpaRepository<UserModel, String> {

	UserModel findByName(String name);
	Optional<UserModel> findById(String id);
	
	
}
