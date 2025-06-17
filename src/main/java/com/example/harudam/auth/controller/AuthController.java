package com.example.harudam.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.harudam.auth.dto.LoginRequest;
import com.example.harudam.auth.dto.SignupRequest;
import com.example.harudam.auth.service.AuthService;
import com.example.harudam.domain.common.dto.ResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseDto<Void> signup(@Valid @RequestBody SignupRequest request) {
		return ResponseDto.success(
			authService.signup(request.getEmail(), request.getPassword(), request.getName(), request.getBirthDate(),
				request.getHeight()));
	}

	@PostMapping("/login")
	public ResponseDto<Void> login(@Valid @RequestBody LoginRequest request) {
		return ResponseDto.success(authService.login(request.getEmail(), request.getPassword()));
	}
}
