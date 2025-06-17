package com.example.harudam.domain.user.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.example.harudam.domain.user.entity.User;
import com.example.harudam.domain.user.repository.AgeRedisRepository;
import com.example.harudam.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserWriteService {

	private final UserRepository userRepository;
	private final AgeRedisRepository ageRedisRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public void ageSave(Long userId, LocalDate birthday) {
		int age = Period.between(birthday, LocalDate.now()).getYears();
		ageRedisRepository.save(userId, age);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}
}
