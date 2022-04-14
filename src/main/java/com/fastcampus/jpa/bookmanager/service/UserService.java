package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService {
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void put() {
		User user = new User();
		user.setName("newName");
		user.setEmail("newEmail@fastcampus.com");

		entityManager.persist(user); // 영속화
		entityManager.detach(user);

		user.setName("newNameAfterPersist");

		entityManager.merge(user);
		entityManager.flush();
		entityManager.clear();

		entityManager.remove(user);
	}
}
