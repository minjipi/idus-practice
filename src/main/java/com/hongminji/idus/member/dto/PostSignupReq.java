package com.hongminji.idus.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.hongminji.idus.member.entity.Member;
import com.hongminji.idus.member.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "회원 요청 DTO")
public class PostSignupReq {
    @Size(max = 20, message = "이름을 20글자 이내로 입력해주세요.")
    @NotEmpty(message = "이름은 필수값 입니다.")
    @Schema(description = "회원 이름을 20글자 이내로 입력해주세요.", defaultValue = "민지")
    private String name;

    @Size(max = 20, message = "닉네임을 30글자 이내로 입력해주세요.")
    @NotEmpty(message = "별명은 필수값 입니다.")
    @Schema(description = "회원 닉네임을 30글자 이내로 입력해주세요.", defaultValue = "민지닉네임")
    private String nickname;

    @Size(min = 10, message = "비밀번호를 10글자 이상으로 입력해주세요.")
    @NotEmpty(message = "비밀번호는 필수값 입니다.")
    @Schema(description = "비밀번호를 10글자 이상으로 입력해주세요.", defaultValue = "민지닉네임")
    private String password;

    @Size(max = 20, message = "하이픈 없이 '-' 올바른 전화번호를 입력해주세요.")
    @NotEmpty(message = "전화번호는 필수값 입니다.")
    @Schema(description = "하이픈 없이 '-' 올바른 전화번호를 입력해주세요.", defaultValue = "01012345678")
    private String phoneNum;

    @Size(max = 100, message = "이메일을 100글자 이내로 입력해주세요.")
    @NotEmpty(message = "이메일은 필수값 입니다.")
    @Email(message = "올바른 형식의 이메일을 입력해주세요.")
    @Schema(description = "올바른 형식의 이메일을 입력해주세요.", defaultValue = "minji1@minji.com")
    private String email;

    @Schema(description = "성별", defaultValue = "성별 정보는 필수가 아닙니다.", allowableValues = {"M", "F"})
    private String gender;

    public void setPassword(String password) {
        this.password = password;
    }

    public Member toEntity() {
        Gender gender = this.gender == null ? null : this.gender.equals("M") ? Gender.M : this.gender.equals("F") ? Gender.F : null;

        return Member.builder()
                .name(name)
                .nickname(nickname)
                .password(password)
                .phoneNum(phoneNum)
                .email(email)
                .gender(gender)
                .build();
    }

}
