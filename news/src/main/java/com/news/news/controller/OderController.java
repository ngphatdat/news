package com.news.news.controller;

import com.news.news.dto.CategoryDTO;
import com.news.news.dto.OtherDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/others")
public class OderController {
    @PostMapping("")
    public ResponseEntity<?> creatOther(@Valid @RequestBody OtherDTO otherDTO, BindingResult result){
        if (result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok("Post successful" + otherDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOther(@Valid @RequestBody OtherDTO otherDTO,
                                              @Valid @PathVariable Long id
                                                ,BindingResult result){
        return  ResponseEntity.ok("update other "+ otherDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOther(@PathVariable long id){

        return ResponseEntity.ok("delete other  with id "+ id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getOther(@PathVariable long id){

        return ResponseEntity.ok("get other  with id "+ id);
    }
}

