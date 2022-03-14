package com.cwt.training;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.cwt.training.dto.CustomerCreateDTO;
import com.cwt.training.dto.CustomerDTO;
import com.cwt.training.exceptions.NotFoundException;
import com.cwt.training.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"ADMIN"})
class ControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;
	
	@WithAnonymousUser
	@Test
	@DisplayName("Get customer without authentication")
	void controllerTestGetCustomerWithoutAuth() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("jon");
		customerDTO.setLastName("snow");
		customerDTO.setEmail("jon@mail.com");
		customerDTO.setLocation("tokyo");
		customerDTO.setId(1);
		
		Mockito.when(customerService.findCustomerById(1)).thenReturn(customerDTO);
		
		mockMvc.perform(get("/customers/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName").value(customerDTO.getFirstName()));
	}
	
	@Test
	@DisplayName("Test create customer")
	void controllerTestCreateCustomer() throws Exception {
		CustomerCreateDTO createDTO = new CustomerCreateDTO();
		createDTO.setFirstName("jon");
		createDTO.setLastName("snow");
		createDTO.setEmail("jon@mail.com");
		createDTO.setLocation("tokyo");
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("jon");
		customerDTO.setLastName("snow");
		customerDTO.setEmail("jon@mail.com");
		customerDTO.setLocation("tokyo");
		customerDTO.setId(1);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Mockito.when(customerService.createCustomer(createDTO)).thenReturn(customerDTO);
		
		mockMvc.perform(
				post("/customers/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(createDTO)) 
					.accept(MediaType.APPLICATION_JSON)
				)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstName").value(createDTO.getFirstName()));
	}
	
	@Test
	@DisplayName("Test validation on customer update")
	void controllerTestValidationOnCustomerUpdate() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("asd");
		customerDTO.setLastName("snow");
		customerDTO.setEmail("jon@mail.com");
		customerDTO.setLocation("tokyo");
		customerDTO.setId(1);
		
		Mockito.when(customerService.updateCustomer(1, customerDTO)).then(
				inv -> { throw new NotFoundException("Customer not found");}
		);
		
		mockMvc.perform(
				put("/customers/update/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(customerDTO)) 
					.accept(MediaType.APPLICATION_JSON)
				)
			.andExpect(status().isNotFound());
		
	}
	
	@Test
	@DisplayName("Test customer update")
	void controllerTestCustomerUpdate() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("asd");
		customerDTO.setLastName("snow");
		customerDTO.setEmail("jon@mail.com");
		customerDTO.setLocation("tokyo");
		customerDTO.setId(1);
		
		Mockito.when(customerService.updateCustomer(1, customerDTO)).thenReturn(customerDTO);
		
		mockMvc.perform(
				put("/customers/update/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(customerDTO)) 
					.accept(MediaType.APPLICATION_JSON)
				)
			.andExpect(status().isOk());
		
	}
	
	
	@Test
	@DisplayName("Test customer delete")
	void controllerTestCustomerDelete() throws Exception {
		Mockito.when(customerService.deleteCustomerById(1)).thenReturn(1);
		
		mockMvc.perform(delete("/customers/1"))
			.andExpect(status().isOk());
		
	}
	
	@WithAnonymousUser
	@Test
	@DisplayName("Test delete customer without authentication")
	void controllerTestDeleteCustomerWithoutAuth() throws Exception {
		Mockito.when(customerService.deleteCustomerById(1)).thenReturn(1);
		
		mockMvc.perform(delete("/customers/1"))
			.andExpect(status().isUnauthorized());
		
	}
	
}
