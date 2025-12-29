package LibraryManagementSystem.src.com.library.ui;

import LibraryManagementSystem.src.com.library.dao.UserDAO;
import LibraryManagementSystem.src.com.library.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnClear;
    private UserDAO userDAO;
    
    public LoginFrame() {
        userDAO = new UserDAO();
        initComponents();
    }
    
    private void initComponents() {
        // Frame settings
        setTitle("Library Management System - Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185));
        titlePanel.setPreferredSize(new Dimension(500, 100));
        
        JLabel lblTitle = new JLabel("Library Management System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        
        // Login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null); // Using absolute positioning for better control
        loginPanel.setBackground(new Color(240, 240, 240));
        loginPanel.setPreferredSize(new Dimension(500, 300));
        
        // Username label
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 15));
        lblUsername.setBounds(80, 40, 100, 30);
        loginPanel.add(lblUsername);
        
        // Username field
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBounds(200, 40, 220, 35);
        loginPanel.add(txtUsername);
        
        // Password label
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 15));
        lblPassword.setBounds(80, 100, 100, 30);
        loginPanel.add(lblPassword);
        
        // Password field
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBounds(200, 100, 220, 35);
        loginPanel.add(txtPassword);
        
        // Login button
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBounds(150, 170, 100, 40);
        btnLogin.setBackground(new Color(46, 204, 113));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginPanel.add(btnLogin);
        
        // Clear button
        btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.setBounds(270, 170, 100, 40);
        btnClear.setBackground(new Color(231, 76, 60));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginPanel.add(btnClear);
        
        // Info label
        JLabel lblInfo = new JLabel("Default: admin / admin123");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        lblInfo.setForeground(Color.GRAY);
        lblInfo.setBounds(170, 230, 200, 20);
        loginPanel.add(lblInfo);
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        
        // Add main panel to frame
        add(mainPanel);
        
        // Add action listeners
        btnLogin.addActionListener(e -> loginAction());
        btnClear.addActionListener(e -> clearFields());
        
        // Enter key listener for password field
        txtPassword.addActionListener(e -> loginAction());
        
        // Focus on username field
        txtUsername.requestFocus();
    }
    
    private void loginAction() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        // Validate input
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter username!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return;
        }
        
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter password!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            txtPassword.requestFocus();
            return;
        }
        
        // Authenticate user
        User user = userDAO.authenticateUser(username, password);
        
        if (user != null) {
            JOptionPane.showMessageDialog(this, 
                "Login successful!\nWelcome " + user.getFullName(), 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Open dashboard
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                DashboardFrame dashboard = new DashboardFrame(user);
                dashboard.setVisible(true);
            });
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid username or password!", 
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
            clearFields();
            txtUsername.requestFocus();
        }
    }
    
    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus();
    }
    
    public static void main(String[] args) {
        // Set Nimbus Look and Feel (optional but looks better)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, use default
        }
        
        // Create and show login frame
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}