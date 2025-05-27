package com.example.harudam.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserWriter userWriter;
	private final UserFinder userFinder;
	private final UserChecker userChecker;

	public Void signup(String email, String password, String name, int age, int height) {

		if (userChecker.existsByEmail(email)) {
			throw new EmailAlreadyExistsException();
		}

		String encode = bCryptPasswordEncoder.encode(password);
		User user = User.of(email, encode, name, age, height);
		User saveUser = userWriter.saveUser(user);

		return null;
	}

	public Void login(String email, String password) {
		User findUser = userFinder.findByEmail(email);

		if (!bCryptPasswordEncoder.matches(password, findUser.getPassword())) {
			throw new InvalidEmailPasswordException();
		}

		// TODO: jwt 응답
	}
}
