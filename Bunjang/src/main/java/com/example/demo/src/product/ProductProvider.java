package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.product.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.demo.config.BaseResponseStatus.*;


@Service

public class ProductProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductDao productDao;
    private final JwtService jwtService;

    @Autowired
    public ProductProvider(ProductDao productDao, JwtService jwtService) {
        this.productDao = productDao;
        this.jwtService = jwtService;
    }


    // 상품 상세 페이지 조회
    public GetProductDetailRes getProductDetail(int productIdx, int userIdx) throws BaseException {
        if (productDao.checkUser(userIdx) == 0 ) {
            throw new BaseException(INVALID_USER);
        }
        if(productDao.checkProduct(productIdx) == 0) {
            throw new BaseException(INVALID_PRODUCT);
        }
        try {
            GetProductDetailRes getProductDetailRes = productDao.getProductDetail(productIdx,userIdx);
            return getProductDetailRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 메인화면 추천 상품 조회
    public List<GetMainProductsRes> getMainProducts() throws BaseException{
        try {
            List<GetMainProductsRes> getMainProductsRes = productDao.getMainProducts();
            return getMainProductsRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // 상품 등록 태그 조회
    public List<GetTagsRes> getTags(String tagWord) throws BaseException{
        try {
            List<GetTagsRes> getTagsRes = productDao.getTags(tagWord);
            return getTagsRes;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 상품 등록 첫번째 카테고리 조회
    public List<GetFirstCategoryRes> getFirstCategory() throws BaseException {
        try {
            List<GetFirstCategoryRes> getFirstCategoryRes = productDao.getFirstCategory();
            return getFirstCategoryRes;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    // 상품 등록 두번째 카테고리 조회
    public List<GetSecondCategoryRes> getSecondCategory(int firstIdx) throws BaseException {
        try {
            List<GetSecondCategoryRes> getSecondCategoryRes = productDao.getSecondCategory(firstIdx);
            return getSecondCategoryRes;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    // 상품 등록 세번째 카테고리 조회
    public List<GetThirdCategoryRes> getThirdCategory(int firstIdx, int secondIdx) throws BaseException {
        try {
            List<GetThirdCategoryRes> getThirdCategoryRes = productDao.getThirdCategory(firstIdx, secondIdx);

            return getThirdCategoryRes;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
