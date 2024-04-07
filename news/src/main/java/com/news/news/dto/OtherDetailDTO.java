package com.news.news.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherDetailDTO {
    // @NotEmpty(message = "user not bank")
    @JsonProperty("other_id")
    private Long otherId;
    @JsonProperty("product_id")
    private Long productID;
    private Float price;
    @JsonProperty("number_of_products")
    private Long numberOfProducts;
    @JsonProperty("total_money")
    private float totalMoney;
    private String color;
}
