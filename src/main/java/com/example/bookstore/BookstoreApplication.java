package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;



@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository drepository) {
		return (args) -> {
			
			log.info("save a couple of books");
			drepository.save(new Category("Sci-Fi"));
			drepository.save(new Category("History"));
			drepository.save(new Category("Horror"));
			drepository.save(new Category("Satire"));
			
			repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "923-64842-9-4", 15, drepository.findByName("History").get(0)));
			repository.save(new Book("Animal Farm", "George Orwell", 1945, "553-97593-9-4", 20.99, drepository.findByName("Satire").get(0)));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}