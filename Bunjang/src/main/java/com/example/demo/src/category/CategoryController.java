package com.example.demo.src.category;

import com.example.demo.src.product.model.GetMainProductsRes;
import com.example.demo.src.product.model.GetSecondCategoryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController

@RequestMapping("/bunjang/categories")

public class CategoryController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CategoryProvider categoryProvider;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final JwtService jwtService;

    public CategoryController(CategoryProvider categoryProvider, CategoryService categoryService, JwtService jwtService) {
        this.categoryProvider = categoryProvider;
        this.categoryService = categoryService;
        this.jwtService = jwtService;
    }

    /**
     * 첫번째 카테고리 상품 조회
     * /first-category
     */
    @ResponseBody
    @GetMapping("/first-category")
    public BaseResponse<List<GetMainProductsRes>> getFirstCategoryProducts(@RequestBody GetFirstCategoryProductsReq getFirstCategoryProductsReq) {
        try {

            List<GetMainProductsRes> getMainProductsRes = categoryProvider.getFirstCategoryProducts(getFirstCategoryProductsReq);

            return new BaseResponse<>(getMainProductsRes);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 두번째 카테고리 상품 조회
     * /second-category
     */
    @ResponseBody
    @GetMapping("second-category")
    public BaseResponse<List<GetMainProductsRes>> getSecondCategoryProducts(@RequestBody GetSecondCategoryProductsReq getSecondCategoryProductsReq) {
        try {

            List<GetMainProductsRes> getMainProductsRes = categoryProvider.getSecondCategoryProducts(getSecondCategoryProductsReq);

            return new BaseResponse<>(getMainProductsRes);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 세번째 카테고리 상품 조회
     * /third-category
     */
    @ResponseBody
    @GetMapping("/third-category")
    public BaseResponse<List<GetMainProductsRes>> getThirdCategoryProducts(@RequestBody GetThirdCategoryProductsReq getThirdCategoryProductsReq) {
        try {

            List<GetMainProductsRes> getMainProductsRes = categoryProvider.getThirdCategoryProducts(getThirdCategoryProductsReq);

            return new BaseResponse<>(getMainProductsRes);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
