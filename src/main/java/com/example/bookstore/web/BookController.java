package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.CategoryRepository;


	@Controller
	public class BookController {
		@Autowired
		private BookRepository repository;
		
		@Autowired
		private CategoryRepository drepository;
		
		@RequestMapping(value="/login")
		public String login() {
			return "login";
		} 
		
		 // RESTful service to get all books
	    @GetMapping(value="/books")
	    public @ResponseBody List<Book> bookListRest() {	
	        return (List<Book>) repository.findAll();
	    }    
	  
	    // RESTful service to get book by id
	    @GetMapping(value="/book/{id}")
	    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {	
	    	return repository.findById(id);
	    }  
		
	    @RequestMapping(value= {"/", "/booklist"})
	    public String bookList(Model model) {	
	        model.addAttribute("books", repository.findAll());
	        return "booklist";
	    }
	    
		@RequestMapping(value = "/add")
	    public String addBook(Model model){
	    	model.addAttribute("book", new Book());
	    	model.addAttribute("categorys", drepository.findAll());
	        return "addbook";
	    }
		
		@PostMapping(value = "/save")
	    public String save(Book book){
			repository.save(book);
	        return "redirect:booklist";
	    }
		
	    @GetMapping(value = "/delete/{id}")
	    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    	repository.deleteById(bookId);
	        return "redirect:../booklist";
	    }
	    
	    @GetMapping(value= "/edit/{id}")
	    public String addBook(@PathVariable("id") Long bookId,Model model) {
	        model.addAttribute("book", repository.findById(bookId));
	        model.addAttribute("categorys", drepository.findAll());
	        return "editbook";
	    
	    }
		
}
