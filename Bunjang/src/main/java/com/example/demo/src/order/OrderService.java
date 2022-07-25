package com.example.demo.src.order;

import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderProvider orderProvider;
    private final OrderDao orderDao;
    private final JwtService jwtService;

    @Autowired
    public OrderService(OrderProvider orderProvider, OrderDao orderDao, JwtService jwtService) {
        this.orderProvider = orderProvider;
        this.orderDao = orderDao;
        this.jwtService = jwtService;
    }


}
