package com.example.harudam.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.harudam.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
