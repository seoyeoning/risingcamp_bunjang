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
        String getProductDetailQuery = "select Products.id, format(price, '###,###') as price, safePay, productName, location,\n" +
                "       (select case when TIMESTAMPDIFF(MINUTE ,Products.createAt, NOW()) < 60\n" +
                "then concat(TIMESTAMPDIFF(MINUTE ,Products.createAt, NOW()),'분 전')\n" +
                "    when TIMESTAMPDIFF(HOUR ,Products.createAt, NOW()) < 24\n" +
                "    then concat(TIMESTAMPDIFF(HOUR ,Products.createAt, NOW()), '시간 전')\n" +
                "    WHEN TIMESTAMPDIFF(DAY ,Products.createAt, NOW()) < 30\n" +
                "    then concat(TIMESTAMPDIFF(DAY ,Products.createAt, NOW()), '일 전')\n" +
                "end) as timeDiff, productStatus, count, deliveryTip, trade, description,\n" +
                "                  (select case when Products.thirdCategoryName is null and Products.secondCategoryName is null\n" +
                "then Products.firstCategoryName\n" +
                "when Products.thirdCategoryName is null and Products.secondCategoryName is not null\n" +
                "then Products.secondCategoryName\n" +
                "    when Products.thirdCategoryName is not null\n" +
                "    then Products.thirdCategoryName\n" +
                "                   end) as category, count( BookMarks.productId) as bookmarkCnt\n" +
                "from Products\n" +
                "left join BookMarks on BookMarks.productId = Products.id\n" +
                "group by Products.id\n" +
                "having Products.id = ?";
        int getProductDetailParams = productIdx;

        String getProductImgQuery = "select url1, url2, url3,url4,url5,url6,url7,url8,url9,url10,url11,url12\n" +
                "from ProductImgUrls\n" +
                "inner join Products on Products.id = ProductImgUrls.productId\n" +
                "where Products.id = ?";
        int getProductImgParams = productIdx;
        GetProductImgRes getProductImgRes = this.jdbcTemplate.queryForObject(getProductImgQuery,
                (rs, rowNum) -> new GetProductImgRes(
                        rs.getString("url1"),
                        rs.getString("url2"),
                        rs.getString("url3"),
                        rs.getString("url4"),
                        rs.getString("url5"),
                        rs.getString("url6"),
                        rs.getString("url7"),
                        rs.getString("url8"),
                        rs.getString("url9"),
                        rs.getString("url10"),
                        rs.getString("url11"),
                        rs.getString("url12")
                ),
                getProductImgParams);

        String getProductTagQuery = "select tag1, tag2, tag3, tag4, tag5\n" +
                "from Products\n" +
                "inner join ProductTags on ProductTags.productId = Products.id\n" +
                "where Products.id = ?";
        int getProductTagParams = productIdx;
        GetProductTagRes getProductTagRes = this.jdbcTemplate.queryForObject(getProductTagQuery,
                (rs, rowNum) -> new GetProductTagRes(
                        rs.getString("tag1"),
                        rs.getString("tag2"),
                        rs.getString("tag3"),
                        rs.getString("tag4"),
                        rs.getString("tag5")
                ),
                getProductTagParams);

        return this.jdbcTemplate.queryForObject(getProductDetailQuery,
                (rs, rowNum) -> new GetProductDetailRes(
                        getProductImgRes,
                        rs.getInt("id"),
                        rs.getString("price"),
                        rs.getString("safePay"),
                        rs.getString("productName"),
                        rs.getString("location"),
                        rs.getString("timeDiff"),
                        rs.getString("productStatus"),
                        rs.getInt("count"),
                        rs.getString("deliveryTip"),
                        rs.getString("trade"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getInt("bookmarkCnt"),
                        getProductTagRes
                        ),
                getProductDetailParams);
    }

    // 메인 화면 추천 상품 조회
    public List<GetMainProductsRes> getMainProducts(){
        String getMainProductsQuery = "select Products.id, url1, format(price, '###,###') as price, productName, location,\n" +
                "       (select case when TIMESTAMPDIFF(MINUTE ,Products.createAt, NOW()) < 60\n" +
                "then concat(TIMESTAMPDIFF(MINUTE ,Products.createAt, NOW()),'분 전')\n" +
                "    when TIMESTAMPDIFF(HOUR ,Products.createAt, NOW()) < 24\n" +
                "    then concat(TIMESTAMPDIFF(HOUR ,Products.createAt, NOW()), '시간 전')\n" +
                "    WHEN TIMESTAMPDIFF(DAY ,Products.createAt, NOW()) < 30\n" +
                "    then concat(TIMESTAMPDIFF(DAY ,Products.createAt, NOW()), '일 전')\n" +
                "end) as timeDiff , safePay, count( BookMarks.productId) as bookmarkCnt\n" +
                "from Products\n" +
                "inner join ProductImgUrls on ProductImgUrls.productId = Products.id\n" +
                "left join BookMarks on BookMarks.productId = Products.id\n" +
                "group by Products.id";

        return this.jdbcTemplate.query(getMainProductsQuery,
                (rs, rowNum) -> new GetMainProductsRes(
                        rs.getInt("id"),
                        rs.getString("url1"),
                        rs.getString("price"),
                        rs.getString("productName"),
                        rs.getString("location"),
                        rs.getString("timeDiff"),
                        rs.getString("safePay"),
                        rs.getInt("bookmarkCnt"))
        );
    }


    // 상품 등록 태그 조회
    public List<GetTagsRes> getTags(String tagWord) {
        String getTagsQuery = "select word\n" +
                "from Words\n" +
                "where word like concat('%',?,'%')";
        String getTagsParams = tagWord;

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

    // 상품 등록
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


    public GetTradeInfoRes getTradeInfo(){
        String getTradeInfoQuery="select productNmae"
    }

}
