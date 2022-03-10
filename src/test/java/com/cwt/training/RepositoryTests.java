package com.cwt.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.cwt.training.entities.Customer;
import com.cwt.training.persistence.CustomerRepository;

@DataJpaTest
class RepositoryTests {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@BeforeEach
	void init() {
		testEntityManager.persist(new Customer(null, "jon", "snow", "jon@mail.com", "tokyo", null));
	}
	
	@DisplayName("Test Customer Repository Find by First name")
	@Test
	void testCustomerRepositoryFindByFirstName() {
		Optional<Customer> optCustomer = customerRepository.findByFirstName("jon");
		
		assertTrue(optCustomer.isPresent());
		assertEquals("jon", optCustomer.get().getFirstName());
	}
	
}
