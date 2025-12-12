import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame {
    
    public App() {
        setTitle("Division Application");
        setLayout(null);
        setBounds(200,200, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    JLabel name, phonenumber;
    JTextField namField;
    public void components() {
        name = new JLabel();
        name.setText("user name :");
        name.setBounds(50, 50, 100, 30);
        add(name);
        phonenumber = new JLabel();
        phonenumber.setText("Phone Number:");
        phonenumber.setBounds(50, 100, 100, 30);
        add(phonenumber);
        namField = new JTextField();
        namField.setBounds(150, 50, 200, 30);   
        add(namField);
        
    }
    public static void main(String[] args) {
        App app = new App();
        app.components();
    }
}