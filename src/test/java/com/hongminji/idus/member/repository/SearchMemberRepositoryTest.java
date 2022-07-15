package com.hongminji.idus.member.repository;

import com.hongminji.idus.member.dto.GetMemberSearchReq;
import com.hongminji.idus.member.dto.GetMemberSearchRes;
import com.hongminji.idus.member.entity.Member;
import com.hongminji.idus.member.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SearchMemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testSearch1(){
        GetMemberSearchReq getMemberSearchReq = GetMemberSearchReq.builder()
                .email("member01")
                .page(0)
                .size(2).build();
        Page<GetMemberSearchRes> getMemberSearchResPage = memberRepository.searchPageMemberWithOrder(getMemberSearchReq);
        for(GetMemberSearchRes getMemberSearchRes:getMemberSearchResPage.getContent()) {
            System.out.println(getMemberSearchRes.getEmail());
            System.out.println(getMemberSearchRes.getOrderNo());

        }
    }

}
