package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

	/*
	 * Basic
	 */

//	Set<User> findByName(String name);
	Set<User> findUserByNameIs(String name);
	Set<User> findUserByName(String name);
	Set<User> findUserByNameEquals(String name);

	User findByEmail(String email);
	User getByEmail(String email);
	User readByEmail(String email);
	User queryByEmail(String email);
	User searchByEmail(String email);
	User streamByEmail(String email);
	User findUserByEmail(String email);
	User findSomethingByEmail(String email);

	List<User> findByEmailAndName(String email, String name);
	List<User> findByEmailOrName(String email, String name);

	List<User> findByCreatedAtAfter(LocalDateTime yesterday);
	List<User> findByIdAfter(Long id);

	List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);
	List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);
	List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
	List<User> findByIdBetween(Long id1, Long id2);
	List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

//	List<User> findByAddressListIsNotEmpty();

	List<User> findByNameIn(List<String> names);

	List<User> findByNameStartingWith(String name);
	List<User> findByNameEndingWith(String name);
	List<User> findByNameContains(String name);
	List<User> findByNameLike(String name); // 코드 가독성이 떨어지므로 위 세가지가 가독성이 더 좋음.

	// jpa가 코드 가독성을 많이 신경 씀.

	/*
	 * Paging, Sorting
	 */

	List<User> findTop1ByName(String name);
	List<User> findFirst2ByName(String name);
	List<User> findTop2ByName(String name);

	List<User> findLast1ByName(String name);
	List<User> findTop1ByNameOrderByIdDesc(String name); // 위 Last1은 안먹히므로 이 쿼리가 의도대로 작동함.
	List<User> findTopByNameOrderByIdDesc(String name);

	List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

	List<User> findFirstByName(String name, Sort sort);

	Page<User> findByName(String name, Pageable pageable);

	@Query(value = "select * from user limit1;", nativeQuery = true)
	Map<String, Object> findRawRecord();

}
