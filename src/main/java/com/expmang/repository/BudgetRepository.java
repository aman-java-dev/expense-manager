package com.expmang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expmang.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

}
