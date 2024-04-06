package com.news.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotBlank(message = "not emty")
    @Size(min=3,max=300,message = "Title must betwen 3 to 300")
   private String name;
    @Min( value=3, message = "Title must between 3 to 300")
    @Max(value =100000,message = "Title must between 3 to 300")
   private float price ;
   private String thumbnail;
   private String description;
   @JsonProperty("category_id")
   private long categoryId;
}
