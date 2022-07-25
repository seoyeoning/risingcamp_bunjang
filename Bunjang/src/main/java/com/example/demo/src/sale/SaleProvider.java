package com.example.demo.src.sale;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.GetOrderRes;
import com.example.demo.src.sale.model.GetSaleRes;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class SaleProvider {

    private final JwtService jwtService;
    private final SaleDao saleDao;


    @Autowired
    public SaleProvider(JwtService jwtService, SaleDao saleDao) {
        this.jwtService = jwtService;
        this.saleDao = saleDao;
    }

    public List<GetSaleRes> getSales(int userId)throws BaseException {
        try {
            return saleDao.getSales(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSaleRes> getSalesCancel(int userId)throws BaseException {
        try {
            return saleDao.getSalesCancel(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSaleRes> getSalesProgress(int userId)throws BaseException {
        try {
            return saleDao.getSalesProgress(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSaleRes> getSalesComplete(int userId)throws BaseException {
        try {
            return saleDao.getSalesComplete(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
