package LibraryManagementSystem.src.com.library.ui;

import LibraryManagementSystem.src.com.library.model.Book;
import LibraryManagementSystem.src.com.library.model.User;
import LibraryManagementSystem.src.com.library.service.BookService;
import LibraryManagementSystem.src.com.library.util.Constants;
import LibraryManagementSystem.src.com.library.util.Validator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookManagementPanel extends JPanel {
    
    private User currentUser;
    private BookService bookService;
    
    // Form components
    private JTextField txtBookId;
    private JTextField txtISBN;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtPublisher;
    private JComboBox<String> cmbCategory;
    private JTextField txtQuantity;
    private JTextField txtAvailableQty;
    private JTextField txtRackNo;
    private JTextField txtSearch;
    
    // Buttons
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnRefresh;
    
    // Table
    private JTable bookTable;
    private DefaultTableModel tableModel;
    
    public BookManagementPanel(User user) {
        this.currentUser = user;
        this.bookService = new BookService();
        initComponents();
        loadBooksTable();
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
            "Book Details",
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
        
        // Book ID (hidden/readonly)
        gbc.gridx = 0; gbc.gridy = 0;
        fieldsPanel.add(new JLabel("Book ID:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtBookId = new JTextField(15);
        txtBookId.setEditable(false);
        txtBookId.setBackground(Color.LIGHT_GRAY);
        fieldsPanel.add(txtBookId, gbc);
        
        // ISBN
        gbc.gridx = 0; gbc.gridy = 1;
        fieldsPanel.add(new JLabel("ISBN: *"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtISBN = new JTextField(15);
        fieldsPanel.add(txtISBN, gbc);
        
        // Title
        gbc.gridx = 0; gbc.gridy = 2;
        fieldsPanel.add(new JLabel("Title: *"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        txtTitle = new JTextField(15);
        fieldsPanel.add(txtTitle, gbc);
        
        // Author
        gbc.gridx = 0; gbc.gridy = 3;
        fieldsPanel.add(new JLabel("Author: *"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        txtAuthor = new JTextField(15);
        fieldsPanel.add(txtAuthor, gbc);
        
        // Publisher
        gbc.gridx = 0; gbc.gridy = 4;
        fieldsPanel.add(new JLabel("Publisher:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        txtPublisher = new JTextField(15);
        fieldsPanel.add(txtPublisher, gbc);
        
        // Category
        gbc.gridx = 0; gbc.gridy = 5;
        fieldsPanel.add(new JLabel("Category:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 5;
        cmbCategory = new JComboBox<>(Constants.BOOK_CATEGORIES);
        fieldsPanel.add(cmbCategory, gbc);
        
        // Quantity
        gbc.gridx = 0; gbc.gridy = 6;
        fieldsPanel.add(new JLabel("Quantity: *"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 6;
        txtQuantity = new JTextField(15);
        fieldsPanel.add(txtQuantity, gbc);
        
        // Available Quantity
        gbc.gridx = 0; gbc.gridy = 7;
        fieldsPanel.add(new JLabel("Available:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 7;
        txtAvailableQty = new JTextField(15);
        txtAvailableQty.setEditable(false);
        txtAvailableQty.setBackground(Color.LIGHT_GRAY);
        fieldsPanel.add(txtAvailableQty, gbc);
        
        // Rack Number
        gbc.gridx = 0; gbc.gridy = 8;
        fieldsPanel.add(new JLabel("Rack No:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 8;
        txtRackNo = new JTextField(15);
        fieldsPanel.add(txtRackNo, gbc);
        
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
        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
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
        
        btnSearch.addActionListener(e -> searchBooks());
        btnRefresh.addActionListener(e -> loadBooksTable());
        
        // Table
        String[] columns = {"ID", "ISBN", "Title", "Author", "Publisher", "Category", "Qty", "Available", "Rack"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        bookTable = new JTable(tableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookTable.setRowHeight(25);
        bookTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        bookTable.getTableHeader().setBackground(new Color(41, 128, 185));
        bookTable.getTableHeader().setForeground(Color.WHITE);
        
        // Table selection listener
        bookTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromTable();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(bookTable);
        
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
    
    private void addBook() {
        try {
            Book book = new Book();
            book.setIsbn(txtISBN.getText().trim());
            book.setTitle(txtTitle.getText().trim());
            book.setAuthor(txtAuthor.getText().trim());
            book.setPublisher(txtPublisher.getText().trim());
            book.setCategory((String) cmbCategory.getSelectedItem());
            book.setRackNo(txtRackNo.getText().trim());
            
            // Validate and parse quantity
            if (!Validator.isPositiveInteger(txtQuantity.getText().trim())) {
                JOptionPane.showMessageDialog(this,
                    "Please enter valid quantity (positive number)!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int quantity = Integer.parseInt(txtQuantity.getText().trim());
            book.setQuantity(quantity);
            book.setAvailableQuantity(quantity);
            
            // Add book through service
            String result = bookService.addBook(book);
            
            if ("SUCCESS".equals(result)) {
                JOptionPane.showMessageDialog(this,
                    "Book added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadBooksTable();
            } else {
                JOptionPane.showMessageDialog(this,
                    result,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error adding book: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateBook() {
        try {
            if (txtBookId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please select a book to update!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Book book = new Book();
            book.setBookId(Integer.parseInt(txtBookId.getText().trim()));
            book.setIsbn(txtISBN.getText().trim());
            book.setTitle(txtTitle.getText().trim());
            book.setAuthor(txtAuthor.getText().trim());
            book.setPublisher(txtPublisher.getText().trim());
            book.setCategory((String) cmbCategory.getSelectedItem());
            book.setRackNo(txtRackNo.getText().trim());
            
            int quantity = Integer.parseInt(txtQuantity.getText().trim());
            int available = Integer.parseInt(txtAvailableQty.getText().trim());
            
            book.setQuantity(quantity);
            book.setAvailableQuantity(available);
            
            String result = bookService.updateBook(book);
            
            if ("SUCCESS".equals(result)) {
                JOptionPane.showMessageDialog(this,
                    "Book updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadBooksTable();
            } else {
                JOptionPane.showMessageDialog(this,
                    result,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error updating book: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteBook() {
        try {
            if (txtBookId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please select a book to delete!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this book?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                int bookId = Integer.parseInt(txtBookId.getText().trim());
                String result = bookService.deleteBook(bookId);
                
                if ("SUCCESS".equals(result)) {
                    JOptionPane.showMessageDialog(this,
                        "Book deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                    loadBooksTable();
                } else {
                    JOptionPane.showMessageDialog(this,
                        result,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error deleting book: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchBooks() {
        String keyword = txtSearch.getText().trim();
        
        if (keyword.isEmpty()) {
            loadBooksTable();
            return;
        }
        
        List<Book> books = bookService.searchBooks(keyword);
        updateTable(books);
    }
    
    private void loadBooksTable() {
        List<Book> books = bookService.getAllBooks();
        updateTable(books);
    }
    
    private void updateTable(List<Book> books) {
        tableModel.setRowCount(0);
        
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
    }
    
    private void fillFormFromTable() {
        int selectedRow = bookTable.getSelectedRow();
        
        if (selectedRow >= 0) {
            txtBookId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtISBN.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtTitle.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtAuthor.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtPublisher.setText(tableModel.getValueAt(selectedRow, 4).toString());
            cmbCategory.setSelectedItem(tableModel.getValueAt(selectedRow, 5));
            txtQuantity.setText(tableModel.getValueAt(selectedRow, 6).toString());
            txtAvailableQty.setText(tableModel.getValueAt(selectedRow, 7).toString());
            txtRackNo.setText(tableModel.getValueAt(selectedRow, 8).toString());
        }
    }
    
    private void clearFields() {
        txtBookId.setText("");
        txtISBN.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        cmbCategory.setSelectedIndex(0);
        txtQuantity.setText("");
        txtAvailableQty.setText("");
        txtRackNo.setText("");
        txtSearch.setText("");
        bookTable.clearSelection();
    }
}