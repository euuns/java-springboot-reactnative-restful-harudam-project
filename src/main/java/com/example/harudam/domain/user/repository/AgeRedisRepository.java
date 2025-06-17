package com.example.harudam.domain.user.repository;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AgeRedisRepository {

	private final RedisTemplate<String, Integer> redisTemplate;

	public AgeRedisRepository(RedisTemplate<String, Integer> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	// 6개월 기준으로 갱신
	public void save(Long userId, int age) {
		redisTemplate.opsForValue().set("user:age:" + userId, age, Duration.ofDays(180));
	}

	public Integer find(Long userId) {
		return redisTemplate.opsForValue().get("user:age:" + userId);
	}

	public void delete(Long userId) {
		redisTemplate.delete("user:age:" + userId);
	}

	public boolean exists(Long userId) {
		return redisTemplate.hasKey("user:age:" + userId);
	}
}

