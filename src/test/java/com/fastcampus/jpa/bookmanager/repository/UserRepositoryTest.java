package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserHistoryRepository userHistoryRepository;

	@Test
	void paging_테스트() {
		Page<User> users = userRepository.findAll(PageRequest.of(1, 3));

		System.out.println("page : " + users);
		System.out.println("totalElements : " + users.getTotalElements());
		System.out.println("totalPages : " + users.getTotalPages());
		System.out.println("numberOfElements : " + users.getNumberOfElements());
		System.out.println("sort : " + users.getSort());
		System.out.println("size : " + users.getSize());

		users.getContent().forEach(System.out::println);
	}

	@Test
	void Example_테스트() {
		User user = new User();
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
		Example<User> example = Example.of(user, matcher);

		userRepository.findAll(example).forEach(System.out::println);
	}

	@Test
	void select() {
//		System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com", "martin"));
//		System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@fastcampus.com", "martin"));

//		System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
//		System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
//		System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

//		System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
//		System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));

//		System.out.println("findByAddressListIsNotEmpty : " + userRepository.findByAddressListIsNotEmpty());

//		System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin", "dennis")));

//		System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
//		System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
//		System.out.println("findByNameContains : " + userRepository.findByNameContains("art"));

	}

	@Test
	void pagingAndSortingTest() {
//		System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
//		System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));

//		System.out.println("findByFirstByNameWithSortParam : " + userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));


		System.out.println("findByNameWithPaging : " + userRepository.findByName("martin", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
	}

	// Sort 여러 개인 경우
	private Sort getCustomSort() {
		return Sort.by(
				Sort.Order.desc("id"),
				Sort.Order.asc("email"),
				Sort.Order.asc("name")
		);
	}

	@Test
	void insertAndUpdateTest() {
		User user = new User();
		user.setName("martin");
		user.setEmail("martin2@fastcampus.com");

		userRepository.save(user);

		User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
		user2.setName("marrrrrrrrtin");

		userRepository.save(user2);
	}

	@Test
	void enumTest() {
		User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
		user.setGender(Gender.MALE);

		userRepository.save(user);

		userRepository.findAll().forEach(System.out::println);

		System.out.println();
	}

	@Test
	void listenerTest() {
		User user = new User();
		user.setEmail("martin2@fastcampus.com");
		user.setName("martin");

		userRepository.save(user);

	}

	@Test
	void userRelationTest(){
		User user = new User();
		user.setName("david");
		user.setEmail("david@fastcampus.com");
		user.setGender(Gender.MALE);

		userRepository.save(user);

		user.setName("daniel");
		userRepository.save(user);

		user.setEmail("daniel@fastcampus.com");
		userRepository.save(user);

//		userHistoryRepository.findAll().forEach(System.out::println);

//		List<UserHistory> result = userHistoryRepository.findByUserId(
//				userRepository.findByEmail("daniel@fastcampus.com").getId());

		List<UserHistory> result = userRepository.findByEmail("daniel@fastcampus.com").getUserHistories();

		result.forEach(System.out::println);

		System.out.println("UserHistory.getUser() : " + userHistoryRepository.findAll().get(0).getUser());
	}
}