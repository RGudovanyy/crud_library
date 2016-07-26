package services;

import model.Book;
import model.User;
import repositories.BookRepository;
import repositories.BookRepositoryImpl;
import repositories.UserRepository;
import repositories.UserRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class UserServiceImpl implements UserService{

    UserRepository userRepository = new UserRepositoryImpl();
    BookRepository bookRepository = new BookRepositoryImpl();

    public void addNewUser(User user) {
        userRepository.addUser(user);
    }

    public User getUserByLogin(String name){
        return userRepository.getUser(name);
    }

    @Override
    public List<Book> getBooksForUser(String name) {

        System.out.println("UserService вызывает BookRepository");
        return bookRepository.getAllBooks(name);
    }

    @Override
    public String changePassword(String name) {
        return null;
    }
}
