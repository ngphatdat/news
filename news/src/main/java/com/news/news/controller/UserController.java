package com.news.news.controller;

import com.news.news.dto.CategoryDTO;
import com.news.news.dto.UserDTO;
import com.news.news.dto.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
@PostMapping("/register")
public ResponseEntity<?> CreatUser(@Valid @RequestBody UserDTO userDTO, BindingResult result){
   try{
    if (result.hasErrors()){
        List<String> errorMessages = result.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(errorMessages);
    }
    if (!userDTO.getPassword().equals(userDTO.getRetypePassword())){
        return ResponseEntity.badRequest().body("password not match");
    }
       return ResponseEntity.ok("Post successful");
   }
    catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody UserDTO userDTO) {
        // Kiểm tra thông tin đăng nhập và sinh token
        // Trả về token trong response
        return ResponseEntity.ok("some token");
    }

}
