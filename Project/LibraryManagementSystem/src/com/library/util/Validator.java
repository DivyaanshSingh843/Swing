package LibraryManagementSystem.src.com.library.util;

import java.util.regex.Pattern;

public class Validator {
    
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    // Phone validation pattern (10 digits)
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^[0-9]{10}$");
    
    /**
     * Check if string is not null and not empty
     */
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }
    
    /**
     * Validate email format
     */
    public static boolean isValidEmail(String email) {
        if (!isNotEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Validate phone number (10 digits)
     */
    public static boolean isValidPhone(String phone) {
        if (!isNotEmpty(phone)) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }
    
    /**
     * Check if string contains only numbers
     */
    public static boolean isNumeric(String text) {
        if (!isNotEmpty(text)) {
            return false;
        }
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Check if string is a valid positive integer
     */
    public static boolean isPositiveInteger(String text) {
        if (!isNumeric(text)) {
            return false;
        }
        return Integer.parseInt(text) > 0;
    }
    
    /**
     * Validate ISBN format (basic check - 10 or 13 digits)
     */
    public static boolean isValidISBN(String isbn) {
        if (!isNotEmpty(isbn)) {
            return false;
        }
        String cleanISBN = isbn.replaceAll("[^0-9]", "");
        return cleanISBN.length() == 10 || cleanISBN.length() == 13;
    }
}
