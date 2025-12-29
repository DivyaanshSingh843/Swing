package LibraryManagementSystem.src.com.library.ui;


import LibraryManagementSystem.src.com.library.model.User;
import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    
    private User currentUser;
    private JTabbedPane tabbedPane;
    
    public DashboardFrame(User user) {
        this.currentUser = user;
        initComponents();
    }
    
    private void initComponents() {
        // Frame settings
        setTitle("Library Management System - Dashboard");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        
        // Tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add tabs
        tabbedPane.addTab("Books Management", new BookManagementPanel(currentUser));
        tabbedPane.addTab("Members Management", new MemberManagementPanel(currentUser));
        tabbedPane.addTab("Issue Book", new IssueBookPanel(currentUser));
        tabbedPane.addTab("Return Book", new ReturnBookPanel(currentUser));
        tabbedPane.addTab("Reports", new ReportsPanel(currentUser));
        
        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        
        // Add main panel to frame
        add(mainPanel);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(1200, 60));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Title
        JLabel lblTitle = new JLabel("Library Management System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        
        // User info panel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(new Color(41, 128, 185));
        
        JLabel lblUser = new JLabel("Welcome, " + currentUser.getFullName() + " (" + currentUser.getRole() + ")");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUser.setForeground(Color.WHITE);
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 12));
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(e -> logout());
        
        userPanel.add(lblUser);
        userPanel.add(Box.createHorizontalStrut(20));
        userPanel.add(btnLogout);
        
        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(userPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            });
        }
    }
}