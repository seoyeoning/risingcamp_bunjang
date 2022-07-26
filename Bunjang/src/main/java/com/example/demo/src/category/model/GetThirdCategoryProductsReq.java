package com.example.demo.src.category.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class GetThirdCategoryProductsReq {

    private int firstCategoryId;
    private int SecondCategoryId;
    private int thirdCategoryId;

}
