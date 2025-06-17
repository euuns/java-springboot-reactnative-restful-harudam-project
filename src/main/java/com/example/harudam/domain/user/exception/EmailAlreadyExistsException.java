package com.example.harudam.domain.user.exception;

import com.example.harudam.domain.user.enums.UserErrorCode;

public class EmailAlreadyExistsException extends UserException {
	public EmailAlreadyExistsException() {
		super(UserErrorCode.ALREADY_EMAIL);
	}
}