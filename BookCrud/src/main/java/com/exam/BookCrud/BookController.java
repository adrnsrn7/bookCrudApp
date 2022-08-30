package com.exam.BookCrud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private CategoryRepository catRepo;
	
	@GetMapping("/search")
	public String search(Model model) {
		BookList bl = new BookList();
		model.addAttribute("bl", bl);
		Query query = new Query();
		model.addAttribute("query", query);
		return "search";
	}
	
	@PostMapping("/search")
	public String result(@ModelAttribute Query query, Model model) {
		List<Book> books = bookRepo.getBookSearch(query.getValue());
		List<Category> categories = catRepo.getCategorySearch(query.getValue());
		for(Category category : categories) {
			List<Book> catbooks = bookRepo.getBookListByCatID(category.getId());
			for(Book book : catbooks) {
				if(!books.contains(book)) {
					books.add(book);
				}
			}
		}
		List<BookProfile> bookl = new ArrayList<BookProfile>();
		for(Book book : books) {
			BookProfile bp = new BookProfile();
			bp.setId(book.getId());
			bp.setTitle(book.getTitle());
			bp.setDescription(book.getDescription());
			bp.setCategories(catRepo.getCatOfBook(book.getId()));
			bookl.add(bp);
		}
		BookList bl = new BookList();
		model.addAttribute("bl", bl);
		model.addAttribute("query", query);
		model.addAttribute("bookl", bookl);
		return "search";
	}
	
	@RequestMapping("/createbook")
	public String create(Model model) {
		BookProfile bp = new BookProfile();
		model.addAttribute("bp", bp);
		model.addAttribute("allcat", catRepo.findAll());
		return "createbook";
	}
	
	@PostMapping("/savebook")
	public ModelAndView createBook(@ModelAttribute BookProfile bp) {
		long id = bookRepo.saveBook(bp.getTitle(), bp.getDescription());
		for(Category cat : bp.getCategories()) {
			catRepo.saveBookCat(id, cat.getId());
		}
	    return new ModelAndView("redirect:" + "/books/" + id);
	}
	
	@RequestMapping("/books")
	public String getBooks(Model model) {
		BookList bl = new BookList();
		model.addAttribute("bl", bl);
		model.addAttribute("books", bookRepo.findAll());
		
		return "books";
	}
	
	@RequestMapping("/books/{id}")
	public String getBook(@PathVariable long id, Model model) {
		Optional<Book> booksret = bookRepo.findById(id);
		model.addAttribute("book", booksret);
		
		List<Category> catret = catRepo.getCatOfBook(id);
		model.addAttribute("categories", catret);
		
		return "book";
	}
	
	@RequestMapping("/books/{id}/edit")
	public String editBook(@PathVariable long id, Model model) {
		Optional<Book> booksret = bookRepo.findById(id);
		model.addAttribute("book", booksret);
		
		List<Category> catret = catRepo.getCatOfBook(id);
		model.addAttribute("categories", catret);
		
		BookProfile bp = new BookProfile();
		model.addAttribute("bp", bp);
		model.addAttribute("allcat", catRepo.findAll());

        List<Long> checked = new ArrayList<Long>();
        for(Category cat : catret) {
        	checked.add(cat.getId());
        }
		model.addAttribute("checked", checked);
		
		return "updatebook";
	}
	
	@PostMapping("/updatebook")
	public ModelAndView updateBook(@ModelAttribute BookProfile bp) {
		bookRepo.updBook(bp.getId(), bp.getTitle(), bp.getDescription());
		catRepo.delBookCat(bp.getId());
		for(Category cat : bp.getCategories()) {
			catRepo.saveBookCat(bp.getId(), cat.getId());
		}
	    return new ModelAndView("redirect:" + "/books/" + bp.getId());
	}
	
	@PostMapping("/delbook")
	public ModelAndView deleteBook(@ModelAttribute BookList bl) {
		for(Book book : bl.getBooks()) {
			bookRepo.delBook(book.getId());
		}
	    return new ModelAndView("redirect:" + "/books");
	}
}
