package com.exam.BookCrud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private CategoryRepository catRepo;
	
	@RequestMapping("/createcategory")
	public String create(Model model) {
		return "createcategory";
	}
	
	@PostMapping("/savecategory")
	public ModelAndView createCategory(@ModelAttribute Category cp) {
		long id = catRepo.saveCategory(cp.getTitle(), cp.getDescription());
	    return new ModelAndView("redirect:" + "/categories/" + id);
	}
	
	@RequestMapping("/categories")
	public String getCategories(Model model) {
		CategoryList cl = new CategoryList();
		model.addAttribute("cl", cl);
		model.addAttribute("categories", catRepo.findAll());
		
		return "categories";
	}
	
	@RequestMapping("/categories/{id}")
	public String getCategory(@PathVariable long id, Model model) {
		Optional<Category> catret = catRepo.findById(id);
		model.addAttribute("category", catret);
		BookList bl = new BookList();
		model.addAttribute("bl", bl);
		
		List<Book> books = bookRepo.getBookListByCatID(id);
		model.addAttribute("books", books);
		return "category";
	}
	
	@RequestMapping("/categories/{id}/edit")
	public String editCategory(@PathVariable long id, Model model) {
		Optional<Category> catret = catRepo.findById(id);
		model.addAttribute("category", catret);
		
		return "updatecategory";
	}
	
	@PostMapping("/updatecategory")
	public ModelAndView updateBook(@ModelAttribute Category cp) {
		catRepo.updCategory(cp.getId(), cp.getTitle(), cp.getDescription());
	    return new ModelAndView("redirect:" + "/categories/" + cp.getId());
	}
	
	@PostMapping("/delcategory")
	public ModelAndView deleteCategory(@ModelAttribute CategoryList cl) {
		for(Category cat : cl.getCategories()) {
			catRepo.delCategory(cat.getId());
		}
	    return new ModelAndView("redirect:" + "/categories");
	}
	
	@PostMapping("/remcategory/{id}")
	public ModelAndView removeFromCategory(@PathVariable long id, @ModelAttribute BookList bl) {
		for(Book book : bl.getBooks()) {
			catRepo.remCategory(book.getId(), id);
		}
	    return new ModelAndView("redirect:" + "/categories/" + id);
	}
}
