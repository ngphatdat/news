package com.news.news.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@MappedSuperclass
public class BaseResponse {


    @JsonProperty("created_at")
    private LocalDateTime CreatedAt;
    @JsonProperty("update_at")
    private LocalDateTime updateAt;

}
