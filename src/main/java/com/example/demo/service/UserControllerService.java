package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.BookModel;
import com.example.demo.model.LibraryModel;
import com.example.demo.model.OrderModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.config.DateCounter;
import com.example.demo.model.repo.BookModelRepo;
import com.example.demo.model.repo.LibraryModelRepo;
import com.example.demo.model.repo.OrderModelRepo;
import com.example.demo.model.repo.UserModelRepo;

@Service
public class UserControllerService {
	
	@Autowired
	UserModelRepo repo;
	
	@Autowired
	OrderModelRepo  orderRepo;
	
	@Autowired
	LibraryModelRepo libRepo;
	
	
	public String getLibraryname(String libraryId) {
		LibraryModel model= libRepo.findById(libraryId).orElse(null);
		return model.getName();
	}
	
	public UserModel updateUserDetails(String id,UserModel newModel) {
		UserModel model=repo.findById(id).orElse(new UserModel());
		model.setAddress(newModel.getAddress());
		model.setName(newModel.getName());
		model.setNic(newModel.getNic());
		model.setPassword(newModel.getPassword());
		model.setId(newModel.getId());
		model.setMnumber(newModel.getMnumber());
		return model;
	}
	/*
	public List<OrderModel> listOfBorrowedBooks(String  id){
		List<OrderModel> myModelList = orderRepo.findByUserid(id);
		myModelList.stream().forEach(x->mapping(x, x.getDate02()));
		Comparator<OrderModel> com = new Comparator<OrderModel>() {
			@Override
			public int compare(OrderModel o1, OrderModel o2) {
				if (o1.getCountdays()<o2.getCountdays()) {
					return 1;
				} else {
					return -1;
				}
			}
		};
		Collections.sort(myModelList, com);
		
		return myModelList;
	}
	
	*/
	
	private void mapping(OrderModel model,String date) {
		model.setCountdays(DateCounter.DateCounterMethod(date));
	}
	
	public List<LibraryModel> addLibraryToExternalUserAccount(String userId,String userPassword,UserModel model){
		
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHKKKKKKKKKKKKKKKKKKKKKKKK");
		System.out.println("KKKKKKKKKKKKJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
		
		if (model.getLid()==null || model.getLid().equals("")) {
			System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			UserModel uModel = repo.findById(userId).orElse(null);
			if (uModel==null) {
				throw new BadRequestException("");
			} else {
				if (((uModel.getPassword()).equals(userPassword))&&((uModel.getRole()).equals("USER"))) {
					String temp [] = userId.split(" ");
					model.setLid(temp[0]);
					model.setNic(temp[1]); 
					repo.deleteById(model.getId());
					repo.save(model);
					return getListOfLibraries(model.getLid());
				} else {
					throw new BadRequestException("");
				}
			}
		}else {
			System.out.println("sasasasas");
			System.out.println("||||||||||||| --------"+model.getLid());
			UserModel uModel = repo.findById(userId).orElse(null);
			if (uModel==null) {
				throw new BadRequestException("");
			} else {
				if (((uModel.getPassword()).equals(userPassword))&&((uModel.getRole()).equals("USER"))) {
					String temp [] = userId.split(" ");
					model.setLid(model.getLid()+" "+temp[0]);
					model.setNic(model.getNic()+" "+temp[1]);
					repo.deleteById(model.getId());
					repo.save(model);
					return getListOfLibraries(model.getLid());
				} else {
					throw new BadRequestException("");
				}
			}
		}
		
	}
	
	
	public List<LibraryModel> getListOfLibraries(String lids){
		String temp [] = lids.split(" ");

		List<LibraryModel> libList = new ArrayList<LibraryModel>();
		for (String string : temp) {	
			
				System.out.println(temp);
				libList.add(libRepo.findById(string).orElse(null));
			
		}
		System.out.println("####  "+libList);
		System.out.println("Size  "+libList.size());
		return libList;
		
	}
	
	public List<OrderModel> returnAllBooks(UserModel model){
		String lids = model.getLid();
		String unames = model.getNic();
		String lidsArray [] = lids.split(" ");
		String unamesArray [] = unames.split(" ");
		List<OrderModel> myList = new ArrayList<OrderModel>();
		for (int i = 0; i < unamesArray.length; i++) {
			
			String temp = lidsArray[i] +" "+unamesArray[i];
			System.out.println("===============================================  "+temp);
			List<OrderModel> tempLis = orderRepo.findByUserid(temp);
			for (int j = 0; j < tempLis.size(); j++) {
				myList.add(j, tempLis.get(j));
			}
			
		}
		
		List<OrderModel> myModelList = myList;
		myModelList.stream().forEach(x->mapping(x, x.getDate02()));
		Comparator<OrderModel> com = new Comparator<OrderModel>() {
			@Override
			public int compare(OrderModel o1, OrderModel o2) {
				if (o1.getCountdays()<o2.getCountdays()) {
					return 1;
				} else {
					return -1;
				}
			}
		};
		Collections.sort(myModelList, com);
		return myModelList;
		
	}
	
	public UserModel returnuserDetails(String userDetails) {
		return repo.findById(userDetails).orElse(null);
	}
	

	
	public List<LibraryModel> addLibraryToExternalUserAccount01(String userId,String userPassword,UserModel model){
		
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHKKKKKKKKKKKKKKKKKKKKKKKK");
		System.out.println("KKKKKKKKKKKKJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
		
		if (model.getLid().equals(userId)) {
			System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			UserModel uModel = repo.findById(userId).orElse(null);
			if (uModel==null) {
				throw new BadRequestException("");
			} else {
				if (((uModel.getPassword()).equals(userPassword))&&((uModel.getRole()).equals("USER"))) {
					String temp [] = userId.split(" ");
					model.setLid(temp[0]);
					model.setNic(temp[1]); 
					repo.deleteById(model.getId());
					repo.save(model);
					return getListOfLibraries(model.getLid());
				} else {
					throw new BadRequestException("");
				}
			} 
		}else {
			System.out.println("||||||||||||| --------"+userId);
			System.out.println("||||||||||||| --------"+model.getLid());
			UserModel uModel = repo.findById(userId).orElse(null);
			if (uModel==null) {
				throw new BadRequestException("");
			} else {
				if (((uModel.getPassword()).equals(userPassword))&&((uModel.getRole()).equals("USER"))) {
					String temp [] = userId.split(" ");
					model.setLid(model.getLid()+" "+temp[0]);
					model.setNic(model.getNic()+" "+temp[1]);
					repo.deleteById(model.getId());
					repo.save(model);
					return getListOfLibraries(model.getLid());
				} else {
					throw new BadRequestException("");
				}
			}
		}
		
	}
	
	/*
	 * 
	 * I/flutter ( 4599): http://10.0.2.2:8080/mobile/
	 * addLibrary/RJP4 user4/asanka96/abc/93-hakuruwela/Asanka Gayshan/aaa/external/0703962577/RJP4 user4/hakuruwela/asanka96/male

	 * 
	 * */
	
}
