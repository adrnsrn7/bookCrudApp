package com.exam.BookCrud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {
	@Query(value = "SELECT * FROM books INNER JOIN books_categories ON books.id = books_categories.BookID WHERE books_categories.CategoryID = :catid", nativeQuery = true)
	List<Book> getBookListByCatID(@Param("catid") long catid);
	
	@Query(value = "SELECT * FROM books WHERE title LIKE %:query%", nativeQuery = true)
	List<Book> getBookSearch(@Param("query") String query);
	
	@Query(value = "INSERT INTO books(title, description) OUTPUT Inserted.ID VALUES(:title,:desc)", nativeQuery = true)
	long saveBook(@Param("title") String title, @Param("desc") String desc);

	@Modifying
	@Transactional
	@Query(value = "UPDATE books SET title = :title, description = :desc WHERE id = :bookid", nativeQuery = true)
	void updBook(@Param("bookid") long bookid, @Param("title") String title, @Param("desc") String desc);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM books WHERE id = :bookid", nativeQuery = true)
	void delBook(@Param("bookid") long bookid);
}
