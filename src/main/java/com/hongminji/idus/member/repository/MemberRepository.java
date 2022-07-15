package com.hongminji.idus.member.repository;

import com.hongminji.idus.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, SearchMemberRepository {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPhoneNum(String phoneNum);
    Page<Member> findAll(Pageable pageable);

}
