package com.example.demo.src.order;

import com.example.demo.src.address.model.GetAddressRes;
import com.example.demo.src.order.model.GetOrderRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //전체 구매내역 조회
    public List<GetOrderRes> getOrders(int userId){
        String getOrdersQuery="select url1, productName, Orders.status, storeName,price, date_format(Orders.updateAt,'%Y.%m.%d (%r)')\n" +
                "from Products join ProductImgUrls on ProductImgUrls.productId=Products.id\n" +
                "join Stores S on Products.userId = S.userId\n" +
                "join Orders on Orders.productId=Products.id\n" +
                "where Orders.userId=?";
        return this.jdbcTemplate.query(getOrdersQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getString("url1"),
                        rs.getString("productName"),
                        rs.getString("status"),
                        rs.getString("storeName"),
                        rs.getString("price"),
                        rs.getString("date")
                ),
                userId);
    }

    //취소/환불 조회
    public List<GetOrderRes> getOrdersCancel(int userId){
        String getOrdersCancelQuery="select url1, productName, Orders.status, storeName,price, date_format(Orders.updateAt,'%Y.%m.%d (%r)')\n" +
                "from Products join ProductImgUrls on ProductImgUrls.productId=Products.id\n" +
                "join Stores S on Products.userId = S.userId\n" +
                "join Orders on Orders.productId=Products.id\n" +
                "where Orders.userId=? and Orders.status=? and Orders.status=? ";
        return this.jdbcTemplate.query(getOrdersCancelQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getString("url1"),
                        rs.getString("productName"),
                        rs.getString("status"),
                        rs.getString("storeName"),
                        rs.getString("price"),
                        rs.getString("date")
                ),
                userId,"환불완료","결제취소");
    }

    //진행중
    public List<GetOrderRes> getOrdersProgress(int userId){
        String getOrdersQuery="select url1, productName, Orders.status, storeName,price, date_format(Orders.createAt,'%Y.%m.%d (%r)')\n" +
                "from Products join ProductImgUrls on ProductImgUrls.productId=Products.id\n" +
                "join Stores S on Products.userId = S.userId\n" +
                "join Orders on Orders.productId=Products.id\n" +
                "where Orders.userId=? and Orders.status=?";
        return this.jdbcTemplate.query(getOrdersQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getString("url1"),
                        rs.getString("productName"),
                        rs.getString("status"),
                        rs.getString("storeName"),
                        rs.getString("price"),
                        rs.getString("date")
                ),
                userId,"진행중");
    }

    //완료
    public List<GetOrderRes> getOrdersComplete(int userId) {
        String getOrdersQuery = "select url1, productName, Orders.status, storeName,price, date_format(Orders.createAt,'%Y.%m.%d (%r)')\n" +
                "from Products join ProductImgUrls on ProductImgUrls.productId=Products.id\n" +
                "join Stores S on Products.userId = S.userId\n" +
                "join Orders on Orders.productId=Products.id\n" +
                "where Orders.userId=? and Orders.status=?";
        return this.jdbcTemplate.query(getOrdersQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getString("url1"),
                        rs.getString("productName"),
                        rs.getString("status"),
                        rs.getString("storeName"),
                        rs.getString("price"),
                        rs.getString("date")
                ),
                userId, "결제완료");
    }
}
