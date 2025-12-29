package LibraryManagementSystem.src.com.library.service;


import LibraryManagementSystem.src.com.library.dao.MemberDAO;
import LibraryManagementSystem.src.com.library.model.Member;
import LibraryManagementSystem.src.com.library.util.Constants;
import LibraryManagementSystem.src.com.library.util.Validator;
import java.util.List;

public class MemberService {
    
    private MemberDAO memberDAO;
    
    public MemberService() {
        this.memberDAO = new MemberDAO();
    }
    
    /**
     * Add member with validation
     */
    public String addMember(Member member) {
        // Validate member number
        if (!Validator.isNotEmpty(member.getMemberNo())) {
            return "Member number is required!";
        }
        
        // Check if member number already exists
        if (memberDAO.memberNoExists(member.getMemberNo())) {
            return "Member number already exists!";
        }
        
        // Validate full name
        if (!Validator.isNotEmpty(member.getFullName())) {
            return "Full name is required!";
        }
        
        // Validate email
        if (Validator.isNotEmpty(member.getEmail()) && !Validator.isValidEmail(member.getEmail())) {
            return "Invalid email format!";
        }
        
        // Validate phone
        if (Validator.isNotEmpty(member.getPhone()) && !Validator.isValidPhone(member.getPhone())) {
            return "Invalid phone number! Must be 10 digits.";
        }
        
        // Set default status if not provided
        if (!Validator.isNotEmpty(member.getStatus())) {
            member.setStatus(Constants.STATUS_ACTIVE);
        }
        
        // Add member
        boolean success = memberDAO.addMember(member);
        return success ? "SUCCESS" : "Failed to add member!";
    }
    
    /**
     * Update member with validation
     */
    public String updateMember(Member member) {
        // Validate full name
        if (!Validator.isNotEmpty(member.getFullName())) {
            return "Full name is required!";
        }
        
        // Validate email
        if (Validator.isNotEmpty(member.getEmail()) && !Validator.isValidEmail(member.getEmail())) {
            return "Invalid email format!";
        }
        
        // Validate phone
        if (Validator.isNotEmpty(member.getPhone()) && !Validator.isValidPhone(member.getPhone())) {
            return "Invalid phone number! Must be 10 digits.";
        }
        
        // Update member
        boolean success = memberDAO.updateMember(member);
        return success ? "SUCCESS" : "Failed to update member!";
    }
    
    /**
     * Delete member
     */
    public String deleteMember(int memberId) {
        // Check if member exists
        Member member = memberDAO.getMemberById(memberId);
        if (member == null) {
            return "Member not found!";
        }
        
        boolean success = memberDAO.deleteMember(memberId);
        return success ? "SUCCESS" : "Failed to delete member!";
    }
    
    /**
     * Get member by ID
     */
    public Member getMemberById(int memberId) {
        return memberDAO.getMemberById(memberId);
    }
    
    /**
     * Get all members
     */
    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }
    
    /**
     * Search members
     */
    public List<Member> searchMembers(String keyword) {
        if (!Validator.isNotEmpty(keyword)) {
            return getAllMembers();
        }
        return memberDAO.searchMembers(keyword);
    }
}