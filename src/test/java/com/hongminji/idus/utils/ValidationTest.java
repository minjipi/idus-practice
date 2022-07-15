package com.hongminji.idus.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hongminji.idus.utils.Validation.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidationTest {
    @Test
    @DisplayName("회원 속성 양식 테스트.")
    void 이름검증() {
        String name = "홍민지";
        assertThat(isRegexName(name)).isTrue();

        name = "ghdalswl";
        assertThat(isRegexName(name)).isTrue();

        name = "Hongminji";
        assertThat(isRegexName(name)).isTrue();

//        틀려야함
//      name = "홍 민지";
//      assertThat(isRegexName(name)).isTrue();

//      name = "minj1";
//      assertThat(isRegexName(name)).isTrue();

    }

    @Test
    @DisplayName("별명 양식 테스트.")
    void 별명검증() {
        String name = "minji";
        assertThat(isRegexNickname(name)).isTrue();

//        name = "민지";
//        assertThat(isRegexNickname(name)).isTrue();
    }

//  영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함
    @Test
    @DisplayName("비밀번호 양식 테스트.")
    void 비밀번호검증() {
        String pw = "Minjiminjiminji1!";
        assertThat(isRegexPw(pw)).isTrue();

//        pw = "민지";
//        assertThat(isRegexNickname(pw)).isTrue();

//        pw = "Minjiminjiminji1";
//        assertThat(isRegexNickname(pw)).isTrue();
//
//        pw = "Minjiminjiminji!";
//        assertThat(isRegexNickname(pw)).isTrue();

//        pw = "minjiminjiminji1!";
//        assertThat(isRegexNickname(pw)).isTrue();

//        pw = "MINJIMINJIMINJI1!";
//        assertThat(isRegexNickname(pw)).isTrue();

//        pw = "MINJIMINJIMINJi1!";
//        assertThat(isRegexNickname(pw)).isTrue();
    }

    @Test
    @DisplayName("전화번호 양식 테스트.")
    void 전화번호() {
        String phoneNum = "01022829833";
        assertThat(isRegexPhoneNum(phoneNum)).isTrue();

//        phoneNum = "공0122829833";
//        assertThat(isRegexEmail(phoneNum)).isTrue();
    }

    @Test
    @DisplayName("이메일 양식 테스트.")
    void 이메일() {
        String email = "test01@idus.com";
        assertThat(isRegexEmail(email)).isTrue();

//        email = "test01idus.com";
//        assertThat(isRegexEmail(email)).isTrue();

//        email = "test민지@idus.com";
//        assertThat(isRegexEmail(email)).isTrue();
    }
}
