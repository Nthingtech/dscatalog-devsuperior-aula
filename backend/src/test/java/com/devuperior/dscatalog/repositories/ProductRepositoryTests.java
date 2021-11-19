package com.devuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devuperior.dscatalog.entities.Product;
import com.devuperior.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repository;
	
	private long existingId;
	private long noExistingId;
	private long countTotalProducts;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		noExistingId = 1000l;
		countTotalProducts = 25L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		
		Product product = Factory.createProduct();
		product.setId(null);
		
		product = repository.save(product);
		
		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProducts + 1, product.getId());
		
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
	
	@Test
	public void findByIdShouldReturnNotNullWhenIdExists() {
		
		
		repository.findById(existingId);
		
		Optional<Product> result = repository.findById(existingId);	
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnNullWhenIdNotExists() {
		
		
		repository.findById(noExistingId);
		
		Optional<Product> result = repository.findById(noExistingId);	
		Assertions.assertTrue(result.isEmpty());
	}
		
}
