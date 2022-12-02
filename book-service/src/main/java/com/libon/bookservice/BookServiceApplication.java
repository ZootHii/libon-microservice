package com.libon.bookservice;

import com.libon.bookservice.model.Book;
import com.libon.bookservice.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class BookServiceApplication implements CommandLineRunner {

	private final BookRepository bookRepository;

	public BookServiceApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book1 = new Book("Sineklerin Tanrısı", 1954, "William Golding", "Kültür Yayınları", "111");
		Book book2 = new Book("Hamlet", 1602, "William Shakespeare", "Ren Yayınları", "222");
		Book book3 = new Book("Cesur Yeni Dünya", 1932, "Aldous Huxley", "İthaki Yayınları", "333");

		List<Book> books = bookRepository.saveAll(List.of(book1, book2, book3));
		System.out.println(books);
	}
}
