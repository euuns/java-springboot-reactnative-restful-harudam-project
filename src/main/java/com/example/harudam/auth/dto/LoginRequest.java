package com.example.harudam.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
	@NotBlank(message = "이메일을 입력 해주세요.")
	private String email;
	@NotBlank(message = "비밀번호를 입력 해주세요.")
	private String password;
}
