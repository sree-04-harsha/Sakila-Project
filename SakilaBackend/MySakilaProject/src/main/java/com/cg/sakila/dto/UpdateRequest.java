package com.cg.sakila.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {

	private String firstname;
	
	private String lastname;
	
	private String email;

	
}
