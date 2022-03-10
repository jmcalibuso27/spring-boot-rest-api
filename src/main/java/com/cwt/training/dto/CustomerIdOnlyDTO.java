package com.cwt.training.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CustomerIdOnlyDTO extends AbstractCustomerDTO{
	
	public CustomerIdOnlyDTO(Integer id) {
		super(id);
	}

}
