package com.example.harudam.domain.user.dto.projection;

import java.time.LocalDate;

public interface UserBirthProjection {
	Long getId();
	LocalDate getBirthDate();
}
