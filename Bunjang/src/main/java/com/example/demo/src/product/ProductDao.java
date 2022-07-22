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
    public int postProduct(int userIdx, PostProductReq postProductReq) {

        // 상품 정보 입력
        String postProductReqQuery = "INSERT INTO bunjang.Products (userId, productName, firstCategoryName, secondCategoryName, thirdCategoryName,\n" +
                "                                 price, deliveryTip, count, productStatus, trade, location, description, safePay)\n" +
                "VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] postProductReqParams = new Object[]{userIdx,postProductReq.getProductName(),postProductReq.getFirstCategoryName(),postProductReq.getSecondCategoryName(),
        postProductReq.getThirdCategoryName(),postProductReq.getPrice(),postProductReq.getDeliveryTip(),postProductReq.getCount(),
        postProductReq.getProductStatus(),postProductReq.getTrade(),postProductReq.getLocation(),
        postProductReq.getDescription(),postProductReq.getSafePay()};

        this.jdbcTemplate.update(postProductReqQuery,postProductReqParams);

        String lastInsertIdQuery = "select last_insert_id()";
        int productId = this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);

        // 상품 이미지 등록
        String postProductReqImgUrlsQuery = "INSERT INTO bunjang.ProductImgUrls (productId, url1, url2, url3, url4, url5, url6, url7, url8, url9, url10, url11, url12)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] postProductReqImgUrlsParams = new Object[]{productId, postProductReq.getUrl1(),postProductReq.getUrl2(),postProductReq.getUrl3(),
        postProductReq.getUrl4(),postProductReq.getUrl5(),postProductReq.getUrl6(),postProductReq.getUrl7(),
        postProductReq.getUrl8(),postProductReq.getUrl9(),postProductReq.getUrl10(),postProductReq.getUrl11(),postProductReq.getUrl12()};

        this.jdbcTemplate.update(postProductReqImgUrlsQuery,postProductReqImgUrlsParams);

        // 상품 태그등록
        String postProductReqTagsQuery = "INSERT INTO bunjang.ProductTags (productId, tag1, tag2, tag3, tag4, tag5)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Object[] postProductReqTagsParams = new Object[]{productId,postProductReq.getTag1(),postProductReq.getTag2(),postProductReq.getTag3(),
        postProductReq.getTag4(),postProductReq.getTag5()};

        return this.jdbcTemplate.update(postProductReqTagsQuery,postProductReqTagsParams);

        /**
         * 나중에 태그 쓸때마다 words 테이블에 자동 insert되게 해보기..
         */

    }


}
