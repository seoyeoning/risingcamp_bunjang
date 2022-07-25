package com.example.demo.src.address;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.address.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bunjang/addresses")
public class AddressController {

    @Autowired
    private final AddressProvider addressProvider;
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final JwtService jwtService;


    public AddressController(AddressProvider addressProvider, AddressService addressService, JwtService jwtService) {
        this.addressProvider = addressProvider;
        this.addressService = addressService;
        this.jwtService = jwtService;
    }

    //배송지 전체 조회
    @ResponseBody
    @GetMapping("/{userId}")
    public BaseResponse<List<GetAddressRes>> getAddresses(@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(addressProvider.getAddresses(userId));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //특정 배송지 조회
    @ResponseBody
    @GetMapping("/{userId}/{addressName}")
    public BaseResponse<GetAddressRes> getAddress(@PathVariable("userId") int userId,@PathVariable("addressName") String addressName){
        try {
            return new BaseResponse<>(addressProvider.getAddress(userId, addressName));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //주소 추가
    @ResponseBody
    @PostMapping("")
    public BaseResponse addAddress(@RequestBody PostAddressReq postAddressReq){
        try {
//            if (postAddressReq)
            addressService.addAddress(postAddressReq);
            return new BaseResponse<>("주소 추가 성공");
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //주소 수정
    @ResponseBody
    @PatchMapping("")
    public BaseResponse modifyAddress(@RequestBody PatchAddressReq patchAddressReq){
        try {
            addressService.modifyAddress(patchAddressReq);
            return new BaseResponse<>("주소 수정 성공");
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //주소 삭제
    @ResponseBody
    @DeleteMapping("")
    public BaseResponse deleteAddress(@RequestBody DeleteAddressReq deleteAddressReq){
        try {
            addressService.deleteAddress(deleteAddressReq);
            return new BaseResponse<>("주소 삭제 성공");
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //주소 선택 조회
    @ResponseBody
    @GetMapping("/orders/{userId}")
    public BaseResponse<List<GetSelectAddressRes>> getSelectAddresses(@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(addressProvider.getSelectAddresses(userId));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
