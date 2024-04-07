package com.news.news.service;

import com.news.news.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import com.news.news.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor

public class CategoryService implements ICategoryService {
    //private final CategoryReponsitory categoryRepository;

    @Override
    public Category creatCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO
                .getName())
                .build();
        return null;
       // return categoryRepository.save(category);
    }

    @Override
    public Category  getCategoryById(Long id) {
//        return categoryRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("category not found"));
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
       // return categoryRepository.findAll();
        return null;
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) {
//        Category existingCategory = getCategoryById(categoryId);
//        existingCategory.setName(categoryDTO.getName());
//        categoryRepository.save(existingCategory);
//      return existingCategory;
        return null;
    }
    @Override
    public void deleteCategory(Long id) {
        //xóa xong
//        categoryRepository.deleteById(id);
        return ;
    }

}
