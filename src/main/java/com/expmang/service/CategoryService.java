package com.expmang.service;

import java.util.List;

import com.expmang.dto.CategoryDTO;
import com.expmang.entity.Category;

public interface CategoryService {
    Category addCategory(CategoryDTO dto);
    List<Category> getAllCategories();
}
