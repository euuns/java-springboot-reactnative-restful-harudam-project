package com.example.harudam.domain.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.SQLDelete;

import com.example.harudam.domain.common.entity.TimeStamp;
import com.example.harudam.domain.user.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?")
@FilterDef(name = "deletedFilter")
@Filter(name = "deletedFilter", condition = "deleted_at IS NULL")
public class User extends TimeStamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	private String nickname;
	@Column(nullable = false)
	private LocalDate birthDate;
	private int height;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	private LocalDateTime deletedAt;

	@Builder
	private User(String email, String password, String nickname, LocalDate birthDate, int height) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.height = height;
		this.role = UserRole.ROLE_USER;
	}
	public static User of(String email, String password, String nickname, LocalDate birthDate, int height) {
		return User.builder().email(email).password(password).nickname(nickname).birthDate(birthDate).height(height).build();
	}

	public void updatePassword(String password) {
		this.password = password;
	}

	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}

	public void updateHeight(int height) {
		this.height = height;
	}
}
