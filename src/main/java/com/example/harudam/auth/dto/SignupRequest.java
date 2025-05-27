package com.example.harudam.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest {
	@Email(message = "이메일 양식에 맞지 않습니다.")
	@NotBlank(message = "이메일 입력은 필수 입니다.")
	private String email;

	@NotBlank(message = "비밀번호 입력은 필수 입니다.")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&]).{8,}$"
		, message = "대소문자, 숫자, 특수문자(!,@,#,$,%,^,&)를 포함한 8자리 이상으로 입력해주세요.")
	private String password;

	@NotBlank(message = "이름 입력은 필수 입니다.")
	private String name;

	@NotNull(message = "나이 입력은 필수 입니다.")
	private int age;

	private int height;
}