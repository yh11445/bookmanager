package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class EntityManagerTest {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private UserRepository userRepository;

	@Test
	void entityManagertest() {
		System.out.println(entityManager.createQuery("select u from User u").getResultList());
	}

	@Test
	void cacheFindTest() {
		System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
		System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
		System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
		System.out.println(userRepository.findById(2L));
		System.out.println(userRepository.findById(2L));
		System.out.println(userRepository.findById(2L));

		userRepository.deleteById(1L); // delete 는 실행 안됨 >> 테스트 메소드이므로 롤백 됨

	}

	@Test
	void cacheFindTest2() {
//		User user = userRepository.findById(1L).get();
		User user = userRepository.findByEmail("martin@fastcampus.com");
		user.setName("marrrrrrrtin");
		userRepository.save(user);

		user.setEmail("marrrrrrtin@fastcampus.com");
		userRepository.save(user);

		System.out.println(">>> 1 : " + userRepository.findById(1L).get());
		userRepository.flush();
		System.out.println(">>> 2 : " + userRepository.findById(1L).get());
	}
}
