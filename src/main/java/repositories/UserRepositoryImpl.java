package repositories;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DBService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by anvi on 7/9/16.
 */
public class UserRepositoryImpl implements UserRepository {

    Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    Connection connection;

    public UserRepositoryImpl(){
        this.connection =  new DBService().getH2Connection();
    }


    @Override
    public void addUser(User user) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("insert into user(name, password) values" +
                    " ('" + user.getName() + "','" + user.getPassword() + "');");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUser(String name) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            statement.execute("select * from user where name='" + name + "'");
            ResultSet resultSet = statement.getResultSet();
            resultSet.last();
            logger.info(String.valueOf(resultSet));
            logger.info("получаем имя");
            String n = resultSet.getString("NAME");
            logger.info("имя - " + n);
            logger.info("получаем пароль");
            String p = resultSet.getString("PASSWORD");
            logger.info("пароль - "+p);
            User existedUser = new User(n, p);
            resultSet.close();
            statement.close();
            connection.close();
            return existedUser;
        } catch (SQLException e) {
            logger.info("пользователь не найден");
        }
        return new User("not","found");
    }

    @Override
    public void changeUser(String name) {

    }
}
