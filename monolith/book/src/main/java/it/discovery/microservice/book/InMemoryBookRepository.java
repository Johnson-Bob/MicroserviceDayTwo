package it.discovery.microservice.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookRepository implements BookRepository {
	private final Map<Integer, Book> books;

	private int counter;

	public InMemoryBookRepository() {
		books = new HashMap<>();
		Book book = new Book();
		book.setId(++counter);
		book.setName("Thinking in Java");
		book.setPages(1150);
		book.setYear(2006);
		books.put(book.getId(), book);
	}

	@Override
	public List<Book> getBooks() {
		return new ArrayList<>(books.values());
	}
	
	@Override
	public Book findBookById(int id) {
		return books.get(id);
	}

	@Override
	public void saveBook(Book book) {
		if(book.getId() == 0) {
			book.setId(++counter);
			books.put(book.getId(), book);			
		} else {
			books.put(book.getId(), book);
		}	
	}

	@Override
	public void deleteBook(int id) {
		books.remove(id);
	}

}
