package com.news.news.service;

import com.news.news.dto.CategoryDTO;
import com.news.news.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category creatCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO
                .getName())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category  getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("category not found"));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }
    @Override
    public void deleteCategory(long id) {
        //xóa xong
        categoryRepository.deleteById(id);
    }

}
