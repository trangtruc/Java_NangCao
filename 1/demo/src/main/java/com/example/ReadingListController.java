package com.example;

import java.util.List;

import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ReadingListController {
	private ReadingListRepository readingListRepository;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository){
		this.readingListRepository = readingListRepository;
	}
	
	//Method 1: readerBooks(): dam nhan nhiem vu xu ly cac HTTP Get
	@RequestMapping(value="/{reader}", method=RequestMethod.GET)
	public String readerBooks(
		@PathVariable("reader") String reader, Model model)
		{
			//Viet cach thuc lay ra sach theo ten tac gia
			List <Book> readingList = readingListRepository.findByReader(reader);
			if(readingList!=null){
				model.addAttribute("books",readingList);
			}
			return "readingList";
		}
		
	//method 2: addToReadingList(): dam nhan nhiem vu xu ly cac HTTP Post
	@RequestMapping(value="/{reader}", method=RequestMethod.POST)
	public String addToReadingList(
			@PathVariable("reader") String reader, Book book)
	{
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/{reader}";
	}
}


