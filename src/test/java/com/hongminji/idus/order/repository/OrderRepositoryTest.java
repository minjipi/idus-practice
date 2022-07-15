//package com.hongminji.idus.order.repository;
//
//import com.hongminji.idus.member.dto.GetMemberRes;
//import com.hongminji.idus.member.dto.PostSignupReq;
//import com.hongminji.idus.member.dto.PostSignupRes;
//import com.hongminji.idus.member.entity.Member;
//import com.hongminji.idus.member.enums.Gender;
//import com.hongminji.idus.member.repository.MemberRepository;
//import com.hongminji.idus.member.service.MemberService;
//import com.hongminji.idus.order.entity.Order;
//import com.hongminji.idus.order.service.OrderService;
//import org.aspectj.weaver.ast.Or;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Transactional
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class OrderRepositoryTest {
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private OrderService orderService;
//
//    @Test
//    public void test() throws Exception {
//        Member member = Member.builder()
//                .name("member01")
//                .nickname("member01")
//                .password("qwer1234")
//                .phoneNum("01012345678")
//                .email("member01@idus.com")
//                .gender(Gender.M)
//                .build();
//        Member member1 = memberRepository.save(member);
//        Order order = Order.builder()
//                .orderNo("test01")
//                .productName("test01")
//                .createdAt(LocalDateTime.now())
//                .member(member1)
//                .build();
//        orderRepository.save(order);
//
//        Order result = orderRepository.findByOrderNo("test01").get();
//
//        assertThat(result.getOrderNo()).isEqualTo(order.getOrderNo());
//    }
//
//
//
//
//
//}