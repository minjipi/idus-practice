package com.hongminji.idus.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    //    이름
    public static boolean isRegexName(String target) {
        String regex = "^[ㄱ-ㅎ가-힣a-zA-Z]{1,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    //    닉네임
    public static boolean isRegexNickname(String target) {
        String regex = "^[a-z]{1,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    //    비밀번호
    public static boolean isRegexPw(String target) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    //    전화번호
    public static boolean isRegexPhoneNum(String phonNum) {
        String regex = "^[0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phonNum);
        return matcher.find();
    }


    //    이메일
    public static boolean isRegexEmail(String target) {
        String regex = "^(?=.{1,20}$)[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }
}
