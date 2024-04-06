package com.news.news.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDTO {
    @NotEmpty(message = "name not empty")
    private String name;
}
