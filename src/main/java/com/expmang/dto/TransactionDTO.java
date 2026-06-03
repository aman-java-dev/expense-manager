package com.expmang.dto;

import lombok.*;

import java.time.LocalDate;

import com.expmang.entity.TransactionType;

@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Double amount;
    private TransactionType type;
    private LocalDate date;

    private Long categoryId;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
    
    
}