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
public class CustomerFirstNameOnlyDTO extends AbstractCustomerDTO{

	@NotBlank
	@Length(min = 2, max = 20)
	private String firstName;

	public CustomerFirstNameOnlyDTO(Integer id, String firstName) {
		super(id);
		this.firstName = firstName;
	}
	
}
