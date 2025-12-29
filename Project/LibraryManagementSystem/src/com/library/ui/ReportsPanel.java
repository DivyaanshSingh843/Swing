package LibraryManagementSystem.src.com.library.ui;


import LibraryManagementSystem.src.com.library.model.Book;
import LibraryManagementSystem.src.com.library.model.IssueRecord;
import LibraryManagementSystem.src.com.library.model.Member;
import LibraryManagementSystem.src.com.library.model.User;
import LibraryManagementSystem.src.com.library.service.BookService;
import LibraryManagementSystem.src.com.library.service.IssueReturnService;
import LibraryManagementSystem.src.com.library.service.MemberService;
import LibraryManagementSystem.src.com.library.util.Constants;
import LibraryManagementSystem.src.com.library.util.DateUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ReportsPanel extends JPanel {
    
    private User currentUser;
    private BookService bookService;
    private MemberService memberService;
    private IssueReturnService issueReturnService;
    
    // Components
    private JComboBox<String> cmbReportType;
    private JButton btnGenerate;
    private JButton btnExport;
    
    private JTable reportTable;
    private DefaultTableModel tableModel;
    
    private JLabel lblTotalRecords;
    private JTextArea txtSummary;
    
    public ReportsPanel(User user) {
        this.currentUser = user;
        this.bookService = new BookService();
        this.memberService = new MemberService();
        this.issueReturnService = new IssueReturnService();
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel - Report selection
        JPanel topPanel = createTopPanel();
        
        // Center panel - Table
        JPanel centerPanel = createCenterPanel();
        
        // Bottom panel - Summary
        JPanel bottomPanel = createBottomPanel();
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panel.setBackground(new Color(52, 152, 219));
        panel.setPreferredSize(new Dimension(1100, 60));
        
        JLabel lblTitle = new JLabel("Select Report Type:");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setForeground(Color.WHITE);
        panel.add(lblTitle);
        
        String[] reportTypes = {
            "All Books",
            "Available Books",
            "Issued Books",
            "All Members",
            "Active Members",
            "All Issue Records",
            "Currently Issued Books",
            "Overdue Books",
            "Returned Books",
            "Books by Category"
        };
        
        cmbReportType = new JComboBox<>(reportTypes);
        cmbReportType.setFont(new Font("Arial", Font.PLAIN, 13));
        cmbReportType.setPreferredSize(new Dimension(200, 35));
        panel.add(cmbReportType);
        
        btnGenerate = createStyledButton("Generate Report", new Color(46, 204, 113));
        btnGenerate.addActionListener(e -> generateReport());
        panel.add(btnGenerate);
        
        btnExport = createStyledButton("Export to CSV", new Color(241, 196, 15));
        btnExport.addActionListener(e -> exportToCSV());
        panel.add(btnExport);
        
        return panel;
    }
    
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Table
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        reportTable = new JTable(tableModel);
        reportTable.setRowHeight(25);
        reportTable.setFont(new Font("Arial", Font.PLAIN, 12));
        reportTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        reportTable.getTableHeader().setBackground(new Color(52, 152, 219));
        reportTable.getTableHeader().setForeground(Color.WHITE);
        reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        JScrollPane scrollPane = new JScrollPane(reportTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.setPreferredSize(new Dimension(1100, 120));
        
        // Left - Record count
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblTotalRecords = new JLabel("Total Records: 0");
        lblTotalRecords.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotalRecords.setForeground(new Color(52, 152, 219));
        leftPanel.add(lblTotalRecords);
        
        // Right - Summary
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
        
        txtSummary = new JTextArea(4, 50);
        txtSummary.setEditable(false);
        txtSummary.setFont(new Font("Monospaced", Font.PLAIN, 11));
        txtSummary.setBackground(new Color(245, 245, 245));
        txtSummary.setText("Generate a report to see summary");
        
        JScrollPane summaryScroll = new JScrollPane(txtSummary);
        rightPanel.add(summaryScroll, BorderLayout.CENTER);
        
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 35));
        return button;
    }
    
    private void generateReport() {
        String reportType = (String) cmbReportType.getSelectedItem();
        
        // Clear previous data
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        
        switch (reportType) {
            case "All Books":
                generateAllBooksReport();
                break;
            case "Available Books":
                generateAvailableBooksReport();
                break;
            case "Issued Books":
                generateIssuedBooksReport();
                break;
            case "All Members":
                generateAllMembersReport();
                break;
            case "Active Members":
                generateActiveMembersReport();
                break;
            case "All Issue Records":
                generateAllIssueRecordsReport();
                break;
            case "Currently Issued Books":
                generateCurrentlyIssuedReport();
                break;
            case "Overdue Books":
                generateOverdueBooksReport();
                break;
            case "Returned Books":
                generateReturnedBooksReport();
                break;
            case "Books by Category":
                generateBooksByCategoryReport();
                break;
        }
    }
    
    private void generateAllBooksReport() {
        String[] columns = {"Book ID", "ISBN", "Title", "Author", "Publisher", "Category", "Total Qty", "Available", "Rack"};
        tableModel.setColumnIdentifiers(columns);
        
        List<Book> books = bookService.getAllBooks();
        
        for (Book book : books) {
            Object[] row = {
                book.getBookId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getCategory(),
                book.getQuantity(),
                book.getAvailableQuantity(),
                book.getRackNo()
            };
            tableModel.addRow(row);
        }
        
        lblTotalRecords.setText("Total Records: " + books.size());
        
        int totalBooks = books.stream().mapToInt(Book::getQuantity).sum();
        int availableBooks = books.stream().mapToInt(Book::getAvailableQuantity).sum();
        int issuedBooks = totalBooks - availableBooks;
        
        txtSummary.setText(String.format(
            "Total Book Titles: %d\n" +
            "Total Book Copies: %d\n" +
            "Available Copies: %d\n" +
            "Issued Copies: %d",
            books.size(), totalBooks, availableBooks, issuedBooks
        ));
    }
    
    private void generateAvailableBooksReport() {
        String[] columns = {"Book ID", "Title", "Author", "Category", "Available Qty", "Rack"};
        tableModel.setColumnIdentifiers(columns);
        
        List<Book> books = bookService.getAllBooks().stream()
            .filter(b -> b.getAvailableQuantity() > 0)
            .collect(Collectors.toList());
        
        for (Book book : books) {
            Object[] row = {
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getAvailableQuantity(),
                book.getRackNo()
            };
            tableModel.addRow(row);
        }
        
        lblTotalRecords.setText("Total Records: " + books.size());
        
        int totalAvailable = books.stream().mapToInt(Book::getAvailableQuantity).sum();
        
        txtSummary.setText(String.format(
            "Available Book Titles: %d\n" +
            "Total Available Copies: %d\n" +
            "Ready to be issued",
            books.size(), totalAvailable
        ));
    }
    
    private void generateIssuedBooksReport() {
        String[] columns = {"Book ID", "Title", "Author", "Total Qty", "Available", "Issued"};
        tableModel.setColumnIdentifiers(columns);
        
        List<Book> books = bookService.getAllBooks().stream()
            .filter(b -> b.getAvailableQuantity() < b.getQuantity())
            .collect(Collectors.toList());
        
        for (Book book : books) {
            int issued = book.getQuantity() - book.getAvailableQuantity();
            Object[] row = {
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getQuantity(),
                book.getAvailableQuantity(),
                issued
            };
            tableModel.addRow(row);
        }
        
        lblTotalRecords.setText("Total Records: " + books.size());
        
        int totalIssued = books.stream()
            .mapToInt(b -> b.getQuantity() - b.getAvailableQuantity())
            .sum();
        
        txtSummary.setText(String.format(
            "Books with Issued Copies: %d\n" +
            "Total Issued Copies: %d",
            books.size(), totalIssued
        ));
    }
    
    private void generateAllMembersReport() {
        String[] columns = {"Member ID", "Member No", "Full Name", "Email", "Phone", "Status", "Reg. Date"};
        tableModel.setColumnIdentifiers(columns);
        
        List<Member> members = memberService.getAllMembers();
        
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
        
        lblTotalRecords.setText("Total Records: " + members.size());
        
        long activeMembers = members.stream()
            .filter(m -> Constants.STATUS_ACTIVE.equals(m.getStatus()))
            .count();
        
        txtSummary.setText(String.format(
            "Total Members: %d\n" +
            "Active Members: %d\n" +
            "Inactive Members: %d",
            members.size(), activeMembers, members.size() - activeMembers
        ));
    }
    
    private void generateActiveMembersReport() {
        String[] columns = {"Member ID", "Member No", "Full Name", "Email", "Phone", "Reg. Date"};
        tableModel.setColumnIdentifiers(columns);
        
        List<Member> members = memberService.getAllMembers().stream()
            .filter(m -> Constants.STATUS_ACTIVE.equals(m.getStatus()))
            .collect(Collectors.toList());
        
        for (Member member : members) {
            Object[] row = {
                member.getMemberId(),
                member.getMemberNo(),
                member.getFullName(),
                member.getEmail(),
                member.getPhone(),
                member.getRegistrationDate() != null ? 
                    DateUtil.formatDate(member.getRegistrationDate()) : ""
            };
            tableModel.addRow(row);
        }
        
        lblTotalRecords.setText("Total Records: " + members.size());
        txtSummary.setText("Active members who can issue books");
    }
    
    private void generateAllIssueRecordsReport() {
        String[] columns = {"Issue ID", "Book Title", "Member Name", "Issue Date", "Due Date", "Return Date", "Fine", "Status"};
        tableModel.setColumnIdentifiers(columns);
        
        List<IssueRecord> records = issueReturnService.getAllIssuedRecords();
        
        for (IssueRecord record : records) {
            Object[] row = {
                record.getIssueId(),
                record.getBookTitle(),
                record.getMemberName(),
                DateUtil.formatDate(record.getIssueDate()),
                DateUtil.formatDate(record.getDueDate()),
                record.getReturnDate() != null ? DateUtil.formatDate(record.getReturnDate()) : "Not Returned",
                "₹" + String.format("%.2f", record.getFineAmount()),
                record.getStatus()
            };
            tableModel.addRow(row);
        }
        
        lblTotalRecords.setText("Total Records: " + records.size());
        
        long issued = records.stream().filter(r -> Constants.STATUS_ISSUED.equals(r.getStatus())).count();
        long returned = records.stream().filter(r -> Constants.STATUS_RETURNED.equals(r.getStatus())).count();
        double totalFine = records.stream().mapToDouble(IssueRecord::getFineAmount).sum();
        
        txtSummary.setText(String.format(
            "Total Issue Records: %d\n" +
            "Currently Issued: %d\n" +
            "Returned: %d\n" +
            "Total Fine Collected: ₹%.2f",
            records.size(), issued, returned, totalFine
        ));
    }
    
    private void generateCurrentlyIssuedReport() {
        String[] columns = {"Issue ID", "Book Title", "Member Name", "Issue Date", "Due Date", "Days Remaining"};
        tableModel.setColumnIdentifiers(columns);
        
        List<IssueRecord> records = issueReturnService.getPendingReturns();
        
        for (IssueRecord record : records) {
            int daysRemaining = DateUtil.getDaysBetween(DateUtil.getCurrentDate(), record.getDueDate());
            
            Object[] row = {
                record.getIssueId(),
                record.getBookTitle(),
                record.getMemberName(),
                DateUtil.formatDate(record.getIssueDate()),
                DateUtil.formatDate(record.getDueDate()),
                daysRemaining
            };
            tableModel.addRow(row);
        }
        
        lblTotalRecords.setText("Total Records: " + records.size());
        txtSummary.setText("Books currently issued to members");
    }
    
    private void generateOverdueBooksReport() {
        String[] columns = {"Issue ID", "Book Title", "Member Name", "Issue Date", "Due Date", "Days Overdue", "Fine"};
        tableModel.setColumnIdentifiers(columns);
        
        List<IssueRecord> records = issueReturnService.getOverdueBooks();
        
        double totalFine = 0;
        
        for (IssueRecord record : records) {
            int daysOverdue = DateUtil.getDaysBetween(record.getDueDate(), DateUtil.getCurrentDate());
            double fine = daysOverdue * Constants.FINE_PER_DAY;
            totalFine += fine;
            
            Object[] row = {
                record.getIssueId(),
                record.getBookTitle(),
                record.getMemberName(),
                DateUtil.formatDate(record.getIssueDate()),
                DateUtil.formatDate(record.getDueDate()),
                daysOverdue,
                "₹" + String.format("%.2f", fine)
            };
            tableModel.addRow(row);
        }
        
        lblTotalRecords.setText("Total Records: " + records.size());
        
        txtSummary.setText(String.format(
            "Overdue Books: %d\n" +
            "Total Expected Fine: ₹%.2f\n" +
            "Action required: Contact members!",
            records.size(), totalFine
        ));
    }
    
    private void generateReturnedBooksReport() {
        String[] columns = {"Issue ID", "Book Title", "Member Name", "Issue Date", "Return Date", "Fine Paid", "Days Taken"};
        tableModel.setColumnIdentifiers(columns);
        
        List<IssueRecord> records = issueReturnService.getAllIssuedRecords().stream()
            .filter(r -> Constants.STATUS_RETURNED.equals(r.getStatus()))
            .collect(Collectors.toList());
        
        double totalFine = 0;
        
        for (IssueRecord record : records) {
            int daysTaken = DateUtil.getDaysBetween(record.getIssueDate(), record.getReturnDate());
            totalFine += record.getFineAmount();
            
            Object[] row = {
                record.getIssueId(),
                record.getBookTitle(),
                record.getMemberName(),
                DateUtil.formatDate(record.getIssueDate()),
                DateUtil.formatDate(record.getReturnDate()),
                "₹" + String.format("%.2f", record.getFineAmount()),
                daysTaken
            };
            tableModel.addRow(row);
        }
        
        lblTotalRecords.setText("Total Records: " + records.size());
        
        txtSummary.setText(String.format(
            "Returned Books: %d\n" +
            "Total Fine Collected: ₹%.2f",
            records.size(), totalFine
        ));
    }
    
    private void generateBooksByCategoryReport() {
        String[] columns = {"Category", "Total Books", "Total Copies", "Available Copies", "Issued Copies"};
        tableModel.setColumnIdentifiers(columns);
        
        List<Book> allBooks = bookService.getAllBooks();
        
        for (String category : Constants.BOOK_CATEGORIES) {
            List<Book> categoryBooks = allBooks.stream()
                .filter(b -> category.equals(b.getCategory()))
                .collect(Collectors.toList());
            
            if (!categoryBooks.isEmpty()) {
                int totalCopies = categoryBooks.stream().mapToInt(Book::getQuantity).sum();
                int available = categoryBooks.stream().mapToInt(Book::getAvailableQuantity).sum();
                int issued = totalCopies - available;
                
                Object[] row = {
                    category,
                    categoryBooks.size(),
                    totalCopies,
                    available,
                    issued
                };
                tableModel.addRow(row);
            }
        }
        
        lblTotalRecords.setText("Total Categories: " + tableModel.getRowCount());
        
        int totalTitles = allBooks.size();
        int totalCopies = allBooks.stream().mapToInt(Book::getQuantity).sum();
        
        txtSummary.setText(String.format(
            "Total Book Titles: %d\n" +
            "Total Book Copies: %d\n" +
            "Categories: %d",
            totalTitles, totalCopies, tableModel.getRowCount()
        ));
    }
    
    private void exportToCSV() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "Please generate a report first!",
                "No Data",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Report as CSV");
        fileChooser.setSelectedFile(new java.io.File("report_" + 
            System.currentTimeMillis() + ".csv"));
        
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File file = fileChooser.getSelectedFile();
                java.io.FileWriter writer = new java.io.FileWriter(file);
                
                // Write headers
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    writer.write(tableModel.getColumnName(i));
                    if (i < tableModel.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
                
                // Write data
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        Object value = tableModel.getValueAt(i, j);
                        writer.write(value != null ? value.toString() : "");
                        if (j < tableModel.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.write("\n");
                }
                
                writer.close();
                
                JOptionPane.showMessageDialog(this,
                    "Report exported successfully!\n" + file.getAbsolutePath(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error exporting report: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}