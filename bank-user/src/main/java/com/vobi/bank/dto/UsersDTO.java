package com.vobi.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
	@NotNull
	@Email
	private String userEmail;
	
	@NotNull
	private Integer ustyId;
	
	@NotNull
	@Size(min = 1, max = 1)
	private String enable;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String token;
}
