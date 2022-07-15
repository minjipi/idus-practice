package com.hongminji.idus.member.service;

import com.hongminji.idus.config.BaseException;
import com.hongminji.idus.jwt.JwtFilter;
import com.hongminji.idus.jwt.TokenProvider;
import com.hongminji.idus.member.dto.*;
import com.hongminji.idus.member.entity.DeniedToken;
import com.hongminji.idus.member.entity.RefreshToken;
import com.hongminji.idus.member.repository.DeniedTokenRepository;
import com.hongminji.idus.member.repository.MemberRepository;
import com.hongminji.idus.member.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hongminji.idus.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final DeniedTokenRepository deniedTokenRepository;


    @Transactional
    public PostSignupRes signup(PostSignupReq postSignupReq) {
//        같은 email 중복 x
        memberRepository.findByEmail(postSignupReq.getEmail()).ifPresent(m -> {
            throw new BaseException(DUPLICATED_EMAIL);
        });

//        번호 중복 x
        memberRepository.findByPhoneNum(postSignupReq.getPhoneNum()).ifPresent(m -> {
            throw new BaseException(DUPLICATED_PHONENUMBER);
        });

        postSignupReq.setPassword(passwordEncoder.encode(postSignupReq.getPassword()));
        return new PostSignupRes(memberRepository.save(postSignupReq.toEntity()).getId());
    }


    @Transactional
    public PostLoginRes login(PostLoginReq postLoginReq) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(postLoginReq.getEmail(), postLoginReq.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        PostLoginRes postLoginRes = tokenProvider.createToken(authentication.getName());

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(postLoginRes.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + postLoginRes.getAccessToken());

        return postLoginRes;
    }


    @Transactional
    public PostLoginRes refresh(PostRefreshReq postRefreshReq) {
        Authentication authentication = tokenProvider.getAuthentication(postRefreshReq.getAccessToken());
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new BaseException(ALREADY_LOGOUT_MEMBER));
        if (!refreshToken.getValue().equals(postRefreshReq.getRefreshToken())) {
            throw new BaseException(TOKEN_MEMBERINFO_NOT_EQUAL);
        }
        PostLoginRes postLoginRes = tokenProvider.createToken(authentication.getName());

        RefreshToken newRefreshToken = refreshToken.updateValue(postLoginRes.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);
        return postLoginRes;
    }

    @Transactional
    public String logout(String email, String jwt) {
        return deniedTokenRepository.save(DeniedToken.builder()
                .accessToken(jwt)
                .email(email)
                .build()
        ).getEmail();
    }

    @Transactional(readOnly = true)
    public GetMemberRes getMemberDetail(Long id) {
        return memberRepository.findById(id).get().toGetMemberRes();
    }

    @Transactional(readOnly = true)
    public GetMemberRes getMemberDetail(String email) {
        return memberRepository.findByEmail(email).get().toGetMemberRes();
    }

    @Transactional(readOnly = true)
    public Page<GetMemberSearchRes> getMemberlist(GetMemberSearchReq getMemberSearchReq) {
        return memberRepository.searchPageMemberWithOrder(getMemberSearchReq);
    }
}
