package com.news.news.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.news.news.model.User;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderResponse {
    private long id;
    @JsonProperty("user_id")
    private User user_id;
    private String fullName;
    private String email;

    private String phoneNumber;

    private String address;

    private String note;

    @JsonProperty("order_date")
    private Date orderDate;
    private String status;
    @JsonProperty("total_money")
    private Integer totalMoney;
    @JsonProperty("shipping_method")
    private String shippingMethod;
    @JsonProperty("shipping_address")
    private String shippingAddress;
    @JsonProperty("shipping_date")
    private Date shippingDate;
    @JsonProperty("tracking_number")
    private String trackingNumber;
    @JsonProperty("payment_method")
    private String paymentMethod;
    @Column(name = "active")
    private Boolean active;//thuộc về admin

}
