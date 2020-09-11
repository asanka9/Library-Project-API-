package com.example.demo.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BookModel;

@Repository
public interface BookModelRepo extends JpaRepository<BookModel, String> {
	
	@Query("FROM Booktable  WHERE libraryid =?1 AND name =?2 AND authorname =?3")
	List<BookModel> findWithBookNameBookAuthor(String library_id,String book_name,String book_author);
	
	
	@Query("FROM Booktable  WHERE libraryid =?1 AND name =?2")
	List<BookModel> findByBookByBookName(String library_id,String book_name);
	
	@Query("FROM Booktable  WHERE libraryid =?1 AND authorname =?2")
	List<BookModel> findWithBookAuthor(String library_id,String book_author);
	
	List<BookModel> findByLibraryid(String libraryid);
	
	@Query("FROM Booktable  WHERE libraryid =?1 AND categoryid =?2")
	List<BookModel> findWithBookCategory(String library_id,String categoryid);

	
	
	/*
	 * 	private String id;
	private String bookCode; //genarate with library
	private String name;
	private String authorname;
	private String blocation;
	private double price;
	private String categoryid;
	private String libraryid;
	private String borrowed;
	 * 
	 * */
	
	

	

}
