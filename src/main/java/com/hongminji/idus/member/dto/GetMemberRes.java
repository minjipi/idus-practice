package com.hongminji.idus.member.dto;

import com.hongminji.idus.member.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "회원 응답 DTO")
@Builder
@Getter
@Setter
@AllArgsConstructor
public class GetMemberRes {
    private Long id;

    @Schema(description = "회원 이름", defaultValue = "민지")
    private String name;

    private String nickname;
    private String phoneNum;
    private String email;
    private Gender gender;
}
