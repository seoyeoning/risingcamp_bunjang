package com.example.demo.src.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController

@RequestMapping("/bunjang/products")

public class ProductController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ProductProvider productProvider;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final JwtService jwtService;

    public ProductController(ProductProvider productProvider, ProductService productService,JwtService jwtService) {
        this.productProvider = productProvider;
        this.productService = productService;
        this.jwtService = jwtService;
    }







    /**
     * 상품 등록 태그 조회 API
     * [GET] /:userIdx/new-product/tags/:tagWord
     */
    @ResponseBody
    @GetMapping("/{userIdx}/new-product/tags/{tagWord}")
    public BaseResponse<List<GetTagsRes>> getTags(@PathVariable("userIdx") int userIdx,@PathVariable String tagWord) {
        try {
            List<GetTagsRes> getTagsRes = productProvider.getTags(tagWord);
            return new BaseResponse<>(getTagsRes);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 등록 첫번째 카테고리 조회
     * [GET] /:userIdx/new-product/categories
     */
    @ResponseBody
    @GetMapping("/{userIdx}/new-product/categories/first")
    public BaseResponse<List<GetFirstCategoryRes>> getFirstCategory(@PathVariable("userIdx") int userIdx) {
        try {
            List<GetFirstCategoryRes> getFirstCategoryRes = productProvider.getFirstCategory();
            return new BaseResponse<>(getFirstCategoryRes);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 상품 등록 두번째 카테고리 조회
     * [POST] /:userIdx/new-product/categories/:first
     */
    @ResponseBody
    @PostMapping("/{userIdx}/new-product/categories/second")
    public BaseResponse<List<GetSecondCategoryRes>> getSecondCategory(@PathVariable("userIdx") int userIdx,@RequestBody GetSecondCategoryReq getSecondCategoryReq) {
        try {
            List<GetSecondCategoryRes> getSecondCategoryRes = productProvider.getSecondCategory(getSecondCategoryReq);
            return new BaseResponse<>(getSecondCategoryRes);

        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 등록 세번째 카테고리 조회
     * [POST] /:userIdx/new-product/categories/:first/:second
     */
    @ResponseBody
    @PostMapping("/{userIdx}/new-product/categories/third")
    public BaseResponse<List<GetThirdCategoryRes>> getThirdCategory(@PathVariable("userIdx") int userIdx,@RequestBody GetThirdCategoryReq getThirdCategoryReq) {
        try {
            List<GetThirdCategoryRes> getThirdCategoryRes = productProvider.getThirdCategory(getThirdCategoryReq);

            return new BaseResponse<>(getThirdCategoryRes);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 등록 API
     * [POST] /bunjang/products/:userIdx/new-product
     */
    @ResponseBody
    @PostMapping("/{userIdx}/new-product")
    public BaseResponse<String> postProduct(@PathVariable("userIdx") int userIdx,@RequestBody PostProductReq postProduct) {
        try {

            productService.postProduct(userIdx, postProduct);

            String result = "상품 등록이 완료되었습니다.";
            return new BaseResponse<>(result);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
