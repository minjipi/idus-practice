package com.hongminji.idus.member.repository;


import com.hongminji.idus.member.dto.GetMemberSearchReq;
import com.hongminji.idus.member.dto.GetMemberSearchRes;
import com.hongminji.idus.member.entity.QMember;
import com.hongminji.idus.order.entity.QOrder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchMemberRepositoryImpl  implements SearchMemberRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<GetMemberSearchRes> searchPageMemberWithOrder(GetMemberSearchReq getMemberSearchReq) {
        QMember member = QMember.member;
        QOrder o1 = new QOrder("o1");
        QOrder o2 = new QOrder("o2");

        //SELECT member.id, member.name, member.nickname, member.phone_num, member.email, member.gender, o1.order_no, o1.product_name, o1.created_at FROM member LEFT JOIN orders AS o1 ON member.id = o1.member_id LEFT JOIN orders AS o2 ON member.id = o2.member_id AND o1.created_at < o2.created_at WHERE o2.created_at IS NULL;
        JPAQuery<GetMemberSearchRes> jpaQuery = jpaQueryFactory
                .select(Projections.constructor(GetMemberSearchRes.class,
                        member.id,
                        member.name,
                        member.nickname,
                        member.phoneNum,
                        member.email,
                        member.gender,
                        o1.orderNum,
                        o1.productName,
                        o1.createdAt)
                ).from(member)
                .leftJoin(o1).on(member.id.eq(o1.member.id))
                .leftJoin(o2).on(member.id.eq(o2.member.id).and(o1.createdAt.lt(o2.createdAt)))
                .where(o2.createdAt.isNull());


        if (getMemberSearchReq.getName() != null) {
            jpaQuery.where(member.name.contains(getMemberSearchReq.getName()));
        }

        if (getMemberSearchReq.getEmail() != null) {
            jpaQuery.where(member.email.contains(getMemberSearchReq.getEmail()));
        }


        jpaQuery.offset(getMemberSearchReq.getSize()*getMemberSearchReq.getPage()).limit(getMemberSearchReq.getSize());

        List<GetMemberSearchRes> result = jpaQuery.fetch();

        return new PageImpl<GetMemberSearchRes>(
                result.stream().collect(Collectors.toList()));
    }
}
