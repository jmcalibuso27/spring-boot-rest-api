package com.cwt.training.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwt.training.dto.OrderDTO;
import com.cwt.training.services.OrderService;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO dto) {
		return ResponseEntity.ok(orderService.createOrder(dto));
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDTO> readOrder(@PathVariable Integer orderId) {
		return ResponseEntity.ok(orderService.readOrder(orderId));
	}
	
	@PutMapping("/update/{orderId}")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer orderId, @Valid @RequestBody OrderDTO dto) {
		return ResponseEntity.ok(orderService.updateOrder(orderId, dto));
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Integer> deleteOrder(@PathVariable Integer orderId) {
		return ResponseEntity.ok(orderService.deleteOrder(orderId));
	}
	
}
