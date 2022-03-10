package com.cwt.training.services;

import com.cwt.training.dto.OrderDTO;

public interface OrderService {

	public OrderDTO createOrder(OrderDTO dto);
	
	public OrderDTO readOrder(Integer orderId);
	
	public OrderDTO updateOrder(Integer orderId, OrderDTO dto);
	
	public Integer deleteOrder(Integer orderId);
	
}
