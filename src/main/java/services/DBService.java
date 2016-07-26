package services;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class DBService {
    private final Connection connection;

    public DBService() {


        this.connection = getH2Connection();
    }

    public Connection getH2Connection() {
        try{
            String url = "jdbc:h2:./h2db";
            String name = "sa";
            String pass = "";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setUrl(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url,name,pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
