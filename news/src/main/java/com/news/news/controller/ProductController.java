package com.news.news.controller;

import com.news.news.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @GetMapping("")
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit    ){
        return ResponseEntity.ok(String.format("successful, page = %d , limit =%d",page,limit));
    }
    @PostMapping(value="",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertAllCategories(@Valid @ModelAttribute ProductDTO productDTO,
                              BindingResult result
                              //,@RequestPart("file") MultipartFile file
    ){
        try {
        if (result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        List<MultipartFile> files = productDTO.getFiles();
        files= files==null?new ArrayList<MultipartFile>():files;
        for (MultipartFile file : files){
            if (file.getSize()==0){
                continue;
            }


        if (file!=null) {
            String contentType = file.getContentType();
//            if (contentType != null || !contentType.startsWith("image/")) {
//            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("file must be image");
//            }
            String filename = storeFile(file);
            //luu vao database
        }

        }
            return ResponseEntity.ok("Post successful"+productDTO);
        }
        catch (IOException e){
           return  ResponseEntity.badRequest().body(e.getMessage());

        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id){
        return  ResponseEntity.ok("update category  with id "+ id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){

        return ResponseEntity.ok("delete category  with id "+ id);
    }
    private String storeFile(MultipartFile file) throws IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        //thêm UUID vào tên file
        String uiqueFilename = UUID.randomUUID().toString()+"_" +filename;
        //đường dân dến thư mục muốn lưu
        java.nio.file.Path uploadDir = Paths.get("uploads");
        //tạo thư mục nếu thư mục chưa tồn tại
        if (!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        //dường dẫn đến file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(),uiqueFilename);
        // sao chép file vào thư mục
        Files.copy(file.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
        return uiqueFilename;
    }
}
