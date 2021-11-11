package com.devuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devuperior.dscatalog.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repository;
	
	private long existingId;
	private long noExistingId;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		noExistingId = 1000l;
		
	}
	
	@Test
	public void deletShouldDeletObjectWhenExists() {
		
		
		
		repository.deleteById(existingId);
		
		Optional<Product> result = repository.findById(existingId);	
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAcessExceptionWhenIdDoesNotExist() {
		
		
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(noExistingId);
		});
	}
		
}
