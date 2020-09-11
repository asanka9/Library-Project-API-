package com.example.demo.controller;

import java.util.List;

import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BookModel;
import com.example.demo.model.CategoryModel;
import com.example.demo.model.LibraryModel;
import com.example.demo.model.OrderModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.MobileService;
import com.example.demo.service.SuperUserService;
import com.example.demo.service.UserControllerService;


@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/mobile")
public class MobileController {
	
	@Autowired
	MobileService service;
	
	@Autowired
	UserControllerService uService;
	

	
	@GetMapping("login/{id}/{password}")
	public UserModel verifyUser(@PathVariable String id,@PathVariable String password) {
		return service.verifyExternalUser(id, password);
	}
	
	@GetMapping("returnBooks/{libraryIds}/{userNames}")
	public List<OrderModel> returnAllBooks(@PathVariable String libraryIds,@PathVariable String userNames){
		return service.returnAllBorrowedBooks(libraryIds, userNames);
	}
	/*      "id":id,
     "email":email,
     "name":name,
     "password":password,
     "role":role,
     "mnumber":mnumber,
     "lid":lid,
     "address":address,
     "nic":nic,
     "gender":gender
	 * 
	 * 
	 * */
	@GetMapping("addLibrary/{uid}/{upas}/{id}/{email}/{name}/{password}/{role}/{mnumber}/{lid}/{address}/{nic}/{gender}")
	public List<LibraryModel> addLibrary(@PathVariable String uid,@PathVariable String upas,@PathVariable String id,@PathVariable String email,
			@PathVariable String name,@PathVariable String password,@PathVariable String role,@PathVariable String mnumber,@PathVariable String lid,
			@PathVariable String address,@PathVariable String nic,@PathVariable String gender
			){
		System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCBBBBBBBBBBBBBBBBBBNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
		UserModel model = new UserModel();
		model.setId(id);
		model.setAddress(address);
		model.setEmail(email);
		model.setGender(gender);
		model.setMnumber(mnumber);
		model.setName(name);
		model.setPassword(password);
		model.setLid(lid);
		model.setNic(nic);
		model.setRole(role);
		System.out.println("HHHHHHHHHHHHHHJJJJJJJJJJJJJKKKKKKKKKKKKKKK");
		return uService.addLibraryToExternalUserAccount01(uid, upas, model);
	}
	
	@DeleteMapping("deleteLibrary/{userID}/{deleteLid}/{lids}/{uids}")
	public List<LibraryModel> deleteLibrary(@PathVariable String userID,@PathVariable String deleteLid,@PathVariable String lids,@PathVariable String uids){
		
		return service.deleteSelectedLibrary(userID, deleteLid, lids, uids);
	}
	
	@GetMapping("create/{name}/{email}/{password}")
	public UserModel createUser(@PathVariable String name,@PathVariable String email,@PathVariable String password) {
		return service.createMobileExternalUser(name, email, password);
	}
	
	@GetMapping("searchAnyBook/{userName}/{bookName}")
	public List<BookModel> returnAlBooks(@PathVariable String userName,@PathVariable String bookName) {
		System.out.println("ASAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		return service.returnAllBooks(userName, bookName);
	}
	
	@GetMapping("update/{name}/{email}/{address}/{password}")
	public UserModel updateUserDetails(@PathVariable String name,@PathVariable String email,@PathVariable String address,@PathVariable String password) {
		System.out.println("AAAAAAAAAAAAAAAAA NNNNNNNNNNNNNNNNN BBBBBBBBBBBBBBBB MMMMMMMMMMMMM");
		return service.updateUserDetails(name, email, password, address);
	}
	
	


}
