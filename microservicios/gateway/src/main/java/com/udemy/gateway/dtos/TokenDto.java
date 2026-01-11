package com.udemy.gateway.dtos;

//import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// Como le da flojera hacerlo con lombok va a hacerlo a mano porque 
// gateway no tiene esa dependencia (no se la pusimos)  
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder

public class TokenDto {

    public TokenDto() {

	}

	
    public TokenDto(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	private String accessToken;
}