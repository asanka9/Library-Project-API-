package com.example.demo.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LibraryModel;

@Repository
public interface LibraryModelRepo extends JpaRepository<LibraryModel, String> {

}
