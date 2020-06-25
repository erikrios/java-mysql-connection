import databases.DatabaseConnection;

public class Application {

    public static void main(String[] args) {
        DatabaseConnection databaseConnection1 = DatabaseConnection.getInstance();
        DatabaseConnection databaseConnection2 = DatabaseConnection.getInstance();
        DatabaseConnection databaseConnection3 = DatabaseConnection.getInstance();
        DatabaseConnection databaseConnection4 = DatabaseConnection.getInstance();
        DatabaseConnection databaseConnection5 = DatabaseConnection.getInstance();

        System.out.println(databaseConnection1);
        System.out.println(databaseConnection2);
        System.out.println(databaseConnection3);
        System.out.println(databaseConnection4);
        System.out.println(databaseConnection5);
    }
}
