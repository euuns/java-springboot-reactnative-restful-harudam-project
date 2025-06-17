package com.example.harudam.global.schedule;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.harudam.domain.user.dto.projection.UserBirthProjection;
import com.example.harudam.domain.user.entity.User;
import com.example.harudam.domain.user.repository.AgeRedisRepository;
import com.example.harudam.domain.user.repository.UserRepository;
import com.example.harudam.domain.user.service.UserFindService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgeScheduler {

	private final UserRepository userRepository;
	private final AgeRedisRepository ageRedisRepository;

	@Scheduled(cron = "0 0 1 * * *")
	public void refreshAgeCache() {

		int cnt = 0;
		List<UserBirthProjection> users = userRepository.findBirthInfoByMonthAndDay(LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());

		for (UserBirthProjection user : users) {
			int age = Period.between(user.getBirthDate(), LocalDate.now()).getYears();
			ageRedisRepository.save(user.getId(), age);
			cnt++;
		}

		log.info("사용자 나이 Redis 캐시 갱신: 총 {}명", cnt);
	}
}
