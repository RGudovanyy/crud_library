package services;

import model.Book;
import repositories.BookRepository;
import repositories.BookRepositoryImpl;

import java.util.List;

/**
 *
 */
public class BookServiceImpl implements BookService {

    BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public void addNewBook(Book book) {
        bookRepository.addBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.deleteBook(book.getName());
    }

    @Override
    public List<Book> getBookFromDB(String name) {
        return bookRepository.getBook(name);
    }
}
