package com.example;

import java.util.*;
import org.springframework.*;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository Interface
public interface ReadingListRepository extends JpaRepository <Book, Long>{
	List <Book> findByReader(String reader);
	//Adding more functions to interact with DB
	List <Book> findByTitle(String title);
}
