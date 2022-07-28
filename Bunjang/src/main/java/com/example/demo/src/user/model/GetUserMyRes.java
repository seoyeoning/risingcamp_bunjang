package com.example.demo.src.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class GetUserMyRes {

    private int productIdx;
    private String storeProfileImgUrl;
    private String storeName;
    private int bookmarkCnt;
    private String productImgUrl;
    private String productName;
    private String price;
    private String timeDiff;
}
