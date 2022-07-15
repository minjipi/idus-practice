package com.hongminji.idus.member.entity;

import com.hongminji.idus.member.dto.GetMemberRes;
import com.hongminji.idus.member.enums.Gender;
import com.hongminji.idus.order.entity.Order;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false, unique = true)
    private String phoneNum;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 1)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<Order>();

    public void addOrder(Order order) {
        this.orderList.add(order);
        if (order.getMember() != this) {
            order.setMember(this);
        }
    }

    public GetMemberRes toGetMemberRes() {
        return GetMemberRes.builder()
                .id(id)
                .name(name)
                .nickname(nickname)
                .phoneNum(phoneNum)
                .email(email)
                .gender(gender)
                .build();
    }

}
