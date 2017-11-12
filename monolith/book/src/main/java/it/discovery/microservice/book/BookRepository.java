package it.discovery.microservice.book;

import java.util.List;

public interface BookRepository {
	
	List<Book> getBooks();
	
	Book findBookById(int id);
	
	void saveBook(Book book);
	
	void deleteBook(int id);

}
