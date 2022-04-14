package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UserTest {
	@Test
	void test() {
		User user = new User();
		user.setEmail("martin@fastcampus.com");
		user.setName("martin");
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());

//		User user1 = new User(null, "martin", "martin@fastcampus.com", LocalDateTime.now(), LocalDateTime.now());
		User user2 = new User("martin", "martin@fastcampus.com");

		User user3 = User.builder()
				.name("martin")
				.email("martin@fastcampus.com")
				.build();

		System.out.println(">>> " + user.toString());
	}
}
