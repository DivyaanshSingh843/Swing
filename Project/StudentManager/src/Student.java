import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;



public class Student extends JFrame {

    private JTextField txtRollNo, txtName, txtCourse, txtPhone, txtEmail, txtSearch;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnSearch, btnRefresh;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    Student(){
        setTitle("Student Record Manager");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 

        createComponents();

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
        
        btnClear = new JButton("Clear");
        btnClear.setBounds(290, 190, 100, 35);
        btnClear.addActionListener(e -> clearFields());
        add(btnClear);
        
        btnRefresh = new JButton("All Record");
        btnRefresh.setBounds(410, 190, 100, 35);
        btnRefresh.addActionListener(e -> loadTableData());
        add(btnRefresh);
        
        JLabel lblSearch = new JLabel("Roll No [Search/Delete] :");
        lblSearch.setBounds(50, 245, 140, 25);
        add(lblSearch);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(200, 245, 200, 25);
        add(txtSearch);
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(410, 245, 100, 25);
        btnSearch.addActionListener(e -> Search());
        add(btnSearch);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(530, 245, 100, 25);
        btnDelete.addActionListener(e -> deleteStudent());
        add(btnDelete);
        
        String[] columns = {"Roll No", "Name", "Course", "Phone", "Email"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table = new JTable(model);
        
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 300, 700, 250);
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
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        String rollNo = txtSearch.getText().trim();
        
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
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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
    

    private void loadTableData() {
        model.setRowCount(0);
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                String rollNo = rs.getString("RollNo");
                String name = rs.getString("Name");
                String course = rs.getString("Course");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");

                Vector<String> row = new Vector<>();
                row.add(rollNo);
                row.add(name);
                row.add(course);
                row.add(phone);
                row.add(email);
                
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

     private void Search() {
        model.setRowCount(0);

        String roll = txtSearch.getText().trim();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Students where RollNo = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, roll);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String rollNo = rs.getString("RollNo");
                String name = rs.getString("Name");
                String course = rs.getString("Course");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                
                Vector<String> row = new Vector<>();
                row.add(rollNo);
                row.add(name);
                row.add(course);
                row.add(phone);
                row.add(email);
                
                model.addRow(row);
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            
            public void run(){
                Student S = new Student();
            }
        });
    
    
}

}