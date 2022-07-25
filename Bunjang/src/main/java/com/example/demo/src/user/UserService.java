package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.example.demo.config.BaseResponseStatus.*;

/**
 * Service란?
 * Controller에 의해 호출되어 실제 비즈니스 로직과 트랜잭션을 처리: Create, Update, Delete 의 로직 처리
 * 요청한 작업을 처리하는 관정을 하나의 작업으로 묶음
 * dao를 호출하여 DB CRUD를 처리 후 Controller로 반환
 */
@Service    // [Business Layer에서 Service를 명시하기 위해서 사용] 비즈니스 로직이나 respository layer 호출하는 함수에 사용된다.
            // [Business Layer]는 컨트롤러와 데이터 베이스를 연결
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log 처리부분: Log를 기록하기 위해 필요한 함수입니다.

    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!


    @Autowired //readme 참고
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!

    }


    // 회원정보 수정(Patch)
    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
        try {
            int result = userDao.modifyUserName(patchUserReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 회원 상점 정보 수정
    public void modifyUserStoreInfo(int userIdx, PatchUserStoreInfoReq patchUserStoreInfoReq) throws BaseException {
        try {
            userDao.modifyUserStoreInfo(userIdx,patchUserStoreInfoReq);

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException{
            try {
                User user=userDao.getUserId(postLoginReq);
                int userId=user.getUserIdx();
                String jwt = jwtService.createJwt(userId);
                return new PostLoginRes(userId,jwt);

            } catch (Exception exception) {
                throw new BaseException(DATABASE_ERROR);
            }
        }


    public PostLoginRes sighIn(PostLoginReq postLoginReq) throws BaseException{
            try {
                int userIdx = userDao.createUser(postLoginReq);

                String jwt = jwtService.createJwt(userIdx);
                return new PostLoginRes(userIdx,jwt);

            } catch (Exception exception) {
                throw new BaseException(DATABASE_ERROR);
            }
        }


    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String api_key = "NCSRPJHD6ZZAZPTB";
        String api_secret = "8WCBPFCPUWKOFTRHXEID9BGCZDBE3BLA";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01046186779");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "본인확인\n" +
                "인증번호" + "("+cerNum+")" + "입력시\n" +
                "정상처리 됩니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }

    public void createAuth(PostAuthNumReq postAuthNumReq)throws BaseException{
        try {
            userDao.createAuth(postAuthNumReq);
        } catch (Exception exception) {
        throw new BaseException(DATABASE_ERROR);
    }
    }

    public void deleteAuth(PostAuthNumReq postAuthNumReq)throws BaseException{
        try {
            userDao.deleteAut(postAuthNumReq);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
