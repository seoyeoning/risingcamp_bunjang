package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.GetOrderRes;
import com.example.demo.src.order.model.PostOrderReq;
import com.example.demo.src.user.model.PostLoginRes;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bunjang/orders")
public class OrderController {

    @Autowired
    private final OrderProvider orderProvider;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final JwtService jwtService;

    public OrderController(OrderProvider orderProvider, OrderService orderService, JwtService jwtService) {
        this.orderProvider = orderProvider;
        this.orderService = orderService;
        this.jwtService = jwtService;
    }

    //전체 구매내역 조회
    @ResponseBody
    @GetMapping("/{userId}")
    public BaseResponse<List<GetOrderRes>> getOrders(@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(orderProvider.getOrders(userId));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    //취소/환불 구매내역 조회
    @ResponseBody
    @GetMapping("/{userId}/cancel")
    public BaseResponse<List<GetOrderRes>> getOrdersCancel(@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(orderProvider.getOrdersCancel(userId));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    //진행중 구매내역 조회
    @ResponseBody
    @GetMapping("/{userId}/progress")
    public BaseResponse<List<GetOrderRes>> getOrdersProgress(@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(orderProvider.getOrdersProgress(userId));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    //완료된 구매내역 조회
    @ResponseBody
    @GetMapping("/{userId}/complete")
    public BaseResponse<List<GetOrderRes>> getOrdersComplete(@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(orderProvider.getOrdersComplete(userId));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
    @ResponseBody
    @GetMapping("/{id}/{userId}")
    public BaseResponse getOrderParcel(@PathVariable("id") int id,@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(orderProvider.getOrderParcel(id,userId));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/directs/{id}/{userId}")
    public BaseResponse getDirectOrder(@PathVariable("id") int id,@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(orderProvider.getDirectOrder(id,userId));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse PostParcelOrder(@RequestBody PostOrderReq postOrderReq){
        try {
            orderService.postParcelOrder(postOrderReq);
            String result="결제 성공";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("/directs")
    public BaseResponse PostDirectOrder(@RequestBody PostOrderReq postOrderReq){
        try {
            orderService.postDirectOrder(postOrderReq);
            String result="결제 성공";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/parcelTrades/{id}/{userId}")
    public BaseResponse getTradeInfo(@PathVariable("id") int id,@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(orderProvider.getTradeInfo(id,userId));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/directTrades/{id}/{userId}")
    public BaseResponse getDirectTradeInfo(@PathVariable("id") int id,@PathVariable("userId") int userId){
        try {
            return new BaseResponse<>(orderProvider.getDirectTradeInfo(id, userId));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
