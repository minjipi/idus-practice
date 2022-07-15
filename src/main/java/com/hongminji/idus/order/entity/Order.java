package com.hongminji.idus.order.entity;
import com.hongminji.idus.member.entity.Member;
import com.hongminji.idus.order.dto.GetOrderRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "orders")
public class Order {
    @Id
    @Column(length = 12, unique = true, nullable = false)
    private String orderNum;

    @Column(length = 100, nullable = false)
    private String productName;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member){
        if (this.member != null) {
            this.member.getOrderList().remove(this);
        }
        this.member = member;
        member.getOrderList().add(this);
    }

    public GetOrderRes toGetOrderRes() {
        return GetOrderRes.builder()
                .orderNo(orderNum)
                .productName(productName)
                .createdAt(createdAt)
                .build();
    }
}
