package com.cwt.training.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.cwt.training.entities.Order;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO extends AbstractCustomerDTO {
	
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
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Order> orders;

	public CustomerDTO(Integer id, String firstName,
			String lastName, String email,
			String location) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = location;
	}
	
}
