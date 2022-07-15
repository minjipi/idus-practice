package com.hongminji.idus.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "주문 생성 요청 DTO")
public class PostOrderReq {
    @Schema(description = "회원 이메일", defaultValue = "minji1@minji.com")
    private String email;

    @Schema(description = "주문할 상품명. emoji 를 포함한 모든 문자를 입력할 수 있습니다.", defaultValue = "하트목걸이❤️")
    private String productName;
}
