package com.hongminji.idus.member.controller;

import com.google.gson.Gson;
import com.hongminji.idus.config.BaseException;
import com.hongminji.idus.member.dto.PostSignupReq;
import com.hongminji.idus.member.entity.Member;
import com.hongminji.idus.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.hongminji.idus.config.BaseResponseStatus.INVALID_PHONENUM;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private MemberService memberService;
    private MemberController memberController;

    class DTO {
        class Fail {
            private final String name = "test1";
            private final String nickname = "test1";
            private final String password = "Test1!";
            private final String phone = "01012345678";
            private final String email = "test1@idus.com";
            private final String gender = "F";

            @Test
            @DisplayName("핸드폰번호_검증")
            void 핸드폰번호_regex() {
                PostSignupReq postSignupReq = PostSignupReq.builder()
                        .name(name)
                        .nickname(nickname)
                        .password(password)
                        .phoneNum("1")
                        .email(email)
                        .gender(gender)
                        .build();
            }
        }
    }
}
