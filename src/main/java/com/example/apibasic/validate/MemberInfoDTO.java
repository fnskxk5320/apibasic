package com.example.apibasic.validate;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class MemberInfoDTO {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank @Size(min =2, max=5)
    private String username;
    @JsonFormat(pattern = "yyMMdd")
    @Past // 과거 날짜인지 검사
    private LocalDate birthOfDate;

    @Valid // 컴포지션 객체 입력 값 검증
    private Address address; // 주소 정보 (도로명 + 우편번호)
    @Valid
    private Card card; //결제 카드 정보 (카드번호 + 카드만료기간)
}
