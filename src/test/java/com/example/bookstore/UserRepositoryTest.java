package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstore.model.User;
import com.example.bookstore.model.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
	@Test
	public void findByUsernameShouldReturnUser() {
		User user = repository.findByUsername("admin");
		assertThat(user).isNotNull();
		assertThat(user.getRole()).isEqualTo("ADMIN");
	}
	
	@Test
	public void createUser() {
		User user = new User("jorma", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "jorma@gmail.com");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		repository.delete(repository.findByUsername("user"));
		User user = repository.findByUsername("user");
		assertThat(user).isNull();
	}
}
