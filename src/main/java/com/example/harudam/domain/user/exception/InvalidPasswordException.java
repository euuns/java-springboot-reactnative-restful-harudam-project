package com.example.harudam.domain.user.exception;

import com.example.harudam.domain.user.enums.UserErrorCode;

public class InvalidPasswordException extends UserException {
	public InvalidPasswordException() {
		super(UserErrorCode.INVALID_PASSWORD);
	}
}