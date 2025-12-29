package LibraryManagementSystem.src.com.library.service;

import LibraryManagementSystem.src.com.library.dao.BookDAO;
import LibraryManagementSystem.src.com.library.dao.IssueRecordDAO;
import LibraryManagementSystem.src.com.library.dao.MemberDAO;
import LibraryManagementSystem.src.com.library.model.Book;
import LibraryManagementSystem.src.com.library.model.IssueRecord;
import LibraryManagementSystem.src.com.library.model.Member;
import LibraryManagementSystem.src.com.library.util.Constants;
import LibraryManagementSystem.src.com.library.util.DateUtil;
import java.util.Date;
import java.util.List;

public class IssueReturnService {
    
    private IssueRecordDAO issueRecordDAO;
    private BookDAO bookDAO;
    private MemberDAO memberDAO;
    
    public IssueReturnService() {
        this.issueRecordDAO = new IssueRecordDAO();
        this.bookDAO = new BookDAO();
        this.memberDAO = new MemberDAO();
    }
    
    /**
     * Issue book with validation
     */
    public String issueBook(int bookId, int memberId, int issuedBy) {
        // Check if book exists
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            return "Book not found!";
        }
        
        // Check if book is available
        if (book.getAvailableQuantity() <= 0) {
            return "Book is not available! All copies are issued.";
        }
        
        // Check if member exists
        Member member = memberDAO.getMemberById(memberId);
        if (member == null) {
            return "Member not found!";
        }
        
        // Check if member is active
        if (!Constants.STATUS_ACTIVE.equals(member.getStatus())) {
            return "Member is not active!";
        }
        
        // Check how many books member already has
        int issuedCount = issueRecordDAO.getIssuedBooksCountByMember(memberId);
        if (issuedCount >= Constants.MAX_BOOKS_PER_MEMBER) {
            return "Member has already issued maximum " + Constants.MAX_BOOKS_PER_MEMBER + " books!";
        }
        
        // Create issue record
        IssueRecord record = new IssueRecord();
        record.setBookId(bookId);
        record.setMemberId(memberId);
        record.setIssueDate(DateUtil.getCurrentDate());
        record.setDueDate(DateUtil.addDays(DateUtil.getCurrentDate(), Constants.DEFAULT_ISSUE_DAYS));
        record.setStatus(Constants.STATUS_ISSUED);
        record.setIssuedBy(issuedBy);
        
        // Issue book
        boolean issueSuccess = issueRecordDAO.issueBook(record);
        if (!issueSuccess) {
            return "Failed to issue book!";
        }
        
        // Update available quantity
        boolean updateSuccess = bookDAO.updateAvailableQuantity(bookId, -1);
        if (!updateSuccess) {
            return "Book issued but failed to update quantity!";
        }
        
        return "SUCCESS";
    }
    
    /**
     * Return book with fine calculation
     */
    public String returnBook(int issueId) {
        // Get issue record
        IssueRecord record = issueRecordDAO.getIssueRecordById(issueId);
        if (record == null) {
            return "Issue record not found!";
        }
        
        // Check if already returned
        if (Constants.STATUS_RETURNED.equals(record.getStatus())) {
            return "Book already returned!";
        }
        
        // Calculate fine
        Date currentDate = DateUtil.getCurrentDate();
        double fine = calculateFine(record.getDueDate(), currentDate);
        
        // Return book
        boolean returnSuccess = issueRecordDAO.returnBook(issueId, currentDate, fine);
        if (!returnSuccess) {
            return "Failed to return book!";
        }
        
        // Update available quantity
        boolean updateSuccess = bookDAO.updateAvailableQuantity(record.getBookId(), 1);
        if (!updateSuccess) {
            return "Book returned but failed to update quantity!";
        }
        
        if (fine > 0) {
            return "SUCCESS_WITH_FINE:" + fine;
        }
        return "SUCCESS";
    }
    
    /**
     * Calculate fine
     */
    public double calculateFine(Date dueDate, Date returnDate) {
        if (DateUtil.isAfter(returnDate, dueDate)) {
            int overdueDays = DateUtil.getDaysBetween(dueDate, returnDate);
            return overdueDays * Constants.FINE_PER_DAY;
        }
        return 0.0;
    }
    
    /**
     * Get pending returns
     */
    public List<IssueRecord> getPendingReturns() {
        return issueRecordDAO.getPendingReturns();
    }
    
    /**
     * Get overdue books
     */
    public List<IssueRecord> getOverdueBooks() {
        return issueRecordDAO.getOverdueBooks();
    }
    
    /**
     * Get issue history by member
     */
    public List<IssueRecord> getIssueHistoryByMember(int memberId) {
        return issueRecordDAO.getIssueHistoryByMember(memberId);
    }
    
    /**
     * Get all issued records
     */
    public List<IssueRecord> getAllIssuedRecords() {
        return issueRecordDAO.getAllIssuedRecords();
    }
    
    /**
     * Search issue records
     */
    public List<IssueRecord> searchIssueRecords(String keyword) {
        return issueRecordDAO.searchIssueRecords(keyword);
    }
    
    /**
     * Get issue record by ID
     */
    public IssueRecord getIssueRecordById(int issueId) {
        return issueRecordDAO.getIssueRecordById(issueId);
    }
}