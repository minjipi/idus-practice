package com.hongminji.idus.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    //    1000 : 요청 성공
    SUCCESS(true, 1000, "요청 성공."),

    //    2000 : Request 오류
    //    JWT 인증 오류
    ALREADY_LOGOUT_MEMBER(false, 2001, "이미 로그아웃 된 사용자입니다."),
    TOKEN_MEMBERINFO_NOT_EQUAL(false, 2002, "토큰의 유저 정보가 일치하지 않습니다."),
    EMPTY_JWT(false, 2003, "JWT를 입력해주세요."),
    INVALID_USER(false, 2004, "잘못된 로그인입니다."),
    INVALID_USER_JWT(false, 2005, "권한이 없는 유저의 접근입니다."),

    //    [POST] /member/signup
    POST_MEMBER_NAME_NULL(false, 2011, "이름은 필수 정보입니다."),
    POST_MEMBER_NICKNAME_NULL(false, 2012, "닉네임은 필수 정보 입니다."),
    POST_MEMBER_PW_NULL(false, 2013, "비밀번호를 설정해주세요"),
    POST_MEMBER_PHONENUM_NULL(false, 2014, "핸드폰번호는 필수 정보 입니다."),
    POST_MEMBER_EMAIL_NULL(false, 2015, "이메일은 필수 정보 입니다."),

    INVALID_NAME(false, 2016, "이름은 한글, 영문 대소문자만 입력 가능합니다."),
    INVALID_NICKNAME(false, 2017, "별명은 영문 소문자만 입력 가능합니다."),
    INVALID_PW(false, 2018, "비밀번호는 10자 이상, 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함해야 합니다."),
    INVALID_PHONENUM(false, 2019, "휴대폰번호는 -를 제외한 숫자만 입력해주세요."),
    INVALID_EMAIL(false, 2020, "올바른 형식의 이메일을 입력해주세요."),
    DUPLICATED_EMAIL(false, 2021, "중복된 이메일입니다."),
    DUPLICATED_PHONENUMBER(false, 2022, "이미 가입되어 있는 핸드폰 번호 입니다."),
    FAILED_TO_LOGIN(false, 2023, "없는 아이디거나 비밀번호가 틀렸습니다."),


    // 7000
    FAIL(false, 7000, "실패.");


    private final boolean success;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
