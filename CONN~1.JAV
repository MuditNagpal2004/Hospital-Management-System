package hospital.management.system;
import java.sql.*;
public class Conn {
    Connection connection;
    Statement statement;
    public Conn() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to the hospital_management_system database
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital_management_system?useSSL=false", "root", "12345");
            // Create a statement object
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}