package com.cg.sakila.dto;

import com.cg.sakila.enitites.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

	private String firstname;

	private String lastname;

	private String email;

	private String password;

	private String role;

	
}
