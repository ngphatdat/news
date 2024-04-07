package com.news.news.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank(message = "Phone can not be blank")
    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    @NotBlank(message = "Password can not be blank")
    private String password;

    @NotBlank(message = "retypePassword can not be blank")
    @JsonProperty("retype_password")
    private String retypePassword;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("facebook_account_id;")
    private int facebookAccountId;

    @JsonProperty("google_account_id;")
    private int googleAccountId;

    @JsonProperty("role_id")
 //   @NotEmpty(message = "can not be blank")
    private Long roleId;
}
