package databases;

import models.FavoriteFood;

import java.sql.*;

public class DatabaseConnection {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    final String DATABASE_NAME = "foods";
    final String TABLE_NAME = "favorite_foods";
    final String COLUMN_NAME = "name";
    final String COLUMN_PRICE = "price";
    final String USERNAME = "erikrios";
    final String PASSWORD = "";
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DATABASE_URL = "jdbc:mysql://localhost/" + DATABASE_NAME;

    private DatabaseConnection() {
        createConnection();
    }

    private void createConnection() {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(FavoriteFood favoriteFood) {
        try {
            statement = connection.createStatement();

            String foodName = favoriteFood.getName();
            int foodPrice = favoriteFood.getPrice();

            String format = "INSERT INTO %s (%s, %s) VALUES (%s, %d);";
            String sql = String.format(format, TABLE_NAME, COLUMN_NAME, COLUMN_PRICE, foodName, foodPrice);

            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
