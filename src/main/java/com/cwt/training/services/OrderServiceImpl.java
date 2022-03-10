package com.cwt.training.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwt.training.dto.OrderDTO;
import com.cwt.training.entities.Order;
import com.cwt.training.exceptions.NotFoundException;
import com.cwt.training.persistence.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	@Override
	public OrderDTO createOrder(OrderDTO dto) {
		Order order = modelMapper.map(dto, Order.class);
		return modelMapper.map(orderRepository.save(order), OrderDTO.class);
	}

	@Transactional(readOnly = true)
	@Override
	public OrderDTO readOrder(Integer orderId) {
		return modelMapper.map(getOrder(orderId), OrderDTO.class);
	}

	@Transactional
	@Override
	public OrderDTO updateOrder(Integer orderId, OrderDTO dto) {
		Order order = getOrder(orderId);
		modelMapper.map(dto, order);
		return modelMapper.map(orderRepository.save(order), OrderDTO.class);
	}

	@Transactional
	@Override
	public Integer deleteOrder(Integer orderId) {
		if (!orderRepository.existsById(orderId)) {
			throw new NotFoundException("Order not found");
		}
		orderRepository.deleteById(orderId);
		return orderId;
	}
	
	private Order getOrder(Integer orderId) {
		return orderRepository.findById(orderId).orElseThrow(
				() -> new NotFoundException("Order not found"));
	}

}
