import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class StudentGUI extends JFrame {
    
    private JTextField txtRollNo, txtName, txtCourse, txtPhone, txtEmail, txtSearch;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnSearch, btnRefresh;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    
    public StudentGUI() {
        setTitle("Student Record Manager");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);  // Null layout
        setResizable(false);
        
        createComponents();
        loadTableData();
        
        setVisible(true);
    }
    
    private void createComponents() {
        JLabel lblTitle = new JLabel("Student Record Management System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(250, 10, 400, 30);
        add(lblTitle);
        
        JLabel lblRollNo = new JLabel("Roll No:");
        lblRollNo.setBounds(50, 60, 100, 25);
        add(lblRollNo);
        
        txtRollNo = new JTextField();
        txtRollNo.setBounds(150, 60, 200, 25);
        add(txtRollNo);
        
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 100, 100, 25);
        add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(150, 100, 200, 25);
        add(txtName);
        
        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setBounds(50, 140, 100, 25);
        add(lblCourse);
        
        txtCourse = new JTextField();
        txtCourse.setBounds(150, 140, 200, 25);
        add(txtCourse);
        
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(450, 60, 100, 25);
        add(lblPhone);
        
        txtPhone = new JTextField();
        txtPhone.setBounds(550, 60, 200, 25);
        add(txtPhone);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(450, 100, 100, 25);
        add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(550, 100, 200, 25);
        add(txtEmail);
        
        btnAdd = new JButton("Add");
        btnAdd.setBounds(50, 190, 100, 35);
        btnAdd.addActionListener(e -> addStudent());
        add(btnAdd);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(170, 190, 100, 35);
        btnUpdate.addActionListener(e -> updateStudent());
        add(btnUpdate);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(290, 190, 100, 35);
        btnDelete.addActionListener(e -> deleteStudent());
        add(btnDelete);
        
        btnClear = new JButton("Clear");
        btnClear.setBounds(410, 190, 100, 35);
        btnClear.addActionListener(e -> clearFields());
        add(btnClear);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(530, 190, 100, 35);
        btnRefresh.addActionListener(e -> loadTableData());
        add(btnRefresh);
        
        JLabel lblSearch = new JLabel("Search Roll No:");
        lblSearch.setBounds(50, 245, 120, 25);
        add(lblSearch);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(170, 245, 200, 25);
        add(txtSearch);
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(390, 245, 100, 25);
        btnSearch.addActionListener(e -> searchStudent());
        add(btnSearch);
        
        String[] columns = {"Roll No", "Name", "Course", "Phone", "Email"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        // table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // table.setRowHeight(25);
        // table.getTableHeader().setReorderingAllowed(false);
        
        
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 290, 800, 300);
        add(scrollPane);
    }
    
    private void addStudent() {
        String rollNo = txtRollNo.getText().trim();
        String name = txtName.getText().trim();
        String course = txtCourse.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        
        if (rollNo.isEmpty() || name.isEmpty() || course.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Students (RollNo, Name, Course, Phone, Email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, rollNo);
            pst.setString(2, name);
            pst.setString(3, course);
            pst.setString(4, phone);
            pst.setString(5, email);
            
            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                clearFields();
                loadTableData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateStudent() {
        String rollNo = txtRollNo.getText().trim();
        String name = txtName.getText().trim();
        String course = txtCourse.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        
        if (rollNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Roll No to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE Students SET Name=?, Course=?, Phone=?, Email=? WHERE RollNo=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, course);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, rollNo);
            
            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                clearFields();
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteStudent() {
        String rollNo = txtRollNo.getText().trim();
        
        if (rollNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Roll No to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Students WHERE RollNo=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, rollNo);
            
            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                clearFields();
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchStudent() {
        String rollNo = txtSearch.getText().trim();
        
        if (rollNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Roll No to search!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Students WHERE RollNo=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, rollNo);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txtRollNo.setText(rs.getString("RollNo"));
                txtName.setText(rs.getString("Name"));
                txtCourse.setText(rs.getString("Course"));
                txtPhone.setText(rs.getString("Phone"));
                txtEmail.setText(rs.getString("Email"));
                txtSearch.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadTableData() {
        model.setRowCount(0);
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Students ORDER BY RollNo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                String rollNo = rs.getString("RollNo");
                String name = rs.getString("Name");
                String course = rs.getString("Course");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                
                model.addRow(new Object[]{rollNo, name, course, phone, email});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        txtRollNo.setText("");
        txtName.setText("");
        txtCourse.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtSearch.setText("");
        table.clearSelection();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGUI());
    }
}