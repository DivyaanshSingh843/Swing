package LibraryManagementSystem.src.com.library.service;


import LibraryManagementSystem.src.com.library.dao.BookDAO;
import LibraryManagementSystem.src.com.library.model.Book;
import LibraryManagementSystem.src.com.library.util.Validator;
import java.util.List;

public class BookService {
    
    private BookDAO bookDAO;
    
    public BookService() {
        this.bookDAO = new BookDAO();
    }
    
    /**
     * Add book with validation
     */
    public String addBook(Book book) {
        // Validate ISBN
        if (!Validator.isValidISBN(book.getIsbn())) {
            return "Invalid ISBN format!";
        }
        
        // Check if ISBN already exists
        if (bookDAO.isbnExists(book.getIsbn())) {
            return "ISBN already exists!";
        }
        
        // Validate title
        if (!Validator.isNotEmpty(book.getTitle())) {
            return "Title is required!";
        }
        
        // Validate author
        if (!Validator.isNotEmpty(book.getAuthor())) {
            return "Author name is required!";
        }
        
        // Validate quantity
        if (book.getQuantity() <= 0) {
            return "Quantity must be greater than 0!";
        }
        
        // Set available quantity same as quantity initially
        book.setAvailableQuantity(book.getQuantity());
        
        // Add book
        boolean success = bookDAO.addBook(book);
        return success ? "SUCCESS" : "Failed to add book!";
    }
    
    /**
     * Update book with validation
     */
    public String updateBook(Book book) {
        // Validate title
        if (!Validator.isNotEmpty(book.getTitle())) {
            return "Title is required!";
        }
        
        // Validate author
        if (!Validator.isNotEmpty(book.getAuthor())) {
            return "Author name is required!";
        }
        
        // Update book
        boolean success = bookDAO.updateBook(book);
        return success ? "SUCCESS" : "Failed to update book!";
    }
    
    /**
     * Delete book
     */
    public String deleteBook(int bookId) {
        // Check if book exists
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            return "Book not found!";
        }
        
        // Check if book is currently issued
        if (book.getAvailableQuantity() < book.getQuantity()) {
            return "Cannot delete! Some copies are currently issued.";
        }
        
        boolean success = bookDAO.deleteBook(bookId);
        return success ? "SUCCESS" : "Failed to delete book!";
    }
    
    /**
     * Get book by ID
     */
    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }
    
    /**
     * Get all books
     */
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
    
    /**
     * Search books
     */
    public List<Book> searchBooks(String keyword) {
        if (!Validator.isNotEmpty(keyword)) {
            return getAllBooks();
        }
        return bookDAO.searchBooks(keyword);
    }
}