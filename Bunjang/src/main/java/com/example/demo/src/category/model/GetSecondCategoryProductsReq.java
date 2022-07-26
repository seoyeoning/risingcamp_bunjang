package com.example.demo.src.category.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class GetSecondCategoryProductsReq {

    private int firstCategoryId;
    private int SecondCategoryId;

}
