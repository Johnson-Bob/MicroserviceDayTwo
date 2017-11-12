package it.discovery.microservice.book;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
	
	private String libraryName = "IT-Discovery library";
	
	private final BookRepository bookRepository;
	
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping("/library")
	public String getLibraryName() {
		return libraryName;
	}

	@GetMapping
	public List<Book> getBooks() {
		return bookRepository.getBooks();				
	}

	@GetMapping("/{id}")
	public Book getBook(@RequestParam int id) {
		return bookRepository.findBookById(id);				
	}

	public void saveBook(Book book) {
		bookRepository.saveBook(book);				
	}

	public void updateBook(Book book) {
		bookRepository.saveBook(book);				
	}

}
