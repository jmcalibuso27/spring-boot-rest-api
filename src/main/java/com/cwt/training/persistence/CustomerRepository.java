package com.cwt.training.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwt.training.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Optional<Customer> findByFirstName(String firstName);
	
}
