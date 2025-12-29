import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Professional Login Form - Complete Swing Project
 * Demonstrates all basic components + event handling + GridBagLayout
 */
public class login extends JFrame {
    // Components
    private JLabel titleLabel, subtitleLabel, statusLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox rememberMeCheck, showPasswordCheck;
    private JButton loginBtn, cancelBtn, registerBtn;
    
    // Layout
    private JPanel mainPanel;
    private GridBagConstraints gbc;
    
    // State
    private boolean passwordVisible = false;

    public login() {
        initializeWindow();
        createComponents();
        createLayout();
        addListeners();
        setVisible(true);
    }

    /** 1. Initialize main window */
    private void initializeWindow() {
        setTitle("User Login System");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center on screen
        
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(248, 249, 250));  // Light gray
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);
    }

    /** 2. Create all components with styling */
    private void createComponents() {
        // Title
        titleLabel = new JLabel("USER LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 123, 255));  // Blue
        
        // Subtitle
        subtitleLabel = new JLabel("Please enter your credentials", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        
        // Username
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        
        // Password
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        
        // Checkboxes
        rememberMeCheck = new JCheckBox("Remember Me");
        showPasswordCheck = new JCheckBox("Show Password");
        
        // Buttons with custom styling
        loginBtn = new JButton("LOGIN");
        styleButton(loginBtn, Color.decode("#28A745"), Color.WHITE);  // Green
        
        cancelBtn = new JButton("CANCEL");
        styleButton(cancelBtn, Color.RED, Color.WHITE);               // Red
        
        registerBtn = new JButton("REGISTER");
        styleButton(registerBtn, Color.decode("#007BFF"), Color.WHITE); // Blue
        
        // Status
        statusLabel = new JLabel("Ready to login...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setForeground(Color.GRAY);
    }

    /** Style buttons consistently */
    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    /** 3. Professional GridBagLayout */
    private void createLayout() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Title (row 0, span 3 columns, centered)
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        mainPanel.add(titleLabel, gbc);
        
        // Subtitle (row 1)
        gbc.gridy = 1; gbc.gridwidth = 3;
        mainPanel.add(subtitleLabel, gbc);
        
        // Username label + field (row 2)
        gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(usernameField, gbc);
        
        // Password label + field (row 3)
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 2; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbc);
        
        // Checkboxes (row 4)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(rememberMeCheck, gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        mainPanel.add(showPasswordCheck, gbc);
        
        // Buttons panel (row 5, span 3 columns)
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3; gbc.weightx = 1.0;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(loginBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(registerBtn);
        mainPanel.add(buttonPanel, gbc);
        
        // Status (row 6, span 3 columns)
        gbc.gridy = 6; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(statusLabel, gbc);
    }

    /** 4. Add all event listeners */
    private void addListeners() {
        // LOGIN button
        loginBtn.addActionListener(e -> validateLogin());
        
        // CANCEL button
        cancelBtn.addActionListener(e -> clearForm());
        
        // REGISTER button
        registerBtn.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Registration page coming soon!", 
                "Register", JOptionPane.INFORMATION_MESSAGE));
        
        // Show Password checkbox
        showPasswordCheck.addActionListener(e -> togglePasswordVisibility());
        
        // Enter key in password field triggers login
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginBtn.doClick();
                }
            }
        });
    }

    /** 5. Validate login credentials */
    private void validateLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        boolean rememberMe = rememberMeCheck.isSelected();
        
        // Clear previous status
        statusLabel.setText("");
        statusLabel.setForeground(Color.GRAY);
        
        // Validation
        if (username.isEmpty() && password.isEmpty()) {
            showError("Please fill all fields!");
            return;
        }
        if (username.isEmpty()) {
            showError("Username is required!");
            usernameField.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            showError("Password is required!");
            passwordField.requestFocus();
            return;
        }
        
        // Check credentials
        if ("admin".equals(username) && "admin123".equals(password)) {
            showSuccess("Login Successful! Welcome " + username);
            System.out.println("Remember me: " + rememberMe);
            loginBtn.setEnabled(false);  // Disable after success
        } else {
            showError("Invalid username or password!");
            passwordField.requestFocus();
            passwordField.selectAll();
        }
    }

    /** 6. Toggle password visibility */
    private void togglePasswordVisibility() {
        if (showPasswordCheck.isSelected()) {
            // Convert to plain text field
            passwordVisible = true;
            String text = new String(passwordField.getPassword());
            passwordField.setEchoChar((char) 0);  // Show characters
        } else {
            passwordVisible = false;
            passwordField.setEchoChar('*');  // Hide with *
        }
    }

    /** 7. Clear form */
    private void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
        rememberMeCheck.setSelected(false);
        showPasswordCheck.setSelected(false);
        statusLabel.setText("Form cleared");
        statusLabel.setForeground(Color.GRAY);
        loginBtn.setEnabled(true);  // Re-enable login
        
        JOptionPane.showMessageDialog(this, "Form cleared successfully!", 
            "Cleared", JOptionPane.INFORMATION_MESSAGE);
        
        usernameField.requestFocus();
    }

    /** 8. Show error message */
    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(Color.RED);
        JOptionPane.showMessageDialog(this, message, "Login Error", 
            JOptionPane.ERROR_MESSAGE);
    }

    /** 9. Show success message */
    private void showSuccess(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(new Color(40, 167, 69));  // Green
        JOptionPane.showMessageDialog(this, message, "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new login();
        });
    }
}
