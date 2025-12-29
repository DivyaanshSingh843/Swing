package LibraryManagementSystem.src.com.library.dao;


import LibraryManagementSystem.src.com.library.model.IssueRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDAO {
    
    /**
     * Issue a book to member
     */
    public boolean issueBook(IssueRecord record) {
        String query = "INSERT INTO IssueRecords (book_id, member_id, issue_date, due_date, status, issued_by) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, record.getBookId());
            pstmt.setInt(2, record.getMemberId());
            pstmt.setDate(3, new java.sql.Date(record.getIssueDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(record.getDueDate().getTime()));
            pstmt.setString(5, record.getStatus());
            pstmt.setInt(6, record.getIssuedBy());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error issuing book: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Return a book
     */
    public boolean returnBook(int issueId, Date returnDate, double fine) {
        String query = "UPDATE IssueRecords SET return_date = ?, fine_amount = ?, status = ? " +
                       "WHERE issue_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setDate(1, new java.sql.Date(returnDate.getTime()));
            pstmt.setDouble(2, fine);
            pstmt.setString(3, "RETURNED");
            pstmt.setInt(4, issueId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error returning book: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get issue record by ID
     */
    public IssueRecord getIssueRecordById(int issueId) {
        String query = "SELECT ir.*, b.title as book_title, m.full_name as member_name " +
                       "FROM IssueRecords ir " +
                       "JOIN Books b ON ir.book_id = b.book_id " +
                       "JOIN Members m ON ir.member_id = m.member_id " +
                       "WHERE ir.issue_id = ?";
        IssueRecord record = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, issueId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                record = extractIssueRecordFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting issue record: " + e.getMessage());
            e.printStackTrace();
        }
        
        return record;
    }
    
    /**
     * Get all pending returns (status = ISSUED)
     */
    public List<IssueRecord> getPendingReturns() {
        List<IssueRecord> records = new ArrayList<>();
        String query = "SELECT ir.*, b.title as book_title, m.full_name as member_name " +
                       "FROM IssueRecords ir " +
                       "JOIN Books b ON ir.book_id = b.book_id " +
                       "JOIN Members m ON ir.member_id = m.member_id " +
                       "WHERE ir.status = 'ISSUED' " +
                       "ORDER BY ir.due_date";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                records.add(extractIssueRecordFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting pending returns: " + e.getMessage());
            e.printStackTrace();
        }
        
        return records;
    }
    
    /**
     * Get overdue books (due_date < today and status = ISSUED)
     */
    public List<IssueRecord> getOverdueBooks() {
        List<IssueRecord> records = new ArrayList<>();
        String query = "SELECT ir.*, b.title as book_title, m.full_name as member_name " +
                       "FROM IssueRecords ir " +
                       "JOIN Books b ON ir.book_id = b.book_id " +
                       "JOIN Members m ON ir.member_id = m.member_id " +
                       "WHERE ir.status = 'ISSUED' AND ir.due_date < CAST(GETDATE() AS DATE) " +
                       "ORDER BY ir.due_date";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                records.add(extractIssueRecordFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting overdue books: " + e.getMessage());
            e.printStackTrace();
        }
        
        return records;
    }
    
    /**
     * Get issue history for a member
     */
    public List<IssueRecord> getIssueHistoryByMember(int memberId) {
        List<IssueRecord> records = new ArrayList<>();
        String query = "SELECT ir.*, b.title as book_title, m.full_name as member_name " +
                       "FROM IssueRecords ir " +
                       "JOIN Books b ON ir.book_id = b.book_id " +
                       "JOIN Members m ON ir.member_id = m.member_id " +
                       "WHERE ir.member_id = ? " +
                       "ORDER BY ir.issue_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                records.add(extractIssueRecordFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting issue history: " + e.getMessage());
            e.printStackTrace();
        }
        
        return records;
    }
    
    /**
     * Get all issued records (for reports)
     */
    public List<IssueRecord> getAllIssuedRecords() {
        List<IssueRecord> records = new ArrayList<>();
        String query = "SELECT ir.*, b.title as book_title, m.full_name as member_name " +
                       "FROM IssueRecords ir " +
                       "JOIN Books b ON ir.book_id = b.book_id " +
                       "JOIN Members m ON ir.member_id = m.member_id " +
                       "ORDER BY ir.issue_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                records.add(extractIssueRecordFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all issued records: " + e.getMessage());
            e.printStackTrace();
        }
        
        return records;
    }
    
    /**
     * Count how many books a member currently has
     */
    public int getIssuedBooksCountByMember(int memberId) {
        String query = "SELECT COUNT(*) FROM IssueRecords WHERE member_id = ? AND status = 'ISSUED'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error counting issued books: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
    
    /**
     * Search issue records by book ID or member ID
     */
    public List<IssueRecord> searchIssueRecords(String keyword) {
        List<IssueRecord> records = new ArrayList<>();
        String query = "SELECT ir.*, b.title as book_title, m.full_name as member_name " +
                       "FROM IssueRecords ir " +
                       "JOIN Books b ON ir.book_id = b.book_id " +
                       "JOIN Members m ON ir.member_id = m.member_id " +
                       "WHERE b.title LIKE ? OR m.full_name LIKE ? OR m.member_no LIKE ? " +
                       "ORDER BY ir.issue_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                records.add(extractIssueRecordFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching issue records: " + e.getMessage());
            e.printStackTrace();
        }
        
        return records;
    }
    
    /**
     * Helper method to extract IssueRecord from ResultSet
     */
    private IssueRecord extractIssueRecordFromResultSet(ResultSet rs) throws SQLException {
        IssueRecord record = new IssueRecord();
        record.setIssueId(rs.getInt("issue_id"));
        record.setBookId(rs.getInt("book_id"));
        record.setMemberId(rs.getInt("member_id"));
        record.setIssueDate(rs.getDate("issue_date"));
        record.setDueDate(rs.getDate("due_date"));
        record.setReturnDate(rs.getDate("return_date"));
        record.setFineAmount(rs.getDouble("fine_amount"));
        record.setStatus(rs.getString("status"));
        record.setIssuedBy(rs.getInt("issued_by"));
        
        // Additional fields for display
        record.setBookTitle(rs.getString("book_title"));
        record.setMemberName(rs.getString("member_name"));
        
        return record;
    }
}