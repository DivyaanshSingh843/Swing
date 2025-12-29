package LibraryManagementSystem.src.com.library.dao;

import LibraryManagementSystem.src.com.library.model.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    
    /**
     * Add new member
     */
    public boolean addMember(Member member) {
        String query = "INSERT INTO Members (member_no, full_name, email, phone, address, status) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, member.getMemberNo());
            pstmt.setString(2, member.getFullName());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddress());
            pstmt.setString(6, member.getStatus());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding member: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Update existing member
     */
    public boolean updateMember(Member member) {
        String query = "UPDATE Members SET member_no = ?, full_name = ?, email = ?, " +
                       "phone = ?, address = ?, status = ? WHERE member_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, member.getMemberNo());
            pstmt.setString(2, member.getFullName());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddress());
            pstmt.setString(6, member.getStatus());
            pstmt.setInt(7, member.getMemberId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating member: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete member
     */
    public boolean deleteMember(int memberId) {
        String query = "DELETE FROM Members WHERE member_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, memberId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting member: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get member by ID
     */
    public Member getMemberById(int memberId) {
        String query = "SELECT * FROM Members WHERE member_id = ?";
        Member member = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                member = extractMemberFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting member: " + e.getMessage());
            e.printStackTrace();
        }
        
        return member;
    }
    
    /**
     * Get all members
     */
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM Members ORDER BY full_name";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                members.add(extractMemberFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all members: " + e.getMessage());
            e.printStackTrace();
        }
        
        return members;
    }
    
    /**
     * Search members by keyword
     */
    public List<Member> searchMembers(String keyword) {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM Members WHERE full_name LIKE ? OR member_no LIKE ? " +
                       "OR email LIKE ? OR phone LIKE ? ORDER BY full_name";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            pstmt.setString(4, searchPattern);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                members.add(extractMemberFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching members: " + e.getMessage());
            e.printStackTrace();
        }
        
        return members;
    }
    
    /**
     * Check if member number exists
     */
    public boolean memberNoExists(String memberNo) {
        String query = "SELECT COUNT(*) FROM Members WHERE member_no = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, memberNo);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking member number: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Helper method to extract Member from ResultSet
     */
    private Member extractMemberFromResultSet(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setMemberId(rs.getInt("member_id"));
        member.setMemberNo(rs.getString("member_no"));
        member.setFullName(rs.getString("full_name"));
        member.setEmail(rs.getString("email"));
        member.setPhone(rs.getString("phone"));
        member.setAddress(rs.getString("address"));
        member.setRegistrationDate(rs.getDate("registration_date"));
        member.setStatus(rs.getString("status"));
        return member;
    }
}
