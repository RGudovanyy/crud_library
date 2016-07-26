package repositories;

import model.Book;

import java.util.List;

/**
 *
 */
public interface BookRepository {

    void addBook(Book book);
    List<Book> getBook(String name);
    void deleteBook(String name);
    List<Book> getAllBooks(String userName);

}
