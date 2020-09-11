package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.BookModel;
import com.example.demo.model.CategoryModel;
import com.example.demo.model.LibraryModel;
import com.example.demo.model.OrderModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.SuperUserService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/superUser")
public class SuperUserController {
	
	@Autowired
	SuperUserService service;
	
	@PostMapping("/addLibraryAndUser/{libraryName}")
	public UserModel createSuperUserAndLibrary(@PathVariable String libraryName,@RequestBody UserModel model ) {
		UserModel newModel= service.createLibraryAndSuperUser(libraryName, model);
		if (newModel==null) {
			throw new BadRequestException("Your User name already taken");
		} else {
			return newModel;
		}
	} 
	
	@PostMapping("/createSuperUser/{libraryId}")
	public UserModel createSuperUser (@PathVariable String libraryId,@RequestBody UserModel model) {
		UserModel newModel= service.createSuperUser(libraryId, model);
		if (newModel==null) {
			throw new BadRequestException("Email Address already taken");
		} else {
			return newModel;
		}
	}
	
	@PostMapping("/createUser/{libraryId}")
	public UserModel createUser (@PathVariable String libraryId,@RequestBody UserModel model) {
		UserModel newModel= service.createUser(libraryId, model);
		if (newModel==null) {
			throw new BadRequestException("Email Address already taken");
		} else {
			return newModel;
		}
	}
	
	@PostMapping("/updateUsers")
	public UserModel updateUsers(@RequestBody UserModel model) {
		return service.updateUsers(model);
	}
	

	@GetMapping("/returnAllBooks/{libraryId}")
	public List<BookModel> returnAllBooks(@PathVariable String libraryId){
		return service.returnAllBooks(libraryId);
	} 
	
	@GetMapping("/returnBorrowedBooks/{libraryId}")
	public List<OrderModel> returnBorrowedBooks(@PathVariable String libraryId){
		return service.listOfBorrowedBooks(libraryId);
	}
	
	
	@GetMapping("/searchBook/{libaryId}/{bookName}/{authorName}")
	public List<BookModel> searchBook(@PathVariable String libaryId,@PathVariable String bookName,@PathVariable String authorName ) {
		List<BookModel> newModel= service.searchBook(libaryId, bookName, authorName);
		if (newModel==null) {
			throw new BadRequestException("Email Address already taken");
		} else {
			return newModel;
		}
	}
	
	@GetMapping("/searchCategoryBook/{categoryName}/{libraryId}")
	public List<BookModel> returnBoookWithCategory(@PathVariable String categoryName,@PathVariable String libraryId){
		return service.returnWithCategory(libraryId, categoryName);
	}
	
	@PostMapping("/createBook/{categoryName}")
	public List<BookModel> createBook(@PathVariable String categoryName ,@RequestBody BookModel model) {
		return service.createBook(categoryName,model);
	}
	
	@PutMapping("/updateBook/{catid}")
	public List<BookModel> updateBook(@PathVariable String catid,@RequestBody BookModel lmodel) {
		//return service.updateBook(bookId, lmodel);
		return service.updateBook(catid, lmodel); 
	}
	
	@PostMapping("/deleteBook/{catId}")
	public List<BookModel> deleteBook(@PathVariable String catId,@RequestBody BookModel model) {
		System.out.println("Deleteeeeeeeeeeeeeeeeeee");
		return service.deleteBook(catId, model);
	}
	
	
	@PutMapping("/updateLibrary")
	public LibraryModel updateLibrary(@RequestBody LibraryModel model) {
		return service.updateLibrary(model);
	}
	
	@GetMapping("/getCategories/{libraryId}")
	public List<CategoryModel> returnAllCategories(@PathVariable  String libraryId){
		System.out.println("Create aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return service.returnAllCategories(libraryId);
	}
	
	@PostMapping("/createCategory")
	public List<CategoryModel> createCategory(@RequestBody CategoryModel model) {
		
		return service.createNewCategory(model);
	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public List<CategoryModel> updateCategory(@PathVariable String categoryID ,@RequestBody CategoryModel model) {
		return service.updateCategory(categoryID, model);
	}
	
	@GetMapping("/getLLibraryDetails/{id}")
	public LibraryModel getLibraryDetails(@PathVariable String id) {
		return service.getLibraryDetails(id);
	}
	
	@GetMapping("/userId/{id}")
	public UserModel getUserDetails(@PathVariable String id) {
		return service.getUserDetails(id);
	}
	
	@GetMapping("/borrowBook/{lid}/{date}/{uid}/{bname}")
	public String borrowedBook(@PathVariable String lid,@PathVariable String date,@PathVariable String uid,@PathVariable String bname) {
		return service.borrowedBook(lid, date, uid, bname);
	}
	
	@DeleteMapping("/removeFromBook/{lid}/{userId}/{bookName}")
	public String removeFromBorrowedList(@PathVariable String lid,@PathVariable String userId,@PathVariable String bookName) {
		return service.deleteBookFromBorrowedBook(lid, userId, bookName);
	}
	
	@GetMapping("getGoogleLocation/{lid}")
	public String getLocation(@PathVariable String lid) {
		return service.getLocation(lid);
	}
	
	@PostMapping("updateGoogleLocation/{lid}/{location}")
	public String updateLocation(@PathVariable String lid,@PathVariable  String location) {
		return service.updateLocaton(lid, location);
	}
}
