package com.cwt.training;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cwt.training.dto.CustomerCreateDTO;
import com.cwt.training.dto.CustomerDTO;
import com.cwt.training.entities.Customer;
import com.cwt.training.entities.Order;
import com.cwt.training.exceptions.NotFoundException;
import com.cwt.training.persistence.CustomerRepository;
import com.cwt.training.services.CustomerService;

@DisplayName("Test Customer Service")
@SpringBootTest
class ServiceTests {

	@MockBean
	private CustomerRepository customerRepository;
	
	@Spy
	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	@DisplayName("Create new customer test")
	void customerServiceTestCreateCustomer() {
		CustomerCreateDTO createDTO = new CustomerCreateDTO();
		createDTO.setFirstName("jon");
		createDTO.setLastName("snow");
		createDTO.setEmail("jon@snow.com");
		createDTO.setLocation("tokyo");
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(1);
		customerDTO.setFirstName("jon");
		customerDTO.setLastName("snow");
		customerDTO.setEmail("jon@snow.com");
		customerDTO.setLocation("tokyo");
		
		Customer customer = new Customer();
		customer.setId(1);
		customer.setFirstName("jon");
		customer.setLastName("snow");
		customer.setEmail("jon@snow.com");
		customer.setLocation("tokyo");
		
		Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		
		CustomerDTO createdCustomerDTO = customerService.createCustomer(createDTO);
		
		assertNotNull(createdCustomerDTO);
		assertEquals(createDTO.getFirstName(), createdCustomerDTO.getFirstName());
		assertEquals(createDTO.getLastName(), createdCustomerDTO.getLastName());
		assertEquals(createDTO.getEmail(), createdCustomerDTO.getEmail());
		assertEquals(createDTO.getLocation(), createdCustomerDTO.getLocation());
	}

	@DisplayName("Test get all customers")
	@Test
	void customerServiceTestFindAllCustomer() {
		List<Customer> dtoList = List.of(
				new Customer(1, "jon", "snow", "jon@mail.com", "tokyo", null),
				new Customer(2, "michael", "scott", "michael@mail.com", "new york", null)
		);
		
		Mockito.when(customerRepository.findAll()).thenReturn(dtoList);
		
		assertEquals(dtoList.size(), customerService.getAll().size());
	}

	@DisplayName("Test update to non existent customer")
	@Test
	void customerServiceTestUpdateNonExistentCustomer() {
		CustomerDTO dto = new CustomerDTO();
		dto.setId(2);
		
		Mockito.when(customerRepository.findById(2)).thenReturn(Optional.empty());
		
		assertThrows(NotFoundException.class, () -> customerService.updateCustomer(2, dto));
	}
	
	@DisplayName("Test update customer")
	@Test
	void customerServiceTestUpdateCustomer() {
		CustomerDTO dto = new CustomerDTO();
		dto.setId(1);
		dto.setFirstName("jon");
		dto.setLastName("snow");
		dto.setEmail("jon@mail.com");
		dto.setLocation("winterfell");
		
		Customer customer = new Customer();
		customer.setId(1);
		customer.setFirstName("jon");
		customer.setLastName("snow");
		customer.setEmail("jon@mail.com");
		customer.setLocation("winterfell");
		
		Customer customerOld = new Customer();
		customerOld.setId(1);
		
		Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customerOld));
		Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		
		assertDoesNotThrow(() -> customerService.updateCustomer(1, dto));
		
		CustomerDTO updatedDto = customerService.updateCustomer(1, dto);
		
		assertEquals(dto.getEmail(), updatedDto.getEmail());
	}
	
	@DisplayName("Test delete customer")
	@Test
	void customerServiceTestDeleteCustomer() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setOrders(Set.of(new Order(12, customer, "Test order", 1 , 1)));
		
		Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		
		assertDoesNotThrow(() -> customerService.deleteCustomerById(1));
		assertEquals(1, customerService.deleteCustomerById(1));
	}
}
