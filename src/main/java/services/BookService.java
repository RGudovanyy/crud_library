package services;

import model.Book;

import java.util.List;

/**
 *
 */
public interface BookService {

    void addNewBook(Book book);

    void deleteBook (Book book);

    List<Book> getBookFromDB(String name);

}
