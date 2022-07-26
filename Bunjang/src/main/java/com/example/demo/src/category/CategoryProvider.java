package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.category.model.*;
import com.example.demo.src.product.model.GetMainProductsRes;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.demo.config.BaseResponseStatus.*;


@Service

public class CategoryProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CategoryDao categoryDao;
    private final JwtService jwtService;

    @Autowired
    public CategoryProvider ( CategoryDao categoryDao, JwtService jwtService) {
        this.categoryDao = categoryDao;
        this.jwtService = jwtService;
    }

    // 첫번째 카테고리 상품 조회
    public List<GetMainProductsRes> getFirstCategoryProducts(GetFirstCategoryProductsReq getFirstCategoryProductsReq) throws BaseException {
        try {
            List<GetMainProductsRes> getMainProductsRes = categoryDao.getFirstCategoryProducts(getFirstCategoryProductsReq);

            return getMainProductsRes;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 두번째 카테고리 상품 조회
    public List<GetMainProductsRes> getSecondCategoryProducts(GetSecondCategoryProductsReq getSecondCategoryProductsReq) throws BaseException {
        try {

            List<GetMainProductsRes> getMainProductsRes = categoryDao.getSecondCategoryProducts(getSecondCategoryProductsReq);

            return getMainProductsRes;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 세번째 카테고리 상품 조회
    public List<GetMainProductsRes> getThirdCategoryProducts(GetThirdCategoryProductsReq getThirdCategoryProductsReq) throws BaseException {
        try {
            List<GetMainProductsRes> getMainProductsRes = categoryDao.getThirdCategoryProducts(getThirdCategoryProductsReq);

            return getMainProductsRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
