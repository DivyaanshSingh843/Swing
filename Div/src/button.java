import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class button extends JFrame{

    public button() {
        setTitle("Button Window");
        setLayout(null);
        setBounds(100,100, 400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    JRadioButton male, female, other;
    JCheckBox javaBox, pythonBox, cppBox;
    JLabel gender, programming;
    public void components() {
        gender = new JLabel("Gender:");
        gender.setBounds(50, 50, 100, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(150, 50, 100, 30);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(150, 90, 100, 30);
        add(female);

        other = new JRadioButton("Other");
        other.setBounds(150, 130, 100, 30);
        add(other);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        programming = new JLabel("Programming Languages:");
        programming.setBounds(50, 170, 150, 30);
        add(programming);

        javaBox = new JCheckBox("Java");
        javaBox.setBounds(200, 170, 100, 30);
        add(javaBox);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                button btn = new button();
                btn.components();
                
            }
        });
    }


    
}
