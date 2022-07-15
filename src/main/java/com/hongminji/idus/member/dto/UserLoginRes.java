package com.hongminji.idus.member.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.math.BigInteger;
import java.util.Collection;

@Getter
@Setter

public class UserLoginRes extends User {
    BigInteger idx;
    String email;
    String nickname;

    public UserLoginRes(BigInteger idx, String username, String password, String nickname, Collection<?
            extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.idx = idx;
        this.email = username;
        this.nickname = nickname;
    }
}
