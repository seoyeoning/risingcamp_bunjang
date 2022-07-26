package com.example.demo.src.category.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class GetFirstCategoryProductsReq {

    private int firstCategoryId;
}
