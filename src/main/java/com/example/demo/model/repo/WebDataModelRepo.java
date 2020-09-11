package com.example.demo.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.WebDataModel;

@Repository
public interface WebDataModelRepo extends JpaRepository<WebDataModel, Integer> {

}
