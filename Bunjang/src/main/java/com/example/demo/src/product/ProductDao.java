package com.example.demo.src.product;

import com.example.demo.src.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 상품 상세 페이지 조회
    public GetProductDetailRes getProductDetail(int productIdx) {
        String getProductDetailQuery = "select format(price, '###,###') as price, productName, location, productStatus, count, trade, description,\n" +
                "       ThirdCategory.categoryName as categoryName, safePay\n" +
                "from Products\n" +
                "inner join FirstCategory on Products.firstCategoryId = FirstCategory.firstCategoryId\n" +
                "inner join SecondCategory on FirstCategory.firstCategoryId = SecondCategory.firstCategoryId\n" +
                "inner join ThirdCategory on SecondCategory.secondCategoryId = ThirdCategory.secondCategoryId\n" +
                "where productId = ?\n" +
                "and Products.thirdCategoryId = ThirdCategory.thirdCategoryId";
        int getProductDetailParams = productIdx;

        String getProductImgQuery = "select productImgUrl\n" +
                "from ProductImgUrls\n" +
                "inner join Products on Products.productId = ProductImgUrls.productId\n" +
                "where Products.productId = ?;";
        int getProductImgParams = productIdx;
        List<GetProductImgRes> getProductImgRes = this.jdbcTemplate.query(getProductImgQuery,
                (rs, rowNum) -> new GetProductImgRes(
                        rs.getString("productImgUrl")),
                getProductDetailParams);

        return this.jdbcTemplate.queryForObject(getProductDetailQuery,
                (rs, rowNum) -> new GetProductDetailRes(
                        getProductImgRes,
                        rs.getString("price"),
                        rs.getString("productName"),
                        rs.getString("location"),
                        rs.getString("productStatus"),
                        rs.getInt("count"),
                        rs.getString("trade"),
                        rs.getString("description"),
                        rs.getString("categoryName"),
                        rs.getString("safePay")),
                getProductDetailParams);
    }

    // 메인 화면 추천 상품 조회
    public List<GetMainProductsRes> getMainProducts(){
        String getMainProductsQuery = "select productImgUrl, price,productName, location, safePay\n" +
                "from Products\n" +
                "inner join ProductImgUrls on Products.productId = ProductImgUrls.productId\n" +
                "group by Products.productId";
        int test=1;
        return this.jdbcTemplate.query(getMainProductsQuery,
                (rs, rowNum) -> new GetMainProductsRes(
                        rs.getString("productImgUrl"),
                        rs.getString("price"),
                        rs.getString("productName"),
                        rs.getString("location"),
                        rs.getString("safePay"))
        );
    }


}
