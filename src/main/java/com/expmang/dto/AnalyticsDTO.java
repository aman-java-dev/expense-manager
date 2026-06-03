package com.expmang.dto;

public class AnalyticsDTO {

    private String categoryName;
    private Double totalAmount;
    
    
	public AnalyticsDTO(String categoryName, Double totalAmount) {
		super();
		this.categoryName = categoryName;
		this.totalAmount = totalAmount;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
    
    
}