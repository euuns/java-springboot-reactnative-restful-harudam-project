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

	public void save(Long userId, int age) {
		redisTemplate.opsForValue().set("user:age:" + userId, age, Duration.ofDays(365));
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

