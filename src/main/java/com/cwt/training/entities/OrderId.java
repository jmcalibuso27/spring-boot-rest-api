package com.cwt.training.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderId implements Serializable{

	private static final long serialVersionUID = -559014178613227390L;
	private Customer customer;
	private Integer orderSequenceNumber;
	
}
