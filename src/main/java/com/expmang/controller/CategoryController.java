package com.expmang.controller;

import org.springframework.web.bind.annotation.*;

import com.expmang.dto.CategoryDTO;
import com.expmang.entity.Category;
import com.expmang.service.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Categories", description = "Category Management APIs")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category addCategory(@RequestBody CategoryDTO dto) {
        return categoryService.addCategory(dto);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}