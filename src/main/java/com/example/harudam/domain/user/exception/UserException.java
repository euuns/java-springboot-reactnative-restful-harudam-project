package com.example.harudam.domain.user.exception;

import com.example.harudam.domain.user.enums.UserErrorCode;
import com.example.harudam.global.exception.BaseException;

public class UserException extends BaseException {
	public UserException(UserErrorCode errorCode) {
		super(errorCode);
	}
}
