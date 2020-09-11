package com.example.demo.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderModel;

import java.util.Optional;

@Repository
public interface OrderModelRepo extends JpaRepository<OrderModel, String> {
	
	
	List<OrderModel> findByUserid(String id);
	
	List<OrderModel> findByLibraryid(String libraryid);
	
	@Query("FROM Ordertable  WHERE userid =?1")
	OrderModel findByexUserid(String id);
}
