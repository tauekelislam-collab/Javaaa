import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "zoo_user";
        String password = "zoo123";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected successfully!");
        } catch (Exception e) {
            System.out.println("Connection failed:");
            e.printStackTrace();
        }
    }
}
