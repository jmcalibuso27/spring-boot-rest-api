package com.cwt.training.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwt.training.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
