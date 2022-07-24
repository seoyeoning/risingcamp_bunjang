package com.example.demo.src.account;

import com.example.demo.src.account.model.PostUserAccountReq;
import com.example.demo.src.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    String a = "A";
    String d = "D";
    // 유저의 계좌 개수 조회
    public int createAccountCnt(int userIdx) {
        String createAccountCntQuery = "select count(Account.id)\n" +
                "from Account\n" +
                "where Account.usrId = ?";
        int createAccountCntParams = userIdx;
        return this.jdbcTemplate.queryForObject(createAccountCntQuery,
                int.class,
                createAccountCntParams);
    }

    //  // 계좌가 없는 경우 무조건 대표계좌  'A' 로 넣기
    public int creatUserAccountForA(int userIdx, PostUserAccountReq postUserAccountReq) {
        String creatUserAccountForAQuery = "INSERT INTO bunjang.Account (usrId, accountHolder, bankId, accountNum, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        Object[] creatUserAccountForAParams = new Object[]{userIdx,postUserAccountReq.getAccountHolder(),postUserAccountReq.getBankId(),
        postUserAccountReq.getAccountNum(),a};

        return this.jdbcTemplate.update(creatUserAccountForAQuery,creatUserAccountForAParams);
    }

    //계좌를 1개 등록한 경우 A로 값이 들어오면 A로 넣고 원래 있던 계좌는 D로 바꾸기
    public int createUserAccountModify(int userIdx, PostUserAccountReq postUserAccountReq) {
        // 유저의 첫번째 계좌 id 추출
        String userFirstAccountIdQuery = "select Account.id as accountId\n" +
                "from Account\n" +
                "where Account.usrId = ?";
        int userFirstAccountIdParams = userIdx;

        int userFirstAccountId = this.jdbcTemplate.queryForObject(userFirstAccountIdQuery,
                int.class,
                userFirstAccountIdParams);

        //첫번째 계좌 D로 바꾸기
        String modifyForDQuery = "UPDATE bunjang.Account t SET t.status = 'D' WHERE t.id = ?";
        int modifyForDParams = userFirstAccountId;
        this.jdbcTemplate.update(modifyForDQuery,modifyForDParams);

        // 두번째 계좌 A로 등록
        String createUserAccountModifyQuery = "INSERT INTO bunjang.Account (usrId, accountHolder, bankId, accountNum, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        Object[] createUserAccountModifyParams = new Object[]{userIdx,postUserAccountReq.getAccountHolder(),postUserAccountReq.getBankId(),
                postUserAccountReq.getAccountNum(),a};

        return this.jdbcTemplate.update(createUserAccountModifyQuery,createUserAccountModifyParams);
    }

    // 계좌가 1개있고 status가 D인 경우 그냥 D 넣기
    public int createUserAccountForD(int userIdx, PostUserAccountReq postUserAccountReq) {
        String createUserAccountForDQuery = "INSERT INTO bunjang.Account (usrId, accountHolder, bankId, accountNum, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        Object[] createUserAccountForDParams = new Object[]{userIdx,postUserAccountReq.getAccountHolder(),postUserAccountReq.getBankId(),
                postUserAccountReq.getAccountNum(),d};

        return this.jdbcTemplate.update(createUserAccountForDQuery,createUserAccountForDParams);
    }

}
