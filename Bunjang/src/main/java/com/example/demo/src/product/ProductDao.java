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
        String getMainProductsQuery = "select productImgUrl, format(price, '###,###') as price,productName, location, safePay\n" +
                "from Products\n" +
                "inner join ProductImgUrls on Products.productId = ProductImgUrls.productId\n" +
                "group by Products.productId";

        return this.jdbcTemplate.query(getMainProductsQuery,
                (rs, rowNum) -> new GetMainProductsRes(
                        rs.getString("productImgUrl"),
                        rs.getString("price"),
                        rs.getString("productName"),
                        rs.getString("location"),
                        rs.getString("safePay"))
        );
    }

    // 상품 등록 태그 조회
    public List<GetTagsRes> getTags(GetTagsReq getTagsReq) {
        String getTagsQuery = "select word\n" +
                "from Words\n" +
                "where word like concat('%',?,'%')";
        String getTagsParams = getTagsReq.getWord();

        return this.jdbcTemplate.query(getTagsQuery,
                (rs, rowNum) -> new GetTagsRes(
                        rs.getString("word")),
                getTagsParams);
    }

    // 상품 등록 첫번째 카테고리 조회
    public List<GetFirstCategoryRes> getFirstCategory() {
        String getFirstCategoryQuery = "select categoryName\n" +
                "from FirstCategory";

        return this.jdbcTemplate.query(getFirstCategoryQuery,
                (rs, rowNum) -> new GetFirstCategoryRes(
                        rs.getString("categoryName"))
                );
    }
    // 상품 등록 두번째 카테고리 조회
    public List<GetSecondCategoryRes> getSecondCategory(GetSecondCategoryReq getSecondCategoryReq) {
        String getSecondCategoryQuery = "select SecondCategory.categoryName\n" +
                "from SecondCategory\n" +
                "inner join FirstCategory on FirstCategory.firstCategoryId = SecondCategory.firstCategoryId\n" +
                "where FirstCategory.categoryName = ?";
        String getSecondCategoryParams = getSecondCategoryReq.getFirstCategoryName();

        return this.jdbcTemplate.query(getSecondCategoryQuery,
                (rs, rowNum) -> new GetSecondCategoryRes(
                        rs.getString("categoryName")),
                getSecondCategoryParams);
    }
    // 상품 등록 세번째 카테고리 조회
    public List<GetThirdCategoryRes> getThirdCategory(GetThirdCategoryReq getThirdCategoryReq) {
        String getThirdCategoryQuery = "select ThirdCategory.categoryName\n" +
                "from ThirdCategory\n" +
                "inner join SecondCategory on SecondCategory.secondCategoryId = ThirdCategory.secondCategoryId\n" +
                "inner join FirstCategory on FirstCategory.firstCategoryId = SecondCategory.firstCategoryId\n" +
                "where FirstCategory.categoryName = ? and SecondCategory.categoryName = ?";
        Object[] getThirdCategoryParams = new Object[]{getThirdCategoryReq.getFirstCategoryName(),getThirdCategoryReq.getSecondCategoryName()};

        return this.jdbcTemplate.query(getThirdCategoryQuery,
                (rs, rowNum) -> new GetThirdCategoryRes(
                        rs.getString("categoryName")),
                getThirdCategoryParams);
    }

    // 상품 등록 전단계
    public int postProductInfo(int userIdx, PostProductInfoReq postProductInfoReq) {

        // 상품 정보 입력
        String postProductInfoQuery = "INSERT INTO bunjang.ProductInfo (userId, productName, firstCategoryName, secondCategoryName, thirdCategoryName,\n" +
                "                                 price, deliveryTip, count, productStatus, trade, location, description, safePay)\n" +
                "VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] postProductInfoParams = new Object[]{userIdx,postProductInfoReq.getProductName(),postProductInfoReq.getFirstCategoryName(),postProductInfoReq.getSecondCategoryName(),
        postProductInfoReq.getThirdCategoryName(),postProductInfoReq.getPrice(),postProductInfoReq.getDeliveryTip(),postProductInfoReq.getCount(),
        postProductInfoReq.getProductStatus(),postProductInfoReq.getTrade(),postProductInfoReq.getLocation(),
        postProductInfoReq.getDescription(),postProductInfoReq.getSafePay()};

        this.jdbcTemplate.update(postProductInfoQuery,postProductInfoParams);

        String lastInsertIdQuery = "select last_insert_id()";
        int productInfoId = this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);

        // 상품 이미지 등록
        String postProductImgUrlsQuery = "INSERT INTO bunjang.ProductImgUrls (productInfoId, url1, url2, url3, url4, url5, url6, url7, url8, url9, url10, url11, url12)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] postProductImgUrlsParams = new Object[]{productInfoId, postProductInfoReq.getUrl1(),postProductInfoReq.getUrl2(),postProductInfoReq.getUrl3(),
        postProductInfoReq.getUrl4(),postProductInfoReq.getUrl5(),postProductInfoReq.getUrl6(),postProductInfoReq.getUrl7(),
        postProductInfoReq.getUrl8(),postProductInfoReq.getUrl9(),postProductInfoReq.getUrl10(),postProductInfoReq.getUrl11(),postProductInfoReq.getUrl12()};

        this.jdbcTemplate.update(postProductImgUrlsQuery,postProductImgUrlsParams);

        // 상품 태그등록
        String postProductTagsQuery = "INSERT INTO bunjang.ProductTags (productInfoId, tag1, tag2, tag3, tag4, tag5)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Object[] postProductTagsParams = new Object[]{productInfoId,postProductInfoReq.getTag1(),postProductInfoReq.getTag2(),postProductInfoReq.getTag3(),
        postProductInfoReq.getTag4(),postProductInfoReq.getTag5()};

        return this.jdbcTemplate.update(postProductTagsQuery,postProductTagsParams);

        /**
         * 나중에 태그 쓸때마다 words 테이블에 자동 insert되게 해보기..
         */

    }


}
