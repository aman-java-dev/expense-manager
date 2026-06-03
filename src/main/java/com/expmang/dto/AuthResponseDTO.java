package com.expmang.dto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class AuthResponseDTO {

	private String token;
	
	

	public AuthResponseDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
