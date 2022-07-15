package com.hongminji.idus.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PostLoginRes {
    private String accessToken;
    private String refreshToken;
}
