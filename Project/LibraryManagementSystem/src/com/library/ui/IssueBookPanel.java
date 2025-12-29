package LibraryManagementSystem.src.com.library.ui;

import LibraryManagementSystem.src.com.library.model.Book;
import LibraryManagementSystem.src.com.library.model.Member;
import LibraryManagementSystem.src.com.library.model.User;
import LibraryManagementSystem.src.com.library.service.BookService;
import LibraryManagementSystem.src.com.library.service.IssueReturnService;
import LibraryManagementSystem.src.com.library.service.MemberService;
import LibraryManagementSystem.src.com.library.util.Constants;
import LibraryManagementSystem.src.com.library.util.DateUtil;
import javax.swing.*;
import java.awt.*;

public class IssueBookPanel extends JPanel {
    
    private User currentUser;
    private BookService bookService;
    private MemberService memberService;
    private IssueReturnService issueReturnService;
    
    // Components
    private JTextField txtBookId;
    private JButton btnSearchBook;
    private JTextArea txtBookDetails;
    
    private JTextField txtMemberId;
    private JButton btnSearchMember;
    private JTextArea txtMemberDetails;
    
    private JLabel lblIssueDate;
    private JLabel lblDueDate;
    private JSpinner spinnerDays;
    
    private JButton btnIssueBook;
    private JButton btnClear;
    
    private Book selectedBook;
    private Member selectedMember;
    
    public IssueBookPanel(User user) {
        this.currentUser = user;
        this.bookService = new BookService();
        this.memberService = new MemberService();
        this.issueReturnService = new IssueReturnService();
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        
        // Book Search Panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.4;
        mainPanel.add(createBookSearchPanel(), gbc);
        
        // Member Search Panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(createMemberSearchPanel(), gbc);
        
        // Issue Details Panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.3;
        mainPanel.add(createIssueDetailsPanel(), gbc);
        
        // Button Panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(createButtonPanel(), gbc);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Set default dates
        updateDates();
    }
    
    private JPanel createBookSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(46, 204, 113), 2),
            "Select Book",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(46, 204, 113)
        ));
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Book ID:"));
        
        txtBookId = new JTextField(15);
        searchPanel.add(txtBookId);
        
        btnSearchBook = createStyledButton("Search Book", new Color(46, 204, 113));
        btnSearchBook.addActionListener(e -> searchBook());
        searchPanel.add(btnSearchBook);
        
        // Details area
        txtBookDetails = new JTextArea(8, 30);
        txtBookDetails.setEditable(false);
        txtBookDetails.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtBookDetails.setBackground(new Color(240, 240, 240));
        txtBookDetails.setText("No book selected");
        
        JScrollPane scrollPane = new JScrollPane(txtBookDetails);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createMemberSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "Select Member",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(52, 152, 219)
        ));
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Member ID:"));
        
        txtMemberId = new JTextField(15);
        searchPanel.add(txtMemberId);
        
        btnSearchMember = createStyledButton("Search Member", new Color(52, 152, 219));
        btnSearchMember.addActionListener(e -> searchMember());
        searchPanel.add(btnSearchMember);
        
        // Details area
        txtMemberDetails = new JTextArea(8, 30);
        txtMemberDetails.setEditable(false);
        txtMemberDetails.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtMemberDetails.setBackground(new Color(240, 240, 240));
        txtMemberDetails.setText("No member selected");
        
        JScrollPane scrollPane = new JScrollPane(txtMemberDetails);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createIssueDetailsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(155, 89, 182), 2),
            "Issue Details",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(155, 89, 182)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Issue Date
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblIssueDateLabel = new JLabel("Issue Date:");
        lblIssueDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(lblIssueDateLabel, gbc);
        
        gbc.gridx = 1;
        lblIssueDate = new JLabel(DateUtil.formatDate(DateUtil.getCurrentDate()));
        lblIssueDate.setFont(new Font("Arial", Font.PLAIN, 13));
        panel.add(lblIssueDate, gbc);
        
        // Number of Days
        gbc.gridx = 2;
        JLabel lblDaysLabel = new JLabel("Number of Days:");
        lblDaysLabel.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(lblDaysLabel, gbc);
        
        gbc.gridx = 3;
        SpinnerModel model = new SpinnerNumberModel(
            Constants.DEFAULT_ISSUE_DAYS, // initial value
            1,                             // min
            90,                            // max
            1                              // step
        );
        spinnerDays = new JSpinner(model);
        spinnerDays.setFont(new Font("Arial", Font.PLAIN, 13));
        ((JSpinner.DefaultEditor) spinnerDays.getEditor()).getTextField().setColumns(5);
        spinnerDays.addChangeListener(e -> updateDates());
        panel.add(spinnerDays, gbc);
        
        // Due Date
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblDueDateLabel = new JLabel("Due Date:");
        lblDueDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(lblDueDateLabel, gbc);
        
        gbc.gridx = 1;
        lblDueDate = new JLabel(DateUtil.formatDate(
            DateUtil.addDays(DateUtil.getCurrentDate(), Constants.DEFAULT_ISSUE_DAYS)
        ));
        lblDueDate.setFont(new Font("Arial", Font.PLAIN, 13));
        lblDueDate.setForeground(new Color(231, 76, 60));
        panel.add(lblDueDate, gbc);
        
        // Fine info
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        JLabel lblFineInfo = new JLabel("Fine: ₹" + Constants.FINE_PER_DAY + " per day after due date");
        lblFineInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        lblFineInfo.setForeground(Color.GRAY);
        panel.add(lblFineInfo, gbc);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btnIssueBook = createStyledButton("Issue Book", new Color(46, 204, 113));
        btnIssueBook.setPreferredSize(new Dimension(150, 40));
        btnIssueBook.setFont(new Font("Arial", Font.BOLD, 14));
        btnIssueBook.addActionListener(e -> issueBook());
        
        btnClear = createStyledButton("Clear", new Color(149, 165, 166));
        btnClear.setPreferredSize(new Dimension(150, 40));
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.addActionListener(e -> clearFields());
        
        panel.add(btnIssueBook);
        panel.add(btnClear);
        
        return panel;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private void searchBook() {
        String bookIdStr = txtBookId.getText().trim();
        
        if (bookIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter Book ID!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int bookId = Integer.parseInt(bookIdStr);
            selectedBook = bookService.getBookById(bookId);
            
            if (selectedBook != null) {
                // Check if book is available
                if (selectedBook.getAvailableQuantity() <= 0) {
                    txtBookDetails.setText("Book found but NOT AVAILABLE!\n\n" + getBookDetailsText(selectedBook));
                    txtBookDetails.setForeground(Color.RED);
                    selectedBook = null;
                    JOptionPane.showMessageDialog(this,
                        "This book is currently not available!",
                        "Not Available",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    txtBookDetails.setText(getBookDetailsText(selectedBook));
                    txtBookDetails.setForeground(Color.BLACK);
                }
            } else {
                txtBookDetails.setText("Book not found!");
                txtBookDetails.setForeground(Color.RED);
                selectedBook = null;
                JOptionPane.showMessageDialog(this,
                    "Book not found with ID: " + bookId,
                    "Not Found",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Invalid Book ID! Please enter a number.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchMember() {
        String memberIdStr = txtMemberId.getText().trim();
        
        if (memberIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter Member ID!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int memberId = Integer.parseInt(memberIdStr);
            selectedMember = memberService.getMemberById(memberId);
            
            if (selectedMember != null) {
                // Check if member is active
                if (!Constants.STATUS_ACTIVE.equals(selectedMember.getStatus())) {
                    txtMemberDetails.setText("Member found but INACTIVE!\n\n" + getMemberDetailsText(selectedMember));
                    txtMemberDetails.setForeground(Color.RED);
                    selectedMember = null;
                    JOptionPane.showMessageDialog(this,
                        "This member is inactive!",
                        "Inactive Member",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    // Check how many books member already has
                    int issuedCount = issueReturnService.getIssueHistoryByMember(memberId)
                        .stream()
                        .filter(ir -> Constants.STATUS_ISSUED.equals(ir.getStatus()))
                        .toArray().length;
                    
                    txtMemberDetails.setText(getMemberDetailsText(selectedMember) + 
                        "\n\nCurrently Issued: " + issuedCount + "/" + Constants.MAX_BOOKS_PER_MEMBER);
                    txtMemberDetails.setForeground(Color.BLACK);
                }
            } else {
                txtMemberDetails.setText("Member not found!");
                txtMemberDetails.setForeground(Color.RED);
                selectedMember = null;
                JOptionPane.showMessageDialog(this,
                    "Member not found with ID: " + memberId,
                    "Not Found",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Invalid Member ID! Please enter a number.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void issueBook() {
        // Validate book selection
        if (selectedBook == null) {
            JOptionPane.showMessageDialog(this,
                "Please search and select a book first!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validate member selection
        if (selectedMember == null) {
            JOptionPane.showMessageDialog(this,
                "Please search and select a member first!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Confirm issue
        int confirm = JOptionPane.showConfirmDialog(this,
            "Issue this book to " + selectedMember.getFullName() + "?\n\n" +
            "Book: " + selectedBook.getTitle() + "\n" +
            "Due Date: " + lblDueDate.getText(),
            "Confirm Issue",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String result = issueReturnService.issueBook(
                selectedBook.getBookId(),
                selectedMember.getMemberId(),
                currentUser.getUserId()
            );
            
            if ("SUCCESS".equals(result)) {
                JOptionPane.showMessageDialog(this,
                    "Book issued successfully!\n\n" +
                    "Book: " + selectedBook.getTitle() + "\n" +
                    "Member: " + selectedMember.getFullName() + "\n" +
                    "Due Date: " + lblDueDate.getText(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    result,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void updateDates() {
        int days = (Integer) spinnerDays.getValue();
        lblDueDate.setText(DateUtil.formatDate(
            DateUtil.addDays(DateUtil.getCurrentDate(), days)
        ));
    }
    
    private String getBookDetailsText(Book book) {
        return String.format(
            "Book ID: %d\n" +
            "ISBN: %s\n" +
            "Title: %s\n" +
            "Author: %s\n" +
            "Publisher: %s\n" +
            "Category: %s\n" +
            "Total Quantity: %d\n" +
            "Available: %d\n" +
            "Rack: %s",
            book.getBookId(),
            book.getIsbn(),
            book.getTitle(),
            book.getAuthor(),
            book.getPublisher(),
            book.getCategory(),
            book.getQuantity(),
            book.getAvailableQuantity(),
            book.getRackNo()
        );
    }
    
    private String getMemberDetailsText(Member member) {
        return String.format(
            "Member ID: %d\n" +
            "Member No: %s\n" +
            "Name: %s\n" +
            "Email: %s\n" +
            "Phone: %s\n" +
            "Status: %s\n" +
            "Registered: %s",
            member.getMemberId(),
            member.getMemberNo(),
            member.getFullName(),
            member.getEmail() != null ? member.getEmail() : "N/A",
            member.getPhone() != null ? member.getPhone() : "N/A",
            member.getStatus(),
            member.getRegistrationDate() != null ? 
                DateUtil.formatDate(member.getRegistrationDate()) : "N/A"
        );
    }
    
    private void clearFields() {
        txtBookId.setText("");
        txtMemberId.setText("");
        txtBookDetails.setText("No book selected");
        txtBookDetails.setForeground(Color.BLACK);
        txtMemberDetails.setText("No member selected");
        txtMemberDetails.setForeground(Color.BLACK);
        selectedBook = null;
        selectedMember = null;
        spinnerDays.setValue(Constants.DEFAULT_ISSUE_DAYS);
        updateDates();
    }
}