package com.example.demo.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.BookModel;
import com.example.demo.model.CategoryModel;
import com.example.demo.model.LibraryModel;
import com.example.demo.model.OrderModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.config.DateCounter;
import com.example.demo.model.config.GenarateLibraryCode;
import com.example.demo.model.config.RandomPassword;
import com.example.demo.model.repo.BookModelRepo;
import com.example.demo.model.repo.CategoryModelRepo;
import com.example.demo.model.repo.LibraryModelRepo;
import com.example.demo.model.repo.OrderModelRepo;
import com.example.demo.model.repo.UserModelRepo;

@Service
public class SuperUserService {
	
	@Autowired
	UserModelRepo userRepo;
	
	@Autowired
	LibraryModelRepo libraryRepo;
	
	@Autowired
	BookModelRepo bookRepo;
	
	@Autowired
	CategoryModelRepo categoryRepo;
	
	@Autowired
	OrderModelRepo orderRepo;
	
	
	public UserModel createLibraryAndSuperUser (String libraryname,UserModel  model){
		
		UserModel uModel = model; 
		System.out.println(uModel);
		uModel.setEmail(uModel.getId());
		uModel.setId(GenarateLibraryCode.libraryCodeGenaratorMethod(libraryname, libraryRepo.findAll().size()) +" "+uModel.getName());
		uModel.setRole("SUPERUSER");
		uModel.setPassword(RandomPassword.RandomPasswordMethod());
		
		LibraryModel lModel = new LibraryModel() ;
		lModel.setName(libraryname);
		lModel.setAddress(uModel.getAddress());
		lModel.setEmail("--");
		lModel.setTel("--");
		
		lModel.setId(GenarateLibraryCode.libraryCodeGenaratorMethod(libraryname, libraryRepo.findAll().size()));
		uModel.setLid(GenarateLibraryCode.libraryCodeGenaratorMethod(libraryname, libraryRepo.findAll().size()));
		userRepo.save(uModel);
		libraryRepo.save(lModel);
		return model;
	}
	
	public UserModel createSuperUser(String libraryId,UserModel uModel) {
		UserModel fModel = userRepo.findById(
				libraryId+" "+uModel.getName()
				).orElse(null);
		if (fModel==null) {
			uModel.setId(libraryId+" "+uModel.getName());
			uModel.setRole("SUPERUSER");
			uModel.setLid(libraryId);
			uModel.setPassword(RandomPassword.RandomPasswordMethod());
			userRepo.save(uModel);
			return uModel;
		} else { 
			return null;
		}

	}
	
	public UserModel createUser(String libraryId,UserModel uModel) {
		UserModel fModel = userRepo.findById(
				libraryId+" "+uModel.getName()
				
				).orElse(null);
		if (fModel==null) {
			uModel.setId(libraryId+" "+uModel.getName());
			uModel.setRole("USER");
			uModel.setLid(libraryId);
			uModel.setPassword(RandomPassword.RandomPasswordMethod());
			userRepo.save(uModel);
			return uModel;
		} else {
			return null;
		}
	}
	
	public UserModel updateUsers(UserModel newModel) {
		UserModel oldModel = userRepo.findById(newModel.getId()).orElse(null);
		if ((newModel.getName()).equals(oldModel.getName())) {
			userRepo.deleteById(newModel.getId());
			userRepo.save(newModel);
			return newModel;
		} else {
			userRepo.deleteById(newModel.getId());
			if (newModel.getRole().equals("external")) {
				newModel.setId(newModel.getName()); 
			} else {
				newModel.setId(newModel.getLid()+" "+newModel.getName());
			}
			
			UserModel temp = userRepo.findById(newModel.getId()).orElse(null);
			if (temp!=null) {
				throw new BadRequestException("Already have");
			}
			userRepo.save(newModel);
			return newModel;
		}
	}
	
	
	
	public List<UserModel> deleteUsers(String userId) {
		userRepo.deleteById(userId);
		return userRepo.findAll();
	}
	
	public List<BookModel> searchBook(String libraryId,String bookName,String bookAuthor){
		
		System.out.println(bookName);
		System.out.println(bookAuthor);
		
		
		if (bookAuthor.equals("undefined")) {
			return bookRepo.findByBookByBookName(libraryId, bookName);
		} else if(bookName.equals("undefined")) {
			return bookRepo.findWithBookAuthor(libraryId, bookAuthor);
		}else {
			return bookRepo.findWithBookNameBookAuthor(libraryId, bookName, bookAuthor);
		}
		
	}
	
	public List<BookModel> returnAllBooks(String libraryId){
		return bookRepo.findByLibraryid(libraryId);
	}

	public List<BookModel> returnWithCategory(String libraryId,String categoryName){
		return bookRepo.findWithBookCategory(libraryId, categoryName);
	}
	
	public List<BookModel> createBook(String categoryId,BookModel model){
		model.setId(model.getLibraryid()+" "+model.getName());
		BookModel mo=bookRepo.findById(model.getId()).orElse(null);
		if (mo!=null) {
			throw  new BadRequestException("Book Name Already Taken");
		} 
		model.setCategoryid(categoryId);
		model.setBorrowed("YES");
		CategoryModel catModel = categoryRepo.findById(model.getLibraryid()+" "+categoryId).orElse(null);
		categoryRepo.deleteById(model.getLibraryid()+" "+categoryId);
		catModel.setNumofbooks(catModel.getNumofbooks()+1);
		categoryRepo.save(catModel);
		bookRepo.save(model);
		return  bookRepo.findWithBookCategory(model.getLibraryid(), categoryId);
	}
	
	public List<BookModel> updateBook(String categoryName,BookModel model){
		BookModel old = bookRepo.findById(model.getId()).orElse(new BookModel());
		if ((model.getName().equals(old.getName()))) {
			old = model;
			bookRepo.deleteById(model.getId());
			bookRepo.save(old);
			return bookRepo.findWithBookCategory(model.getLibraryid(), categoryName);
		}
		else {
			bookRepo.deleteById(model.getId());
			model.setId(model.getLibraryid()+" "+model.getName());
			if (bookRepo.findById(model.getId()).orElse(null)==null) {
				bookRepo.save(model);
				return bookRepo.findWithBookCategory(model.getLibraryid(), categoryName);
			} else {
				throw new BadRequestException("Book Name Already Taken");
			}


		}

	}
	
	public List<BookModel> deleteBook(String categoryName,BookModel  model){
		
		bookRepo.deleteById(model.getId());
		CategoryModel catModel = categoryRepo.findById(model.getLibraryid()+" "+categoryName).orElse(null);
		categoryRepo.deleteById(model.getLibraryid()+" "+categoryName);
		catModel.setNumofbooks(catModel.getNumofbooks()-1);
		categoryRepo.save(catModel);
		return bookRepo.findWithBookCategory(model.getLibraryid(), categoryName);

	}
	
	public List<CategoryModel> createCategory(CategoryModel model){
		model.setId(model.getLibraryid()+" "+model.getCategoryname());
		categoryRepo.save(model);
		return categoryRepo.findAll();

	}
	
	public List<CategoryModel> updateCategory(String categoryId,CategoryModel model){
		CategoryModel old=categoryRepo.findById(categoryId).orElse(new CategoryModel());
		model.setId(model.getLibraryid()+" "+model.getCategoryname());
		old=model;
		return categoryRepo.findAll();
		

	}
	
	public LibraryModel updateLibrary(LibraryModel model) {
		libraryRepo.deleteById(model.getId());
		libraryRepo.save(model);
		return model;

	}
	
	public List<CategoryModel> returnAllCategories(String libraryId){
		return categoryRepo.findByLibraryid(libraryId);
	}
	
	public List<OrderModel> returnBorrowedBooks(String l){
		return orderRepo.findByLibraryid(l);
	}
	
	

	
	public List<OrderModel> listOfBorrowedBooks(String  id){
		List<OrderModel> myModelList = orderRepo.findByLibraryid(id);
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
	
	public LibraryModel getLibraryDetails(String id) {
		return libraryRepo.findById(id).orElse(null);
	}
	
	public UserModel getUserDetails(String id) {
		UserModel model= userRepo.findById(id).orElse(null);
		if (model==null) {
			throw new BadRequestException("Can not Find User");
		} else {
			return model;
		}
	}
	
	public String borrowedBook(String lid,String date,String uid,String bname) {
		OrderModel model = new OrderModel();
		UserModel uModel = userRepo.findById(uid).orElse(null);
		if (uModel==null) {
			throw new BadRequestException("No User");
		}
		String authorName = (bookRepo.findById(lid+" "+bname).orElse(null)).getAuthorname();
		if (authorName==null) {
			throw new BadRequestException("No User");
		}
		model.setAuthorname(authorName);
		model.setId(lid+" "+uid+" "+bname);
		model.setDate02(date);
		ZoneId zone = ZoneId.of("Asia/Colombo");
		LocalDate today = LocalDate.now(zone);
		String currentDate = today.toString();
		model.setDate01(currentDate);
		model.setBookname(bname);
		model.setLibraryid(lid);
		model.setUserid(uid);
		orderRepo.save(model);
		return "";
	}
	
	public String deleteBookFromBorrowedBook(String libraryId,String uId,String bookName) {
		orderRepo.deleteById(libraryId+" "+uId+" "+bookName);
		return "Successs";
	}
	
	public List<CategoryModel> createNewCategory (CategoryModel model){
		model.setId(model.getLibraryid()+" "+model.getCategoryname());
		model.setNumofbooks(0);
		CategoryModel model2 = categoryRepo.findById(model.getId()).orElse(null);
		if (model2!=null) {
			throw new BadRequestException("Already have category name");
		}
		categoryRepo.save(model);
		return categoryRepo.findByLibraryid(model.getLibraryid());
	}
	
	
	
	private void mapping(OrderModel model,String date) {
		model.setCountdays(DateCounter.DateCounterMethod(date));
	}
	
	
	public String getLocation(String lid) {
		LibraryModel model = libraryRepo.findById(lid).orElse(null);
		return model.getGooglelocation();
	}
	
	public String updateLocaton(String lid,String googlelocation) {
		LibraryModel model = libraryRepo.findById(lid).orElse(null);
		libraryRepo.delete(model);
		model.setGooglelocation(googlelocation);
		libraryRepo.save(model);
		return null;
	}
	


}
