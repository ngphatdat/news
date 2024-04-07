package com.news.news.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherDTO {
   // @NotEmpty(message = "user not bank")
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("full_name")
    private String fullName;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    private String note;
    //@Min(value = 0, message = "total money > 0")
    @JsonProperty("total_money")
    private float totalMoney;
    @JsonProperty("Shipping_method")
    private String ShippingMethod;
    @JsonProperty("Shipping_address")
    private String ShippingAddress;
    @JsonProperty("payment_method")
    private String paymentMethod;
}
