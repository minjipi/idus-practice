package com.hongminji.idus.order.controller;

import com.hongminji.idus.config.BaseResponse;
import com.hongminji.idus.order.dto.GetOrderRes;
import com.hongminji.idus.order.dto.PostOrderReq;
import com.hongminji.idus.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "주문", tags = "주문")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "주문 생성", description = "회원가입이 필수 입니다.")
    public BaseResponse<String> createOrder(@RequestBody PostOrderReq postOrderReq) {
        return new BaseResponse<>(orderService.createOrder(postOrderReq));
    }

    @GetMapping("/{email}")
    @Operation(summary = "단일 회원의 주문 목록 조회", description = "이메일로 단일 회원의 주문 목록을 조회 합니다. 회원가입이 필수 입니다.")
    public BaseResponse<List<GetOrderRes>> readOrderByEmail(@PathVariable String email) {
        return new BaseResponse<>(orderService.readOrderByEmail(email));
    }
}
