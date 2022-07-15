package com.hongminji.idus.member.dto;

import com.hongminji.idus.member.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "회원 검색 응답 DTO")
@Builder
@Getter
@Setter
@AllArgsConstructor
public class GetMemberSearchRes {
    private Long id;
    private String name;
    private String nickname;
    private String phoneNum;
    private String email;
    private Gender gender;
    private String orderNo;
    private String productName;
    private LocalDateTime createdAt;
}
