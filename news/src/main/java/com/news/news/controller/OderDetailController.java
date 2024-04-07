package com.news.news.controller;

import com.news.news.dto.OtherDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/others_detail")
public class OderDetailController {
    @GetMapping("/other/{otherId}")
    public ResponseEntity<String> getOtherDetail(
           @Valid @PathVariable("otherId") Long otherId  ){
        return ResponseEntity.ok("successful with %d"+otherId);
    }
    @PostMapping("")
    public ResponseEntity<?> creatOtherDetail(@Valid @RequestBody OtherDetailDTO otherDetailDTO, BindingResult result){
        if (result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok("Post successful" + otherDetailDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOtherDetail(@Valid @RequestBody OtherDetailDTO otherDetailDTO,
                                              @Valid @PathVariable Long id
            ,BindingResult result){
        return  ResponseEntity.ok("update other "+ otherDetailDTO +"with" + id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOther(@PathVariable long id){

        return ResponseEntity.ok("delete other  with id "+ id);
    }
}
