package com.cwt.training.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwt.training.dto.AbstractCustomerDTO;
import com.cwt.training.dto.CustomerCreateDTO;
import com.cwt.training.dto.CustomerDTO;
import com.cwt.training.dto.CustomerEmailOnlyDTO;
import com.cwt.training.dto.CustomerFirstNameOnlyDTO;
import com.cwt.training.dto.CustomerLastNameOnlyDTO;
import com.cwt.training.dto.CustomerLocationOnlyDTO;
import com.cwt.training.entities.Customer;
import com.cwt.training.exceptions.AppException;
import com.cwt.training.exceptions.NotFoundException;
import com.cwt.training.persistence.CustomerRepository;
import com.cwt.training.persistence.OrderRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional(readOnly = true)
	@Override
	public CustomerDTO findCustomerById(Integer id) {
		log.info("finding customer by id " + id);
		Customer customer = getCustomer(id);
		customer.getOrders().forEach(System.out::println);
		return modelMapper.map(getCustomer(id), CustomerDTO.class);
	}

	@Transactional
	@Override
	public CustomerDTO createCustomer(CustomerCreateDTO dto) {
		log.info("creating customer");
		Customer customer = modelMapper.map(dto, Customer.class);
		return modelMapper.map(customerRepository.save(customer), CustomerDTO.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CustomerDTO> getAll() {
		log.info("getting all customer");
		return customerRepository.findAll()
				.stream()
				.map(c -> modelMapper.map(c, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public Integer deleteCustomerById(Integer id) {
		log.info("deleting customer " + id);
		Customer customer = getCustomer(id);
		
		customer.getOrders().forEach(orderRepository::delete);
		
		customerRepository.deleteById(id);
		return id;
	}

	@Transactional
	@Override
	public CustomerDTO updateCustomer(Integer id, CustomerDTO dto) {
		log.info("updating customer");
		return updateCustomerInfo(id, dto);
	}

	@Transactional
	@Override
	public CustomerDTO updateCustomerFirstName(Integer id, CustomerFirstNameOnlyDTO dto) {
		log.info("updating customer first name");
		return updateCustomerInfo(id, dto);
	}

	@Transactional
	@Override
	public CustomerDTO updateCustomerLastName(Integer id, CustomerLastNameOnlyDTO dto) {
		log.info("updating customer last name");
		return updateCustomerInfo(id, dto);
	}

	@Transactional
	@Override
	public CustomerDTO updateCustomerEmail(Integer id, CustomerEmailOnlyDTO dto) {
		log.info("updating customer email");
		return updateCustomerInfo(id, dto);
	}

	@Transactional
	@Override
	public CustomerDTO updateCustomerLocation(Integer id, CustomerLocationOnlyDTO dto) {
		log.info("updating customer location");
		return updateCustomerInfo(id, dto);
	}
	
	private Customer getCustomer(Integer id) {
		return customerRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Customer Not Found")
				);
	}
	
	private CustomerDTO updateCustomerInfo(Integer id, AbstractCustomerDTO dto) {
		checkIfCustomerUpdateIdMatch(id, dto.getId());
		
		Customer customer = getCustomer(id);
		modelMapper.map(dto, customer);
		
		return modelMapper.map(customerRepository.save(customer), CustomerDTO.class);
	}
	
	private void checkIfCustomerUpdateIdMatch(Integer id, Integer dtoId) {
		if (!id.equals(dtoId)) {
			throw new AppException(HttpStatus.BAD_REQUEST, "ID does not match!" + id + " " + dtoId);
		}
	}

}
