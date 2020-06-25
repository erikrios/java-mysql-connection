package databases;

import models.FavoriteFood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    private static DatabaseConnection databaseConnection;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    final String DATABASE_NAME = "foods";
    final String TABLE_NAME = "favorite_foods";
    final String COLUMN_ID = "id";
    final String COLUMN_NAME = "name";
    final String COLUMN_PRICE = "price";
    final String USERNAME = "erikrios";
    final String PASSWORD = "";
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DATABASE_URL = "jdbc:mysql://localhost/" + DATABASE_NAME;

    private DatabaseConnection() {
        createConnection();
    }

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    private void createConnection() {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean add(FavoriteFood favoriteFood) {
        boolean result = false;

        try {
            String foodName = favoriteFood.getName();
            int foodPrice = favoriteFood.getPrice();

            String format = "INSERT INTO %s (%s, %s) VALUES (%s, %d)";
            String sql = String.format(format, TABLE_NAME, COLUMN_NAME, COLUMN_PRICE, foodName, foodPrice);

            result = statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<FavoriteFood> show() {
        List<FavoriteFood> favoriteFoods = new ArrayList<FavoriteFood>();

        try {
            String format = "SELECT * FROM %s";
            String sql = String.format(format, TABLE_NAME);

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);
                int price = resultSet.getInt(COLUMN_PRICE);

                FavoriteFood favoriteFood = new FavoriteFood(id, name, price);

                favoriteFoods.add(favoriteFood);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return favoriteFoods;
    }

    public boolean update(FavoriteFood favoriteFood) {
        boolean result = false;

        try {
            int foodId = favoriteFood.getId();
            String foodName = favoriteFood.getName();
            int foodPrice = favoriteFood.getPrice();

            String format = "UPDATE %s SET %s = %s, %s = %s WHERE %s = %d";
            String sql = String.format(format, TABLE_NAME, COLUMN_NAME, foodName, COLUMN_PRICE, foodPrice, COLUMN_ID, foodId);
            result = statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean delete(int id) {
        boolean result = false;

        try {
            String format = "DELETE FROM %s WHERE %s = %d";
            String sql = String.format(format, TABLE_NAME, COLUMN_ID, id);

            result = statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "connection=" + connection +
                ", statement=" + statement +
                ", resultSet=" + resultSet +
                ", DATABASE_NAME='" + DATABASE_NAME + '\'' +
                ", TABLE_NAME='" + TABLE_NAME + '\'' +
                ", COLUMN_ID='" + COLUMN_ID + '\'' +
                ", COLUMN_NAME='" + COLUMN_NAME + '\'' +
                ", COLUMN_PRICE='" + COLUMN_PRICE + '\'' +
                ", USERNAME='" + USERNAME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", JDBC_DRIVER='" + JDBC_DRIVER + '\'' +
                ", DATABASE_URL='" + DATABASE_URL + '\'' +
                '}' + "\nMemory Address = " + hashCode();
    }
}
