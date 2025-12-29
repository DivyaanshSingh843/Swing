package LibraryManagementSystem.src.com.library.ui;

import LibraryManagementSystem.src.com.library.model.Member;
import LibraryManagementSystem.src.com.library.model.User;
import LibraryManagementSystem.src.com.library.service.MemberService;
import LibraryManagementSystem.src.com.library.util.Constants;
import LibraryManagementSystem.src.com.library.util.DateUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MemberManagementPanel extends JPanel {
    
    private User currentUser;
    private MemberService memberService;
    
    // Form components
    private JTextField txtMemberId;
    private JTextField txtMemberNo;
    private JTextField txtFullName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextArea txtAddress;
    private JComboBox<String> cmbStatus;
    private JTextField txtRegDate;
    private JTextField txtSearch;
    
    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnRefresh;
    
    // Table
    private JTable memberTable;
    private DefaultTableModel tableModel;
    
    public MemberManagementPanel(User user) {
        this.currentUser = user;
        this.memberService = new MemberService();
        initComponents();
        loadMembersTable();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Form Panel (Left side)
        JPanel formPanel = createFormPanel();
        
        // Table Panel (Right side)
        JPanel tablePanel = createTablePanel();
        
        // Add panels
        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(350, 600));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
            "Member Details",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(41, 128, 185)
        ));
        
        // Form fields panel
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Member ID (hidden/readonly)
        gbc.gridx = 0; gbc.gridy = 0;
        fieldsPanel.add(new JLabel("Member ID:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtMemberId = new JTextField(15);
        txtMemberId.setEditable(false);
        txtMemberId.setBackground(Color.LIGHT_GRAY);
        fieldsPanel.add(txtMemberId, gbc);
        
        // Member Number
        gbc.gridx = 0; gbc.gridy = 1;
        fieldsPanel.add(new JLabel("Member No: *"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtMemberNo = new JTextField(15);
        fieldsPanel.add(txtMemberNo, gbc);
        
        // Full Name
        gbc.gridx = 0; gbc.gridy = 2;
        fieldsPanel.add(new JLabel("Full Name: *"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        txtFullName = new JTextField(15);
        fieldsPanel.add(txtFullName, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 3;
        fieldsPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        txtEmail = new JTextField(15);
        fieldsPanel.add(txtEmail, gbc);
        
        // Phone
        gbc.gridx = 0; gbc.gridy = 4;
        fieldsPanel.add(new JLabel("Phone:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        txtPhone = new JTextField(15);
        fieldsPanel.add(txtPhone, gbc);
        
        // Address
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        fieldsPanel.add(new JLabel("Address:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        txtAddress = new JTextArea(3, 15);
        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);
        JScrollPane addressScroll = new JScrollPane(txtAddress);
        fieldsPanel.add(addressScroll, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 6;
        fieldsPanel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 6;
        cmbStatus = new JComboBox<>(new String[]{Constants.STATUS_ACTIVE, Constants.STATUS_INACTIVE});
        fieldsPanel.add(cmbStatus, gbc);
        
        // Registration Date
        gbc.gridx = 0; gbc.gridy = 7;
        fieldsPanel.add(new JLabel("Reg. Date:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 7;
        txtRegDate = new JTextField(15);
        txtRegDate.setEditable(false);
        txtRegDate.setBackground(Color.LIGHT_GRAY);
        fieldsPanel.add(txtRegDate, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        
        btnAdd = createStyledButton("Add", new Color(46, 204, 113));
        btnUpdate = createStyledButton("Update", new Color(52, 152, 219));
        btnDelete = createStyledButton("Delete", new Color(231, 76, 60));
        btnClear = createStyledButton("Clear", new Color(149, 165, 166));
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        // Add action listeners
        btnAdd.addActionListener(e -> addMember());
        btnUpdate.addActionListener(e -> updateMember());
        btnDelete.addActionListener(e -> deleteMember());
        btnClear.addActionListener(e -> clearFields());
        
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        searchPanel.add(new JLabel("Search:"));
        txtSearch = new JTextField(25);
        searchPanel.add(txtSearch);
        
        btnSearch = createStyledButton("Search", new Color(52, 152, 219));
        btnRefresh = createStyledButton("Refresh", new Color(46, 204, 113));
        
        searchPanel.add(btnSearch);
        searchPanel.add(btnRefresh);
        
        btnSearch.addActionListener(e -> searchMembers());
        btnRefresh.addActionListener(e -> loadMembersTable());
        
        // Table
        String[] columns = {"ID", "Member No", "Full Name", "Email", "Phone", "Status", "Reg. Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        memberTable = new JTable(tableModel);
        memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        memberTable.setRowHeight(25);
        memberTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        memberTable.getTableHeader().setBackground(new Color(41, 128, 185));
        memberTable.getTableHeader().setForeground(Color.WHITE);
        
        // Table selection listener
        memberTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromTable();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(memberTable);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 35));
        return button;
    }
    
    private void addMember() {
        try {
            Member member = new Member();
            member.setMemberNo(txtMemberNo.getText().trim());
            member.setFullName(txtFullName.getText().trim());
            member.setEmail(txtEmail.getText().trim());
            member.setPhone(txtPhone.getText().trim());
            member.setAddress(txtAddress.getText().trim());
            member.setStatus((String) cmbStatus.getSelectedItem());
            
            // Add member through service
            String result = memberService.addMember(member);
            
            if ("SUCCESS".equals(result)) {
                JOptionPane.showMessageDialog(this,
                    "Member added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadMembersTable();
            } else {
                JOptionPane.showMessageDialog(this,
                    result,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error adding member: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateMember() {
        try {
            if (txtMemberId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please select a member to update!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Member member = new Member();
            member.setMemberId(Integer.parseInt(txtMemberId.getText().trim()));
            member.setMemberNo(txtMemberNo.getText().trim());
            member.setFullName(txtFullName.getText().trim());
            member.setEmail(txtEmail.getText().trim());
            member.setPhone(txtPhone.getText().trim());
            member.setAddress(txtAddress.getText().trim());
            member.setStatus((String) cmbStatus.getSelectedItem());
            
            String result = memberService.updateMember(member);
            
            if ("SUCCESS".equals(result)) {
                JOptionPane.showMessageDialog(this,
                    "Member updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadMembersTable();
            } else {
                JOptionPane.showMessageDialog(this,
                    result,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error updating member: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteMember() {
        try {
            if (txtMemberId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please select a member to delete!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this member?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                int memberId = Integer.parseInt(txtMemberId.getText().trim());
                String result = memberService.deleteMember(memberId);
                
                if ("SUCCESS".equals(result)) {
                    JOptionPane.showMessageDialog(this,
                        "Member deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                    loadMembersTable();
                } else {
                    JOptionPane.showMessageDialog(this,
                        result,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error deleting member: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchMembers() {
        String keyword = txtSearch.getText().trim();
        
        if (keyword.isEmpty()) {
            loadMembersTable();
            return;
        }
        
        List<Member> members = memberService.searchMembers(keyword);
        updateTable(members);
    }
    
    private void loadMembersTable() {
        List<Member> members = memberService.getAllMembers();
        updateTable(members);
    }
    
    private void updateTable(List<Member> members) {
        tableModel.setRowCount(0);
        
        for (Member member : members) {
            Object[] row = {
                member.getMemberId(),
                member.getMemberNo(),
                member.getFullName(),
                member.getEmail(),
                member.getPhone(),
                member.getStatus(),
                member.getRegistrationDate() != null ? 
                    DateUtil.formatDate(member.getRegistrationDate()) : ""
            };
            tableModel.addRow(row);
        }
    }
    
    private void fillFormFromTable() {
        int selectedRow = memberTable.getSelectedRow();
        
        if (selectedRow >= 0) {
            txtMemberId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtMemberNo.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtFullName.setText(tableModel.getValueAt(selectedRow, 2).toString());
            
            Object email = tableModel.getValueAt(selectedRow, 3);
            txtEmail.setText(email != null ? email.toString() : "");
            
            Object phone = tableModel.getValueAt(selectedRow, 4);
            txtPhone.setText(phone != null ? phone.toString() : "");
            
            cmbStatus.setSelectedItem(tableModel.getValueAt(selectedRow, 5));
            txtRegDate.setText(tableModel.getValueAt(selectedRow, 6).toString());
            
            // Load full member details for address
            int memberId = Integer.parseInt(txtMemberId.getText());
            Member member = memberService.getMemberById(memberId);
            if (member != null) {
                txtAddress.setText(member.getAddress() != null ? member.getAddress() : "");
            }
        }
    }
    
    private void clearFields() {
        txtMemberId.setText("");
        txtMemberNo.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        cmbStatus.setSelectedIndex(0);
        txtRegDate.setText("");
        txtSearch.setText("");
        memberTable.clearSelection();
    }
}