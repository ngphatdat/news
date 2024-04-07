package com.news.news.service;

import com.news.news.dto.CategoryDTO;
import model.Category;

import java.util.List;

public interface ICategoryService {
    public Category creatCategory(CategoryDTO categoryDTO);
    public Category  getCategoryById(Long id);
    public List<Category> getAllCategory();
    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO);
    public void deleteCategory(long id);
}
