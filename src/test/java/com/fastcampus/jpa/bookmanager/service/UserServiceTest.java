package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Test
	void test() {
		userService.put();

		System.out.println(userRepository.findByEmail("newEmail@fastcampus.com"));
	}
}