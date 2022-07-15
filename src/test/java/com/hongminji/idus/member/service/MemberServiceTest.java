package com.hongminji.idus.member.service;

import com.hongminji.idus.config.BaseException;
import com.hongminji.idus.jwt.TokenProvider;
import com.hongminji.idus.member.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    TokenProvider tokenProvider;

    @Test
    @DisplayName("사용자 생성 성공")
    void 회원가입() {
        PostSignupReq postSignupReq = PostSignupReq.builder()
                .name("member01")
                .nickname("member01")
                .password("qwer1234")
                .phoneNum("01012345678")
                .email("member01@idus.com")
                .gender("M")
                .build();

        PostSignupRes postSignupRes = memberService.signup(postSignupReq);

        GetMemberRes getMemberRes = memberService.getMemberDetail(postSignupRes.getId());
        assertThat(postSignupReq.getEmail()).isEqualTo(getMemberRes.getEmail());
    }

    @Test
    @DisplayName("실패. 중복된 이메일")
    public void 중복_회원_예외() {
        PostSignupReq postSignupReq1 = PostSignupReq.builder()
                .name("member01")
                .nickname("member01")
                .password("qwer1234")
                .phoneNum("01011111111")
                .email("member01@idus.com")
                .gender("M")
                .build();

        try {
            memberService.signup(postSignupReq1);
            fail("중복 발생");
        } catch (BaseException e) {
            assertThat(e.getStatus().isSuccess()).isEqualTo(false);
            assertThat(e.getStatus().getCode()).isEqualTo(2001);
            assertThat(e.getStatus().getMessage()).isEqualTo("중복된 이메일입니다.");
        }

    }

    @Test
    @DisplayName("로그인 성공")
    public void 로그인() {
        PostLoginReq postLoginReq = PostLoginReq.builder()
                .email("member01@idus.com")
                .password("qwer1234")
                .build();

        PostLoginRes postLoginRes1 = tokenProvider.createToken("member01@idus.com");
        PostLoginRes postLoginRes2 = memberService.login(postLoginReq);
        assertThat(postLoginRes1.getAccessToken()).isEqualTo(postLoginRes2.getAccessToken());
    }

//    @Test
//    @DisplayName("여러 회원 목록 페이지네이션 조회.")
//    public void 목록_조회_페이지네이션() {
//
//        for(int i =2; i<101; i++) {
//            PostSignupReq postSignupReq = PostSignupReq.builder()
//                    .name("member"+i)
//                    .nickname("member"+i)
//                    .password("qwer1234")
//                    .phoneNum("010"+String.format("%08d",i))
//                    .email("member"+i+"@idus.com")
//                    .gender("M")
//                    .build();
//
//            memberService.signup(postSignupReq);
//        }
//
//        Pageable pageable = PageRequest.of(0, 3);
//        Page<GetMemberRes> result =  memberService.getMemberlist(pageable);
//
//        assertThat(result.getNumberOfElements()).isEqualTo(3);
//        assertThat(result.getTotalPages()).isEqualTo(34);
//
//    }

}