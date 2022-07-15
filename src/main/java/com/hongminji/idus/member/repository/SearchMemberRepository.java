package com.hongminji.idus.member.repository;

import com.hongminji.idus.member.dto.GetMemberSearchReq;
import com.hongminji.idus.member.dto.GetMemberSearchRes;
import org.springframework.data.domain.Page;

public interface SearchMemberRepository {
    Page<GetMemberSearchRes> searchPageMemberWithOrder(GetMemberSearchReq getMemberSearchReq);

}
