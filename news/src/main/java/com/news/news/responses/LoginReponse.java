package com.news.news.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReponse {
    @JsonProperty("token")
    private String token;
    @JsonProperty("mess")
    private String mess;

}
