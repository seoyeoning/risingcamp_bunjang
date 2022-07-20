package com.example.demo.src.bookmark;

import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class BookmarkDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 찜 추가
    public int createBookmark(int storeIdx, int productIdx) {
        String createBookmarkQuery = "INSERT INTO bunjang.BookMarks (storeId, productId ) VALUES (?, ?)";
        Object[] createBookmarkParams = new Object[]{storeIdx,productIdx};
        return this.jdbcTemplate.update(createBookmarkQuery,createBookmarkParams);
    }
    // 찜 여부 확인
    public int checkBookmark(int storeIdx, int productIdx) {
        String checkBookmarkQuery = "select exists(select *\n" +
                "from BookMarks\n" +
                "where BookMarks.storeId = ? and BookMarks.productId = ?)";
        Object[] checkBookmarkParams = new Object[]{storeIdx,productIdx};
        return this.jdbcTemplate.queryForObject(checkBookmarkQuery,
                int.class,
        checkBookmarkParams);
    }
    // 찜 취소
    public int patchBookmark(int storeIdx, int productIdx) {
        String patchBookmarkQuery = "DELETE FROM bunjang.BookMarks WHERE storeId = ? and productId = ?";
        Object[] patchBookmarkParams = new Object[]{storeIdx,productIdx};
        return this.jdbcTemplate.update(patchBookmarkQuery,patchBookmarkParams);
    }



}
