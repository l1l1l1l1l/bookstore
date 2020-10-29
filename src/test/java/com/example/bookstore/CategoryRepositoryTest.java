package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void findByName() {
		List<Category> categoryList = repository.findByName("Sci-Fi");
		
		assertThat(categoryList).hasSize(1);
		assertThat(categoryList.get(0).getName()).isEqualTo("Sci-Fi");
	}
	
	@Test
	public void createCategory() {
		Category category = new Category("Horror");
		repository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
		
	}
	
	@Test
	public void deleteCategory() {
		repository.delete(repository.findByName("Horror").get(0));
		List<Category> categoryList = repository.findByName("Horror");
		assertThat(categoryList).hasSize(0);
	
	}
}
