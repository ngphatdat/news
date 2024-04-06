package com.news.news.controller;

import com.news.news.dto.CategoryDTO;
import com.news.news.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @GetMapping("")
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit    ){
        return ResponseEntity.ok(String.format("successful, page = %d , limit =%d",page,limit));
    }
    @PostMapping("")
    public ResponseEntity<?> insertAllCategories(@Valid @RequestBody ProductDTO productDTO, BindingResult result){
        if (result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }

        return ResponseEntity.ok("Post successful"+productDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id){
        return  ResponseEntity.ok("update category  with id "+ id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){

        return ResponseEntity.ok("delete category  with id "+ id);
    }
}
