package com.example.demo.src.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.account.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/bunjang/accounts")

public class AccountController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final AccountProvider accountProvider;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final JwtService jwtService;

    public AccountController(AccountProvider accountProvider, AccountService accountService, JwtService jwtService) {
        this.accountProvider = accountProvider;
        this.accountService = accountService;
        this.jwtService = jwtService;
    }

    /**
     * 계좌 추가 API
     * [POST] /:userIdx/new-account
     */
    @ResponseBody
    @PostMapping("/{userIdx}/new-account")
    public BaseResponse<String> creatUserAccount(@PathVariable("userIdx") int userIdx,@RequestBody PostUserAccountReq postUserAccountReq) {
        try {
            accountService.creatUserAccount(userIdx,postUserAccountReq);

            String result = "유저" + userIdx + "의 계좌가 추가되었습니다.";
            return new BaseResponse<>(result);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
