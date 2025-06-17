package com.example.harudam.auth.service;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.harudam.domain.user.entity.User;
import com.example.harudam.domain.user.exception.EmailAlreadyExistsException;
import com.example.harudam.domain.user.exception.InvalidPasswordException;
import com.example.harudam.domain.user.service.UserFindService;
import com.example.harudam.domain.user.service.UserWriteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserWriteService userWriter;
	private final UserFindService userFinder;

	public Void signup(String email, String password, String name, LocalDate birthDate, int height) {

		if (userFinder.existsByEmail(email)) {
			throw new EmailAlreadyExistsException();
		}

		String encode = bCryptPasswordEncoder.encode(password);
		User user = User.of(email, encode, name, birthDate, height);

		User saveUser = userWriter.save(user);
		userWriter.ageSave(saveUser.getId(), saveUser.getBirthDate());

		return null;
	}

	public Void login(String email, String password) {
		User findUser = userFinder.findByEmail(email);

		if (!bCryptPasswordEncoder.matches(password, findUser.getPassword())) {
			throw new InvalidPasswordException();
		}

		// TODO: jwt 응답

		return null;
	}
}
