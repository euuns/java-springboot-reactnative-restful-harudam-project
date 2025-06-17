package com.example.harudam.domain.user.enums;

import org.springframework.http.HttpStatus;

import com.example.harudam.global.exception.ErrorCode;

public enum UserErrorCode implements ErrorCode {
	ALREADY_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 입니다."),

	INVALID_ROLE(HttpStatus.BAD_REQUEST, "유효하지 않은 역할 입니다."),
	INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "유효하지 않은 비밀번호 입니다.");

	private HttpStatus httpStatus;
	private String message;

	private UserErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	@Override
	public String getDefaultMessage() {
		return this.message;
	}
}
