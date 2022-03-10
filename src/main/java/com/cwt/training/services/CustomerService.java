package com.cwt.training.services;

import java.util.List;

import com.cwt.training.dto.CustomerCreateDTO;
import com.cwt.training.dto.CustomerDTO;
import com.cwt.training.dto.CustomerEmailOnlyDTO;
import com.cwt.training.dto.CustomerFirstNameOnlyDTO;
import com.cwt.training.dto.CustomerLastNameOnlyDTO;
import com.cwt.training.dto.CustomerLocationOnlyDTO;

public interface CustomerService {

	public CustomerDTO findCustomerById(Integer id);
	
	public CustomerDTO createCustomer(CustomerCreateDTO customer);
	
	public List<CustomerDTO> getAll();
	
	public Integer deleteCustomerById(Integer id);
	
	public CustomerDTO updateCustomer(Integer id, CustomerDTO customer);
	
	public CustomerDTO updateCustomerFirstName(Integer id, CustomerFirstNameOnlyDTO dto);
	
	public CustomerDTO updateCustomerLastName(Integer id, CustomerLastNameOnlyDTO dto);

	public CustomerDTO updateCustomerEmail(Integer id, CustomerEmailOnlyDTO dto);
	
	public CustomerDTO updateCustomerLocation(Integer id, CustomerLocationOnlyDTO dto);
}
