package com.hongminji.idus.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class GetOrderRes {
    private String orderNo;
    private String productName;
    private LocalDateTime createdAt;
}
