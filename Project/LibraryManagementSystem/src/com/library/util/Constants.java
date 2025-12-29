package LibraryManagementSystem.src.com.library.util;


public class Constants {
    // Database Configuration
    public static final String DB_URL = "jdbc:sqlserver://DESKTOP-42S24CL\\DIVYAANSH;databaseName=LibraryDB;encrypt=false;trustServerCertificate=true";
    public static final String DB_USER = "Divyaansh";  // Apna username daalo
    public static final String DB_PASSWORD = "12345678";  // Apna password daalo
    
    // Date Format
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    
    // Issue/Return Configuration
    public static final int DEFAULT_ISSUE_DAYS = 14;  // 14 days default
    public static final double FINE_PER_DAY = 5.0;    // Rs. 5 per day
    public static final int MAX_BOOKS_PER_MEMBER = 3;
    
    // Status Constants
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";
    public static final String STATUS_ISSUED = "ISSUED";
    public static final String STATUS_RETURNED = "RETURNED";
    
    // Role Constants
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_LIBRARIAN = "LIBRARIAN";
    
    // Book Categories
    public static final String[] BOOK_CATEGORIES = {
        "Fiction", "Non-Fiction", "Science", "Technology", 
        "History", "Biography", "Self-Help", "Children", 
        "Comics", "Academic", "Other"
    };
}
