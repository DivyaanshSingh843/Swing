package LibraryManagementSystem.src.com.library.ui;

import LibraryManagementSystem.src.com.library.model.IssueRecord;
import LibraryManagementSystem.src.com.library.model.User;
import LibraryManagementSystem.src.com.library.service.IssueReturnService;
import LibraryManagementSystem.src.com.library.util.Constants;
import LibraryManagementSystem.src.com.library.util.DateUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReturnBookPanel extends JPanel {
    
    private User currentUser;
    private IssueReturnService issueReturnService;
    
    // Components
    private JTextField txtIssueId;
    private JButton btnSearch;
    private JButton btnRefresh;
    
    private JTextArea txtIssueDetails;
    
    private JLabel lblReturnDate;
    private JLabel lblFineAmount;
    
    private JButton btnReturnBook;
    private JButton btnClear;
    
    // Table
    private JTable pendingTable;
    private DefaultTableModel tableModel;
    
    private IssueRecord selectedIssueRecord;
    
    public ReturnBookPanel(User user) {
        this.currentUser = user;
        this.issueReturnService = new IssueReturnService();
        initComponents();
        loadPendingReturns();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left Panel - Return Form
        JPanel leftPanel = createReturnFormPanel();
        
        // Right Panel - Pending Returns Table
        JPanel rightPanel = createPendingReturnsPanel();
        
        // Add panels
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
    
    private JPanel createReturnFormPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setPreferredSize(new Dimension(450, 600));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(231, 76, 60), 2),
            "Return Book",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(231, 76, 60)
        ));
        
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setMaximumSize(new Dimension(450, 50));
        searchPanel.add(new JLabel("Issue ID:"));
        
        txtIssueId = new JTextField(15);
        searchPanel.add(txtIssueId);
        
        btnSearch = createStyledButton("Search", new Color(52, 152, 219));
        btnSearch.addActionListener(e -> searchIssueRecord());
        searchPanel.add(btnSearch);
        
        contentPanel.add(searchPanel);
        contentPanel.add(Box.createVerticalStrut(10));
        
        // Issue Details Panel
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Issue Details"));
        detailsPanel.setMaximumSize(new Dimension(450, 200));
        
        txtIssueDetails = new JTextArea(8, 30);
        txtIssueDetails.setEditable(false);
        txtIssueDetails.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtIssueDetails.setBackground(new Color(240, 240, 240));
        txtIssueDetails.setText("No issue record selected");
        
        JScrollPane scrollPane = new JScrollPane(txtIssueDetails);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);
        
        contentPanel.add(detailsPanel);
        contentPanel.add(Box.createVerticalStrut(15));
        
        // Return Information Panel
        JPanel returnInfoPanel = new JPanel(new GridBagLayout());
        returnInfoPanel.setBorder(BorderFactory.createTitledBorder("Return Information"));
        returnInfoPanel.setMaximumSize(new Dimension(450, 150));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Return Date
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblReturnDateLabel = new JLabel("Return Date:");
        lblReturnDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        returnInfoPanel.add(lblReturnDateLabel, gbc);
        
        gbc.gridx = 1;
        lblReturnDate = new JLabel(DateUtil.formatDate(DateUtil.getCurrentDate()));
        lblReturnDate.setFont(new Font("Arial", Font.PLAIN, 13));
        returnInfoPanel.add(lblReturnDate, gbc);
        
        // Fine Amount
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblFineLabel = new JLabel("Fine Amount:");
        lblFineLabel.setFont(new Font("Arial", Font.BOLD, 13));
        returnInfoPanel.add(lblFineLabel, gbc);
        
        gbc.gridx = 1;
        lblFineAmount = new JLabel("₹ 0.00");
        lblFineAmount.setFont(new Font("Arial", Font.BOLD, 14));
        lblFineAmount.setForeground(new Color(231, 76, 60));
        returnInfoPanel.add(lblFineAmount, gbc);
        
        // Fine info
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JLabel lblFineInfo = new JLabel("(₹" + Constants.FINE_PER_DAY + " per day after due date)");
        lblFineInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        lblFineInfo.setForeground(Color.GRAY);
        returnInfoPanel.add(lblFineInfo, gbc);
        
        contentPanel.add(returnInfoPanel);
        contentPanel.add(Box.createVerticalStrut(20));
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setMaximumSize(new Dimension(450, 60));
        
        btnReturnBook = createStyledButton("Return Book", new Color(46, 204, 113));
        btnReturnBook.setPreferredSize(new Dimension(140, 40));
        btnReturnBook.setFont(new Font("Arial", Font.BOLD, 14));
        btnReturnBook.addActionListener(e -> returnBook());
        
        btnClear = createStyledButton("Clear", new Color(149, 165, 166));
        btnClear.setPreferredSize(new Dimension(140, 40));
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.addActionListener(e -> clearFields());
        
        buttonPanel.add(btnReturnBook);
        buttonPanel.add(btnClear);
        
        contentPanel.add(buttonPanel);
        
        panel.add(contentPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createPendingReturnsPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "Pending Returns",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(52, 152, 219)
        ));
        
        // Top panel with refresh button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnRefresh = createStyledButton("Refresh", new Color(46, 204, 113));
        btnRefresh.addActionListener(e -> loadPendingReturns());
        topPanel.add(btnRefresh);
        
        // Table
        String[] columns = {"Issue ID", "Book Title", "Member Name", "Issue Date", "Due Date", "Days Overdue"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        pendingTable = new JTable(tableModel);
        pendingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pendingTable.setRowHeight(25);
        pendingTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        pendingTable.getTableHeader().setBackground(new Color(52, 152, 219));
        pendingTable.getTableHeader().setForeground(Color.WHITE);
        
        // Table selection listener
        pendingTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromTable();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(pendingTable);
        
        panel.add(topPanel, BorderLayout.NORTH);
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
        return button;
    }
    
    private void searchIssueRecord() {
        String issueIdStr = txtIssueId.getText().trim();
        
        if (issueIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter Issue ID!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int issueId = Integer.parseInt(issueIdStr);
            selectedIssueRecord = issueReturnService.getIssueRecordById(issueId);
            
            if (selectedIssueRecord != null) {
                // Check if already returned
                if (Constants.STATUS_RETURNED.equals(selectedIssueRecord.getStatus())) {
                    txtIssueDetails.setText("This book is already returned!\n\n" + 
                        getIssueDetailsText(selectedIssueRecord));
                    txtIssueDetails.setForeground(Color.RED);
                    lblFineAmount.setText("Already Returned");
                    selectedIssueRecord = null;
                    JOptionPane.showMessageDialog(this,
                        "This book has already been returned!",
                        "Already Returned",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    txtIssueDetails.setText(getIssueDetailsText(selectedIssueRecord));
                    txtIssueDetails.setForeground(Color.BLACK);
                    calculateAndDisplayFine();
                }
            } else {
                txtIssueDetails.setText("Issue record not found!");
                txtIssueDetails.setForeground(Color.RED);
                lblFineAmount.setText("₹ 0.00");
                selectedIssueRecord = null;
                JOptionPane.showMessageDialog(this,
                    "Issue record not found with ID: " + issueId,
                    "Not Found",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Invalid Issue ID! Please enter a number.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void returnBook() {
        if (selectedIssueRecord == null) {
            JOptionPane.showMessageDialog(this,
                "Please search and select an issue record first!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Calculate fine
        double fine = issueReturnService.calculateFine(
            selectedIssueRecord.getDueDate(),
            DateUtil.getCurrentDate()
        );
        
        // Confirm return
        String message = "Return this book?\n\n" +
            "Book: " + selectedIssueRecord.getBookTitle() + "\n" +
            "Member: " + selectedIssueRecord.getMemberName() + "\n" +
            "Return Date: " + lblReturnDate.getText();
        
        if (fine > 0) {
            message += "\n\nFine Amount: ₹" + String.format("%.2f", fine);
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            message,
            "Confirm Return",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String result = issueReturnService.returnBook(selectedIssueRecord.getIssueId());
            
            if (result.startsWith("SUCCESS")) {
                String successMsg = "Book returned successfully!\n\n" +
                    "Book: " + selectedIssueRecord.getBookTitle() + "\n" +
                    "Member: " + selectedIssueRecord.getMemberName();
                
                if (result.contains("FINE")) {
                    String fineStr = result.split(":")[1];
                    successMsg += "\n\nFine Amount: ₹" + fineStr + "\nPlease collect the fine!";
                }
                
                JOptionPane.showMessageDialog(this,
                    successMsg,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                
                clearFields();
                loadPendingReturns();
            } else {
                JOptionPane.showMessageDialog(this,
                    result,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadPendingReturns() {
        List<IssueRecord> records = issueReturnService.getPendingReturns();
        updateTable(records);
    }
    
    private void updateTable(List<IssueRecord> records) {
        tableModel.setRowCount(0);
        
        for (IssueRecord record : records) {
            // Calculate days overdue
            int daysOverdue = DateUtil.getDaysBetween(
                record.getDueDate(),
                DateUtil.getCurrentDate()
            );
            
            String overdueStr = daysOverdue > 0 ? 
                String.valueOf(daysOverdue) : "0";
            
            Object[] row = {
                record.getIssueId(),
                record.getBookTitle(),
                record.getMemberName(),
                DateUtil.formatDate(record.getIssueDate()),
                DateUtil.formatDate(record.getDueDate()),
                overdueStr
            };
            tableModel.addRow(row);
        }
    }
    
    private void fillFormFromTable() {
        int selectedRow = pendingTable.getSelectedRow();
        
        if (selectedRow >= 0) {
            String issueIdStr = tableModel.getValueAt(selectedRow, 0).toString();
            txtIssueId.setText(issueIdStr);
            searchIssueRecord();
        }
    }
    
    private void calculateAndDisplayFine() {
        if (selectedIssueRecord == null) {
            lblFineAmount.setText("₹ 0.00");
            return;
        }
        
        double fine = issueReturnService.calculateFine(
            selectedIssueRecord.getDueDate(),
            DateUtil.getCurrentDate()
        );
        
        lblFineAmount.setText("₹ " + String.format("%.2f", fine));
        
        if (fine > 0) {
            lblFineAmount.setForeground(new Color(231, 76, 60));
        } else {
            lblFineAmount.setForeground(new Color(46, 204, 113));
        }
    }
    
    private String getIssueDetailsText(IssueRecord record) {
        int daysOverdue = DateUtil.getDaysBetween(
            record.getDueDate(),
            DateUtil.getCurrentDate()
        );
        
        String status = daysOverdue > 0 ? "OVERDUE" : "ON TIME";
        
        return String.format(
            "Issue ID: %d\n" +
            "Book: %s\n" +
            "Member: %s\n" +
            "Issue Date: %s\n" +
            "Due Date: %s\n" +
            "Status: %s\n" +
            "Days Overdue: %d",
            record.getIssueId(),
            record.getBookTitle(),
            record.getMemberName(),
            DateUtil.formatDate(record.getIssueDate()),
            DateUtil.formatDate(record.getDueDate()),
            status,
            Math.max(0, daysOverdue)
        );
    }
    
    private void clearFields() {
        txtIssueId.setText("");
        txtIssueDetails.setText("No issue record selected");
        txtIssueDetails.setForeground(Color.BLACK);
        lblReturnDate.setText(DateUtil.formatDate(DateUtil.getCurrentDate()));
        lblFineAmount.setText("₹ 0.00");
        lblFineAmount.setForeground(new Color(231, 76, 60));
        selectedIssueRecord = null;
        pendingTable.clearSelection();
    }
}