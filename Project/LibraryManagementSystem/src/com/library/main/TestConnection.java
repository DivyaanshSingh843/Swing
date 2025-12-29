package LibraryManagementSystem.src.com.library.main;


import LibraryManagementSystem.src.com.library.dao.DatabaseConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("Testing database connection...");
        
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn != null) {
            System.out.println("✓ Connection successful!");
            System.out.println("✓ Database is ready to use!");
        } else {
            System.out.println("✗ Connection failed!");
            System.out.println("✗ Please check your database credentials in Constants.java");
        }
        
        DatabaseConnection.closeConnection();
    }
}