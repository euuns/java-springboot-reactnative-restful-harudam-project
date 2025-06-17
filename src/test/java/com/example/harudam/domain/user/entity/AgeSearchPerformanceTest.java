package com.example.harudam.domain.user.entity;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;

@ExtendWith(MockitoExtension.class)
class AgeSearchPerformanceTest {

	private final LocalDate birthDate = LocalDate.of(1990, 6, 17);
	private User mockUser;
	private RedisTemplate<String, Integer> redisTemplate;

	@BeforeEach
	void setup() {
		mockUser = new User("홍길동", birthDate);

		// Redis 초기 저장
		if (redisTemplate != null) {
			int age = Period.between(birthDate, LocalDate.now()).getYears();
			redisTemplate.opsForValue().set("user:age:" + mockUser.getId(), age);
		}
	}

	@Test
	void 만_나이_계산_성능_비교_테스트() {
		// 5만번 조회 속도 확인
		int iteration = 50000;

		// 1. Period 계산(getAge)
		long PeriodStart = System.nanoTime();
		for (int i = 0; i < iteration; i++) {
			int age = mockUser.getPeriodBirthBetweenNow();
		}
		long PeriodEnd = System.nanoTime();

		// 2. Entity 필드에 age 저장 방식
		mockUser.updateAge();
		long updateStart = System.nanoTime();
		for (int i = 0; i < iteration; i++) {
			int age = mockUser.getAge();
		}
		long updateEnd = System.nanoTime();

		// 3. DTO에서 계산
		long dtoStart = System.nanoTime();
		for (int i = 0; i < iteration; i++) {
			UserDto dto = new UserDto(mockUser);
		}
		long dtoEnd = System.nanoTime();

		// 4. Redis에서 조회
		long redisStart = System.nanoTime();
		for (int i = 0; i < iteration; i++) {
			if (redisTemplate != null) {
				Integer age = redisTemplate.opsForValue().get("user:age:" + mockUser.getId());
			}
		}
		long redisEnd = System.nanoTime();

		System.out.println("1. Period 계산		:	" + (PeriodEnd - PeriodStart) / 1_000_000 + " ms");
		System.out.println("2. 필드 저장			:	" + (updateEnd - updateStart) / 1_000_000 + " ms");
		System.out.println("3. DTO 계산			:	" + (dtoEnd - dtoStart) / 1_000_000 + " ms");
		System.out.println("4. Redis 조회		:	" + (redisEnd - redisStart) / 1_000_000 + " ms");

		assertThat(true).isTrue();
	}

	// Mock User Entity
	static class User {
		private static long ID_SEQ = 1;
		private final long id;
		private LocalDate birthDate;
		private int age;

		public User(String name, LocalDate birthDate) {
			this.id = ID_SEQ++;
			this.birthDate = birthDate;
		}

		public long getId() {
			return id;
		}

		public int getPeriodBirthBetweenNow() {
			return Period.between(birthDate, LocalDate.now()).getYears();
		}

		// @PreUpdate 사용 DB 값 변경
		public void updateAge() {
			this.age = Period.between(this.birthDate, LocalDate.now()).getYears();
		}

		public LocalDate getBirthDate() {
			return birthDate;
		}

		public int getAge(){
			return age;
		}
	}

	// DTO 내부에서 계산하는 방식
	static class UserDto {
		private int age;

		public UserDto(User user) {
			this.age = Period.between(user.getBirthDate(), LocalDate.now()).getYears();
		}

		public int getAge() {
			return age;
		}
	}
}