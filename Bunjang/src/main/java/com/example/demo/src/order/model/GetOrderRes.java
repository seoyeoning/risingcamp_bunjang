package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderRes {
    private String url1;
    private String productName;
    private String status;
    private String storeName;
    private String price;
    private String date;
}
