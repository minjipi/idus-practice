package com.hongminji.idus.member.controller;

import com.hongminji.idus.config.BaseException;
import com.hongminji.idus.config.BaseResponse;
import com.hongminji.idus.member.dto.*;
import com.hongminji.idus.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.hongminji.idus.config.BaseResponseStatus.*;
import static com.hongminji.idus.utils.Validation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "회원", tags = "회원")
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공")
    })
    public BaseResponse<PostSignupRes> signup(@RequestBody PostSignupReq postSignupReq) {
        try {
            if (postSignupReq.getName() == null) {
                return new BaseResponse<>(POST_MEMBER_NAME_NULL);
            }
            if (!isRegexName(postSignupReq.getName())) {
                return new BaseResponse<>(INVALID_NAME);
            }


            if (postSignupReq.getNickname() == null) {
                return new BaseResponse<>(POST_MEMBER_NICKNAME_NULL);
            }
            if (!isRegexNickname(postSignupReq.getNickname())) {
                return new BaseResponse<>(INVALID_NICKNAME);
            }


            if (postSignupReq.getPassword() == null) {
                return new BaseResponse<>(POST_MEMBER_PW_NULL);
            }
            if (!isRegexPw(postSignupReq.getPassword())) {
                return new BaseResponse<>(INVALID_PW);
            }


            if (postSignupReq.getPhoneNum() == null) {
                return new BaseResponse<>(POST_MEMBER_PHONENUM_NULL);
            }
            if (!isRegexPhoneNum(postSignupReq.getPhoneNum())) {
                return new BaseResponse<>(INVALID_PHONENUM);
            }


            if (postSignupReq.getEmail() == null) {
                return new BaseResponse<>(POST_MEMBER_EMAIL_NULL);
            }
            if (!isRegexEmail(postSignupReq.getEmail())) {
                return new BaseResponse<>(INVALID_EMAIL);
            }

            PostSignupRes postSignupRes = memberService.signup(postSignupReq);
            return new BaseResponse<>(postSignupRes);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq) {
        try {
            return new BaseResponse<>(memberService.login(postLoginReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("/refresh")
    public BaseResponse<PostLoginRes> refresh(@RequestBody PostRefreshReq postRefreshReq) {
        try {
            return new BaseResponse<>(memberService.refresh(postRefreshReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }

    @GetMapping("/logout")
    @Operation(summary = "로그아웃")
    public BaseResponse<String> logoutMember() {
        String jwt = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return new BaseResponse<>(memberService.logout(email, jwt));
    }

    @GetMapping("/list")
    @Operation(summary = "회원 목록 조회", description = "각 회원의 마지막 주문 정보가 함께 출력됩니다. 이름, 이메일을 이용하여 검색이 가능하며, 페이지네이션 일정 단위로 조회합니다. 검색어가 포함된 모든 회원 정보가 검색 됩니다. 기본 페이지는 1페이지부터 시작됩니다.")
    public BaseResponse<Page<GetMemberSearchRes>> list(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        GetMemberSearchReq getMemberSearchReq = GetMemberSearchReq.builder()
                .page(page - 1)
                .size(size)
                .name(name)
                .email(email)
                .build();
        return new BaseResponse<>(memberService.getMemberlist(getMemberSearchReq));
    }


    @GetMapping("/id/{id}")
    @Operation(summary = "단일 회원 id로 상세 조회", description = "회원 번호로 상세 정보를 조회합니다.")
    public BaseResponse<GetMemberRes> getMemberDetailById(@PathVariable("id") Long id) {
        return new BaseResponse<>(memberService.getMemberDetail(id));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "단일 회원 이름으로 상세 조회", description = "회원 이메일로 상세 정보를 조회합니다. 검색어와 정확히 일치하는 이메일의 회원 정보가 출력됩니다.")
    public BaseResponse<GetMemberRes> getMemberDetailByEmail(@PathVariable("email") String email) {
        return new BaseResponse<>(memberService.getMemberDetail(email));
    }


}


