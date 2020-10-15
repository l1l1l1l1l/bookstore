package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("A Farewell to Arms");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Ernest Hemingway");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("Carrie", "Stephen King", 1974, "923-233-9-3", 20.99, new Category("Horror"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    
    @Test
    public void deleteBook() {
    	List<Book> booklist = repository.findByTitle("A Farewell to Arms");
		repository.delete(repository.findByTitle("A Farewell to Arms").get(0));
		assertThat(booklist).hasSize(0);
		
	}
    
}
