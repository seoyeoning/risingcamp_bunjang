package com.example.demo.src.bookmark;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.bookmark.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.demo.config.BaseResponseStatus.*;

@Service

public class BookmarkProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookmarkDao bookmarkDao;
    private final JwtService jwtService;

    @Autowired
    public BookmarkProvider(BookmarkDao bookmarkDao, JwtService jwtService) {
        this.bookmarkDao = bookmarkDao;
        this.jwtService = jwtService;
    }

    // 찜 여부 확인
    public int checkBookmark(int storeIdx, int productIdx) throws BaseException {
        try {
            return bookmarkDao.checkBookmark(storeIdx,productIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
