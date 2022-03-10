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
public class CustomerLastNameOnlyDTO extends AbstractCustomerDTO{

	@NotBlank
	@Length(min = 2, max = 20)
	private String lastName;

	public CustomerLastNameOnlyDTO(Integer id, String lastName) {
		super(id);
		this.lastName = lastName;
	}
	
}
