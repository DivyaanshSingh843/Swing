import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class connection extends JFrame {

    connection(){
        setBounds(100, 100, 600, 400);
        setTitle("Databse Connection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

    }

    JLabel id, name;
    JTextField idField, nameField;
    JButton submit;
    public void components() {
        id = new JLabel("ID:");
        id.setBounds(50, 50, 80, 30);
        add(id);

        idField = new JTextField("");
        idField.setBounds(150, 50, 150, 30);
        add(idField);

        name = new JLabel("Name:");
        name.setBounds(50, 100, 80, 30);
        add(name);

        nameField = new JTextField("");
        nameField.setBounds(150, 100, 150, 30);
        add(nameField);

        submit = new JButton("Submit");
        submit.setBounds(150, 150, 100, 30);
        add(submit);

        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();
                try {

                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:sqlserver://DESKTOP-42S24CL\\DIVYAANSH;databaseName=demo;user=Divyaansh;password=12345678;encrypt=false;");

                    System.out.println("Connection established successfully.");
                    PreparedStatement ps = con.prepareStatement("insert into Employee(id, name) values(?, ?)");
                    ps.setInt(1, Integer.parseInt(id));
                    ps.setString(2, name);
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
                connection conn = new connection();
                conn.components();
            }
            
        }); 
    }
}
