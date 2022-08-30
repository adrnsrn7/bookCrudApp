package com.exam.BookCrud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookCrudApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository catRepo;

	public static void main(String[] args) {
		SpringApplication.run(BookCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Category> categories = catRepo.findAll();
		
		categories.forEach(System.out :: println);
	}

}
