package com.example.demo.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CategoryModel;

@Repository
public interface CategoryModelRepo extends JpaRepository<CategoryModel, String> {
	
	List<CategoryModel> findByLibraryid(String libraryid);
}
