package com.expmang.service.impl;

import com.expmang.dto.CategoryDTO;
import com.expmang.entity.Category;
import com.expmang.entity.User;
import com.expmang.exception.ResourceNotFoundException;
import com.expmang.repository.CategoryRepository;
import com.expmang.repository.UserRepository;
import com.expmang.service.CategoryService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Category addCategory(CategoryDTO dto) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Category category = new Category();
        category.setName(dto.getName());
        category.setUser(user);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
    	String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return categoryRepository.findByUser(user);
    }
}