package com.hongminji.idus.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostLoginReq {

    @NotNull
    @Size(min = 3, max = 50)
    @Schema(description = "회원 email을 입력해주세요.", defaultValue = "minji1@minji.com")
    private String email;

    @NotNull
    @Size(min = 3, max = 100)
    @Schema(description = "회원 비밀번호를 입력해주세요.")
    private String password;
}