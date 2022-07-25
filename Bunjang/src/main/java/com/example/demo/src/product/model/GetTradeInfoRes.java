package com.example.demo.src.product.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class GetTradeInfoRes {
    private String productName;
    private String url1;
    private int price;
    private String storeName;
    //변경값
    private String tradeMethod;
    private String orderNumber;
    private String orderDay;
    private String payMethod;
    private int fee;
    private String deliveryTip;

    private String userName;
    private String phone;
    private String address;
}
