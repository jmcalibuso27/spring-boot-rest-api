package com.cwt.training.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private Integer id;
	
	private CustomerIdOnlyDTO customer;

	private String orderName;
	
	private double quantity;
	
	private double amount;
	
}
