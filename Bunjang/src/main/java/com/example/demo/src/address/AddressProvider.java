package com.example.demo.src.address;

import com.example.demo.config.BaseException;
import com.example.demo.src.address.model.GetAddressRes;
import com.example.demo.src.address.model.GetSelectAddressRes;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class AddressProvider {

    private final AddressDao addressDao;
    private final JwtService jwtService;

    @Autowired
    public AddressProvider(AddressDao addressDao, JwtService jwtService) {
        this.addressDao = addressDao;
        this.jwtService = jwtService;
    }

    public List<GetAddressRes> getAddresses(int userId) throws BaseException {
        try {
            return addressDao.getAddresses(userId);
        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetAddressRes getAddress(int userId, String addressName) throws BaseException {
        try {
            return addressDao.getAddress(userId, addressName);
        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSelectAddressRes> getSelectAddresses(int userId) throws BaseException {
        try {
            return addressDao.getSelectAddress(userId);
        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
