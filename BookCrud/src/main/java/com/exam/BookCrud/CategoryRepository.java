package com.exam.BookCrud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query(value = "SELECT * FROM categories INNER JOIN books_categories ON categories.id = books_categories.CategoryID WHERE books_categories.BookID = :bookid", nativeQuery = true)
	List<Category> getCatOfBook(@Param("bookid") long bookid);
	
	@Query(value = "SELECT * FROM categories WHERE title LIKE %:query%", nativeQuery = true)
	List<Category> getCategorySearch(@Param("query") String query);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO books_categories(BookID, CategoryID) VALUES(:bookid,:categoryid)", nativeQuery = true)
	void saveBookCat(@Param("bookid") long bookid, @Param("categoryid") long categoryid);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM books_categories WHERE BookID = :bookid", nativeQuery = true)
	void delBookCat(@Param("bookid") long bookid);

	@Query(value = "INSERT INTO categories(title, description) OUTPUT Inserted.ID VALUES(:title,:desc)", nativeQuery = true)
	long saveCategory(@Param("title") String title, @Param("desc") String desc);

	@Modifying
	@Transactional
	@Query(value = "UPDATE categories SET title = :title, description = :desc WHERE id = :catid", nativeQuery = true)
	void updCategory(@Param("catid") long catid, @Param("title") String title, @Param("desc") String desc);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM categories WHERE id = :catid", nativeQuery = true)
	void delCategory(@Param("catid") long catid);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM books_categories WHERE BookID = :bookid AND CategoryID = :catid", nativeQuery = true)
	void remCategory(@Param("bookid") long bookid, @Param("catid") long catid);
}
