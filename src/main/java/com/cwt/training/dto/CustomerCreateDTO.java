package com.cwt.training.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateDTO {

	@NotBlank
	@Length(min = 2, max = 20)
	private String firstName;
	
	@NotBlank
	@Length(min = 2, max = 20)
	private String lastName;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Length(max = 100)
	private String location;
	
}
