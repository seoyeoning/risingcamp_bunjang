package com.example.demo.src.account;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.account.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.demo.config.BaseResponseStatus.*;


@Service

public class AccountProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AccountDao accountDao;
    private final JwtService jwtService;

    @Autowired
    public AccountProvider(AccountDao accountDao, JwtService jwtService) {
        this.accountDao = accountDao;
        this.jwtService = jwtService;
    }

    // 유저의 등록된 계좌 갯수 확인
    public int checkAccountCnt(int userIdx) throws BaseException {
        try {
            int result = accountDao.createAccountCnt(userIdx);
            return result;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
