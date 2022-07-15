package com.hongminji.idus.member.repository;

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

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save(){
        Member member = new Member();
        member.setName("member01");
        member.setNickname("member01");
        member.setPassword("qwer1234");
        member.setPhoneNum("01012345678");
        member.setEmail("member01@idus.com");
        member.setGender(Gender.M);
        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByEmail(){
        Member member1 = new Member();
        member1.setName("member01");
        member1.setNickname("member01");
        member1.setPassword("qwer1234");
        member1.setPhoneNum("01011111111");
        member1.setEmail("member01@idus.com");
        member1.setGender(Gender.M);
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("member02");
        member2.setNickname("member02");
        member2.setPassword("qwer1234");
        member2.setPhoneNum("01022222222");
        member2.setEmail("member02@idus.com");
        member2.setGender(Gender.M);
        memberRepository.save(member2);

        Member result = memberRepository.findByEmail("member01@idus.com").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    @DisplayName("여러 사용자 확인.")
    public void findAll(){
        Member member1 = new Member();
        member1.setName("member01");
        member1.setNickname("member01");
        member1.setPassword("qwer1234");
        member1.setPhoneNum("01011111111");
        member1.setEmail("member01@idus.com");
        member1.setGender(Gender.M);
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("member02");
        member2.setNickname("member02");
        member2.setPassword("qwer1234");
        member2.setPhoneNum("01022222222");
        member2.setEmail("member02@idus.com");
        member2.setGender(Gender.M);
        memberRepository.save(member2);

        Pageable pageable = PageRequest.of(1, 5);
        Page<Member> result = memberRepository.findAll(pageable);

        assertThat(result.getTotalElements()).isEqualTo(2);

    }

    @Test
    @DisplayName("여러 사용자 및 페이지 확인.")
    public void findAllWithPage(){
        Member member1 = new Member();
        member1.setName("member01");
        member1.setNickname("member01");
        member1.setPassword("qwer1234");
        member1.setPhoneNum("01011111111");
        member1.setEmail("member01@idus.com");
        member1.setGender(Gender.M);
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("member02");
        member2.setNickname("member02");
        member2.setPassword("qwer1234");
        member2.setPhoneNum("01022222222");
        member2.setEmail("member02@idus.com");
        member2.setGender(Gender.M);
        memberRepository.save(member2);

        Member member3 = new Member();
        member3.setName("member03");
        member3.setNickname("member03");
        member3.setPassword("qwer1234");
        member3.setPhoneNum("01033333333");
        member3.setEmail("member03@idus.com");
        member3.setGender(Gender.M);
        memberRepository.save(member3);

        Member member4 = new Member();
        member4.setName("member04");
        member4.setNickname("member04");
        member4.setPassword("qwer1234");
        member4.setPhoneNum("01044444444");
        member4.setEmail("member04@idus.com");
        member4.setGender(Gender.M);
        memberRepository.save(member4);


        Pageable pageable = PageRequest.of(0, 3);
        Page<Member> result = memberRepository.findAll(pageable);

        assertThat(result.getNumberOfElements()).isEqualTo(3);

        pageable = PageRequest.of(1, 3);
        result = memberRepository.findAll(pageable);

        assertThat(result.getNumberOfElements()).isEqualTo(1);

    }
}
