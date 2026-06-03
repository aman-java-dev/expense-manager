package com.expmang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expmang.entity.Category;
import com.expmang.entity.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByUser(User user);
}
