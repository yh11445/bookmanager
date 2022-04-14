package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final AuthorService authorService;

	public void put() {
		this.putBookAndAuthor();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	void putBookAndAuthor() {
		Book book = new Book();
		book.setName("JPA 시작하기");
		bookRepository.save(book);

		authorService.putAuthor();

//		Author author = new Author();
//		author.setName("martin");
//		authorRepository.save(author);

//		throw new RuntimeException("오류가 나서 DB commit이 발생하지 않습니다");
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void get(Long id) {
		System.out.println(">>> " + bookRepository.findById(id));
		System.out.println(">>> " + bookRepository.findAll());

		System.out.println(">>> " + bookRepository.findById(id));
		System.out.println(">>> " + bookRepository.findById(id));
	}
}
