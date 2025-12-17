import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class info extends JFrame{

    public info() {
        setTitle("Information");
        setLayout(null);
        setBounds(100,60, 500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    JLabel Name, Age, DOB, Phone, Adrs, state, country;
    JTextField nameField, ageField, dobField, phoneField, adrsField, stateField, countryField,
    hidenameField, hideageField, hidephoneField, hideadrsField, hidestateField, hidecountryField;
    JButton submit;

    public void components() {
        Name = new JLabel();
        Name.setText("Name:");
        Name.setBounds(30, 30, 80, 30);
        add(Name);

        nameField = new JTextField("");
        nameField.setBounds(100, 30, 150, 30);
        add(nameField);

        hidenameField = new JTextField("");
        hidenameField.setBounds(280, 30, 150, 30);
        add(hidenameField);
        hidenameField.setVisible(false);

        Age = new JLabel();
        Age.setText("Age:");
        Age.setBounds(30, 70, 80, 30);
        add(Age);

        ageField = new JTextField("");
        ageField.setBounds(100, 70, 150, 30);
        add(ageField);

        hideageField = new JTextField("");
        hideageField.setBounds(280, 70, 150, 30);
        add(hideageField);
        hideageField.setVisible(false);

        Phone = new JLabel("Phone:");
        Phone.setBounds(30, 110, 80, 30);
        add(Phone);

        phoneField = new JTextField("");
        phoneField.setBounds(100, 110, 150, 30);
        add(phoneField);

        hidephoneField = new JTextField("");
        hidephoneField.setBounds(280, 110, 150, 30);
        add(hidephoneField);
        hidephoneField.setVisible(false);

        Adrs = new JLabel("Address:");
        Adrs.setBounds(30, 150, 80, 30);
        add(Adrs);

        adrsField = new JTextField("");
        adrsField.setBounds(100, 150, 150, 30);
        add(adrsField);

        hideadrsField = new JTextField("");
        hideadrsField.setBounds(280, 150, 150, 30);
        add(hideadrsField);
        hideadrsField.setVisible(false);

        state = new JLabel("State:");
        state.setBounds(30, 190, 80, 30);
        add(state);

        stateField = new JTextField("");
        stateField.setBounds(100, 190, 150, 30);
        add(stateField);

        hidestateField = new JTextField("");
        hidestateField.setBounds(280, 190, 150, 30);
        add(hidestateField);
        hidestateField.setVisible(false);

        country = new JLabel("Country:");
        country.setBounds(30, 230, 80, 30);
        add(country);

        countryField = new JTextField("");
        countryField.setBounds(100, 230, 150, 30);
        add(countryField);

        hidecountryField = new JTextField("");
        hidecountryField.setBounds(280, 230, 150, 30);
        add(hidecountryField);
        hidecountryField.setVisible(false);

        submit = new JButton("Submit");
        submit.setBounds(150, 300, 100, 30);
        add(submit);

        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                hidenameField.setText(name);
                hidenameField.setVisible(true);
                nameField.setText("");

                String age = ageField.getText();
                hideageField.setText(age);
                hideageField.setVisible(true);
                ageField.setText("");

                String phone = phoneField.getText();
                hidephoneField.setText(phone);
                hidephoneField.setVisible(true);
                phoneField.setText("");

                String address = adrsField.getText();
                hideadrsField.setText(address);
                hideadrsField.setVisible(true);
                adrsField.setText("");

                String state = stateField.getText();
                hidestateField.setText(state);
                hidestateField.setVisible(true);
                stateField.setText("");

                String country = countryField.getText();
                hidecountryField.setText(country);
                hidecountryField.setVisible(true);
                countryField.setText("");
            }
        });
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                info inf = new info();
                inf.components();
            }
            
        });
        
    }
    
}
