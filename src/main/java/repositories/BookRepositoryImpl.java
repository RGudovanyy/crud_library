package repositories;

import model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DBService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BookRepositoryImpl implements BookRepository {
    Logger log = LoggerFactory.getLogger(BookRepositoryImpl.class);
    Connection connection;

    public BookRepositoryImpl(){
        this.connection =  new DBService().getH2Connection();
    }

    @Override
    public void addBook(Book book) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("insert into book(name, owner, path) values " +
                    "('"+book.getName()+"','"+book.getOwner()+"','"+book.getPathToFile()+"')");
            log.info("успешно добавлена книга");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                }
        }
    }

    @Override
    public List<Book> getBook(String name) {
        List<Book> result = new ArrayList<>();
        try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            statement.executeQuery("select * from book where name like '"+name+"'");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                String bookName = resultSet.getString("name");
                String bookOwner = resultSet.getString("owner");
                String bookPath = resultSet.getString("path");
                result.add(new Book(bookName,bookOwner,bookPath));
            }
            if (!result.isEmpty())
                log.info("получена книга из БД");
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                }
        }

        return result;
    }

    @Override
    public void deleteBook(String name) {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("delete from book where name='"+name+"'");
            log.info("книга удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection !=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                }
        }
    }

    @Override
    public List<Book> getAllBooks(String userName) {
        log.info("BookRepository отвечает");
        List<Book> listOfBooks = new ArrayList<>();
        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            log.info("ищем книги для пользователя "+userName);
            statement.executeQuery("select * from book where owner='"+userName+"'");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                String bookName = resultSet.getString("name");
                String bookOwner = resultSet.getString("owner");
                String bookPath = resultSet.getString("path");
                listOfBooks.add(new Book(bookName,bookOwner,bookPath));
            }
            if (!listOfBooks.isEmpty())
                log.info("получен список книг из БД");
            else
                log.info("книги из БД не получены");
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                }
        }

        return listOfBooks;
    }
}
