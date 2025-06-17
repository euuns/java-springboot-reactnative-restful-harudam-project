package com.example.harudam.domain.user.exception;

import com.example.harudam.domain.user.enums.UserErrorCode;

public class InvalidUserRoleException extends UserException {
	public InvalidUserRoleException() {
		super(UserErrorCode.INVALID_ROLE);
	}
}
