package com.example.harudam.domain.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.harudam.domain.user.dto.projection.UserBirthProjection;
import com.example.harudam.domain.user.entity.User;

import io.lettuce.core.dynamic.annotation.Param;

public interface UserRepository extends JpaRepository<User,Long> {

	@Query(value = "SELECT id, birth_date FROM user WHERE MONTH(birth_date) = :month AND DAY(birth_date) = :day", nativeQuery = true)
	List<UserBirthProjection> findBirthInfoByMonthAndDay(@Param("month") int month, @Param("day") int day);

	User findByEmail(String email);

	boolean existsByEmail(String email);
}
