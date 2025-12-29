package LoginForm;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Form extends JFrame {

    Form(){
        setBounds(100, 30, 400, 650);
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
    }

    JLabel Id, Name, Age, Gender, Address, Phone, Email, DOB, Massage;
    JTextArea MassageArea;
    JTextField IdField, NameField, AgeField, GenderField, AddressField, PhoneField, EmailField, DOBField;
    JButton Submit;
    public void components() {

        Id = new JLabel("Id:");
        Id.setBounds(50, 50, 80, 30);
        add(Id);

        IdField = new JTextField("");
        IdField.setBounds(150, 50, 150, 30);
        add(IdField);

        Name = new JLabel("Name:");
        Name.setBounds(50, 100, 80, 30);
        add(Name);

        NameField = new JTextField("");
        NameField.setBounds(150, 100, 150, 30);
        add(NameField);

        Age = new JLabel("Age:");
        Age.setBounds(50, 150, 80, 30);
        add(Age);

        AgeField = new JTextField("");
        AgeField.setBounds(150, 150, 150, 30);
        add(AgeField);

        Address = new JLabel("Address:");
        Address.setBounds(50, 200, 80, 30);
        add(Address);

        AddressField = new JTextField("");
        AddressField.setBounds(150, 200, 150, 30);
        add(AddressField);

        Phone = new JLabel("Phone:");
        Phone.setBounds(50, 250, 80, 30);
        add(Phone); 

        PhoneField = new JTextField("");
        PhoneField.setBounds(150, 250, 150, 30);
        add(PhoneField);

        DOB = new JLabel("DOB:");
        DOB.setBounds(50, 300, 80, 30);
        add(DOB);

        DOBField = new JTextField("");
        DOBField.setBounds(150, 300, 150, 30);
        add(DOBField);

        Email = new JLabel("Email:");
        Email.setBounds(50, 350, 80, 30);
        add(Email);

        EmailField = new JTextField("");
        EmailField.setBounds(150, 350, 150, 30);
        add(EmailField);

        Gender = new JLabel("Gender:");
        Gender.setBounds(50, 400, 80, 30);
        add(Gender);

        GenderField = new JTextField("");
        GenderField.setBounds(150, 400, 150, 30);
        add(GenderField);

        Massage = new JLabel("Massage");
        Massage.setBounds(50, 450, 250, 30);
        add(Massage);

        MassageArea = new JTextArea("");
        MassageArea.setBounds(150, 450, 150, 50);
        add(MassageArea);

        Submit = new JButton("Submit");
        Submit.setBounds(150, 550, 100, 30);
        add(Submit);

        Submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String id = IdField.getText();
                String name = NameField.getText();
                String age = AgeField.getText();
                String address = AddressField.getText();
                String phone = PhoneField.getText();
                String dob = DOBField.getText();
                String email = EmailField.getText();
                String gender = GenderField.getText();
                // String massage = MassageArea.getText();
                try {

                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:sqlserver://DESKTOP-42S24CL\\DIVYAANSH;databaseName=demo;user=Divyaansh;password=12345678;encrypt=false;");

                    System.out.println("Connection established successfully.");
                    PreparedStatement ps = con.prepareStatement("insert into Employee(id, name, age, address, phone, dob, email, gender) values(?, ?, ?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, Integer.parseInt(id));
                    ps.setString(2, name);
                    ps.setInt(3, Integer.parseInt(age));
                    ps.setString(4, address);
                    ps.setString(5, phone);
                    ps.setDate(6, java.sql.Date.valueOf(dob));
                    ps.setString(7, email);
                    ps.setString(8, gender);
                    // ps.setString(9, massage);
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        System.out.println("Record inserted successfully.");
                    } else {
                        System.out.println("Failed to insert record.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }

        });
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Form f = new Form();
                f.components();
            }
        });
        
    }
    
}
