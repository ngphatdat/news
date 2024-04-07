package com.news.news.controller;

import com.news.news.dto.CategoryDTO;
import com.news.news.service.CategoryService;
import jakarta.validation.Valid;
import com.news.news.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
//dependence injection
    @RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<List<Category>>getAllCategories(
                @RequestParam("page") int page,
                @RequestParam("limit") int limit    ){
       List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }
    @PostMapping("")
    public ResponseEntity<?> insertAllCategories(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result){
    if (result.hasErrors()){
      List<String> errorMessages = result.getFieldErrors()
              .stream()
              .map(FieldError::getDefaultMessage)
              .toList();
        return ResponseEntity.badRequest().body(errorMessages);
    }
        categoryService.creatCategory(categoryDTO);
        return ResponseEntity.ok("Post successful");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id,
                                                 @Valid @RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(id,categoryDTO);
        return  ResponseEntity.ok("update category  with id "+ id);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("delete category  with id "+ id);
    }
}