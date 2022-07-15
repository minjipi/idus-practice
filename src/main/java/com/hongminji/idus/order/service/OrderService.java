package com.hongminji.idus.order.service;

import com.hongminji.idus.config.BaseException;
import com.hongminji.idus.member.entity.Member;
import com.hongminji.idus.member.repository.MemberRepository;
import com.hongminji.idus.order.dto.GetOrderRes;
import com.hongminji.idus.order.dto.PostOrderReq;
import com.hongminji.idus.order.entity.Order;
import com.hongminji.idus.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.hongminji.idus.config.BaseResponseStatus.DUPLICATED_EMAIL;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String createOrder(PostOrderReq postOrderReq) {
        Member member = memberRepository.findByEmail(postOrderReq.getEmail()).orElseThrow(() -> {
            throw new BaseException(DUPLICATED_EMAIL);
        });

        String uid = "";
        while (true) {
            uid = createUniqueValue();
            if (!orderRepository.findById(uid).isPresent()) {
                break;
            }
        }

        Order order = Order.builder()
                .orderNum(createUniqueValue())
                .productName(postOrderReq.getProductName())
                .createdAt(LocalDateTime.now())
                .build();
        order.setMember(member);
        orderRepository.save(order);
        return order.getOrderNum();
    }

    String createUniqueValue() {
        String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int currentYear = LocalDate.now().getYear() % 100;

        String uid = "";
        uid += currentYear;

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            uid += possible.charAt(rand.nextInt(possible.length()));
        }
        return uid;
    }


    @Transactional(readOnly = true)
    public List<GetOrderRes> readOrderByEmail(String email) {
        List<Order> orderList = orderRepository.findOrderByMemberEmail(email);
        return orderList.stream().map(Order::toGetOrderRes).collect(Collectors.toList());
    }


}
