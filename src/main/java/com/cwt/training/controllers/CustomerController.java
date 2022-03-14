package com.cwt.training.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwt.training.dto.CustomerCreateDTO;
import com.cwt.training.dto.CustomerDTO;
import com.cwt.training.dto.CustomerEmailOnlyDTO;
import com.cwt.training.dto.CustomerFirstNameOnlyDTO;
import com.cwt.training.dto.CustomerLastNameOnlyDTO;
import com.cwt.training.dto.CustomerLocationOnlyDTO;
import com.cwt.training.services.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Operation(summary = "Get customer by id")
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable(required = true) Integer customerId) {	
		return ResponseEntity.ok(customerService.findCustomerById(customerId));
	}
	
	@Operation(summary = "Get all customer")
	@GetMapping("/all")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAll());
	}
	
	@Operation(summary = "Create a customer", security = @SecurityRequirement(name = "basicAuth"))
	@PostMapping("/create")
	public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerCreateDTO dto) {		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(customerService.createCustomer(dto));
	}
	
	@Operation(summary = "Update customer")
	@PutMapping("/update/{customerId}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer customerId, @Valid @RequestBody CustomerDTO dto) {
		return ResponseEntity.ok(customerService.updateCustomer(customerId, dto));
	}
	
	@Operation(summary = "Update customer first name")
	@PatchMapping("/update/{customerId}/firstName")
	public ResponseEntity<CustomerDTO> updateCustomerFirstName(@PathVariable Integer customerId, @Valid @RequestBody CustomerFirstNameOnlyDTO dto) {
		return ResponseEntity.ok(customerService.updateCustomerFirstName(customerId, dto));
	}
	
	@Operation(summary = "Update customer last name")
	@PatchMapping("/update/{customerId}/lastName")
	public ResponseEntity<CustomerDTO> updateCustomerLastName(@PathVariable Integer customerId, @Valid @RequestBody CustomerLastNameOnlyDTO dto) {
		return ResponseEntity.ok(customerService.updateCustomerLastName(customerId, dto));
	}
	
	@Operation(summary = "Update customer email")
	@PatchMapping("/update/{customerId}/email")
	public ResponseEntity<CustomerDTO> updateCustomerEmail(@PathVariable Integer customerId, @Valid @RequestBody CustomerEmailOnlyDTO dto) {
		return ResponseEntity.ok(customerService.updateCustomerEmail(customerId, dto));
	}
	
	@Operation(summary = "Update customer location")
	@PatchMapping("/update/{customerId}/location")
	public ResponseEntity<CustomerDTO> updateCustomerLocation(@PathVariable Integer customerId, @Valid @RequestBody CustomerLocationOnlyDTO dto) {
		return ResponseEntity.ok(customerService.updateCustomerLocation(customerId, dto));
	}
	
	@Operation(summary = "Delete a customer")
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Integer> deleteCustomer(@PathVariable Integer customerId) {
		return ResponseEntity.ok(customerService.deleteCustomerById(customerId));
	}
	
}
