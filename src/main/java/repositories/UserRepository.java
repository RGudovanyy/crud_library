package repositories;

import model.User;

/**
 *
 */
public interface UserRepository {

    void addUser(User user);
    User getUser(String name);
    void changeUser(String name);

}
