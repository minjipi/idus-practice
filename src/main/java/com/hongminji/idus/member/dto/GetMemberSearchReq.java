package com.hongminji.idus.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "회원 검색 요청 DTO")
@Builder
@Getter
@AllArgsConstructor
public class GetMemberSearchReq {
    @Schema(description = "시작 페이지를 입력해주세요.", defaultValue = "1")
    private Integer page;

    @Schema(description = "마지막 페이지를 입력해주세요.")
    private Integer size;

    @Schema(description = "검색할 회원의 이름을 입력해주세요.")
    private String name;

    @Schema(description = "검색할 회원의 이메일을 입력해주세요.")
    private String email;
}
