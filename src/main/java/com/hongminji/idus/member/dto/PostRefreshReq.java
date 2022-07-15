package com.hongminji.idus.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRefreshReq {
    @Schema(description = "로그인 시 발급 받은 accessToken 입니다.")
    private String accessToken;

    @Schema(description = "로그인 시 발급 받은 refreshToken 입니다.")
    private String refreshToken;
}