package com.cwt.training.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEmailOnlyDTO extends AbstractCustomerDTO{

	@NotBlank
	@Email
	private String email;

	public CustomerEmailOnlyDTO(Integer id, String email) {
		super(id);
		this.email = email;
	}
	
}
