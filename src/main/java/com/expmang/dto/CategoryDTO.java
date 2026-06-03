package com.expmang.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}