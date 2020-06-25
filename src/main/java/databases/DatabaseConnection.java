package databases;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class DatabaseConnection {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private DatabaseConnection() {
        createConnection();
    }

    private void createConnection() {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DATABASE_NAME = "foods";
        final String TABLE_NAME = "favorite_foods";
        final String DATABASE_URL = "jdbc:mysql://localhost/" + DATABASE_NAME;
        final String USERNAME = "erikrios";
        final String PASSWORD = "";

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
