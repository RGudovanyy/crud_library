package services;

import model.Book;
import model.User;

import java.util.List;

/**
 *
 */
public interface UserService {

    void addNewUser(User user);

    User getUserByLogin(String name);

    List<Book> getBooksForUser(String name);

    String changePassword(String name);
}
