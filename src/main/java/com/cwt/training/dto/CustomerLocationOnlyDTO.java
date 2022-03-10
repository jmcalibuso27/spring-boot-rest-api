package com.cwt.training.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLocationOnlyDTO extends AbstractCustomerDTO{

	@NotBlank
	@Length(max = 100)
	private String location;

	public CustomerLocationOnlyDTO(Integer id, String location) {
		super(id);
		this.location = location;
	}
	
}
