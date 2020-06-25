package databases;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class DatabaseConnection {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_NAME = "foods";
    private final String TABLE_NAME = "favorite_foods";
    private final String DATABASE_URL = "jdbc:mysql://localhost/" + DATABASE_NAME;
    private final String MYSQL_USERNAME = "erikrios";
    private final String MYSQL_PASSWORD = "";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private DatabaseConnection() {

    }
}
