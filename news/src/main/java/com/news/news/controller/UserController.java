package com.news.news.controller;

import com.news.news.dto.UserDTO;
import com.news.news.dto.UserLoginDTO;
import com.news.news.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
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
       userService.creatUser(userDTO);
       return ResponseEntity.ok("Post successful" + userDTO);
   }
    catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = userService.login(userLoginDTO.getPhoneNumber(), userLoginDTO.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return null;
        }
    }

}
