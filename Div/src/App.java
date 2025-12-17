import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    
    public App() {
        setTitle("Division Application");
        setLayout(null);
        setBounds(200,200, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    JLabel num1, num2, result;
    JTextField numberField1, numberField2, resultField;
    JButton submit, Plus, minus, multiply, divide;
    
    public void components() {
        num1 = new JLabel();
        num1.setText("Number 1:");
        num1.setBounds(50, 50, 100, 30);
        add(num1);

        numberField1 = new JTextField("");
        numberField1.setBounds(150, 50, 200, 30);
        add(numberField1);

        num2 = new JLabel();
        num2.setText("Number 2:");
        num2.setBounds(50, 100, 100, 30);
        add(num2);

        numberField2 = new JTextField("");
        numberField2.setBounds(150, 100, 200, 30);
        add(numberField2);

        result = new JLabel("Result:");
        result.setBounds(50, 150, 100, 30);
        add(result);

        resultField = new JTextField();
        resultField.setBounds(150, 150, 200, 30);
        add(resultField);


        Plus = new JButton("+");
        Plus.setBounds(50, 200, 70, 30);
        add(Plus);

        minus = new JButton("-");
        minus.setBounds(150, 200, 70, 30);
        add(minus);

        multiply = new JButton("*");
        multiply.setBounds(250, 200, 70, 30);
        add(multiply);

        divide = new JButton("/");
        divide.setBounds(350, 200, 70, 30);
        add(divide);

        Plus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int number1 = Integer.parseInt(numberField1.getText());
                int number2 = Integer.parseInt(numberField2.getText());
                int plus = number1 + number2;
                resultField.setText(Integer.toString(plus));
            }
        });

        minus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int number1 = Integer.parseInt(numberField1.getText());
                int number2 = Integer.parseInt(numberField2.getText());
                int minus = number1 - number2;
                resultField.setText(Integer.toString(minus));
            }
        });

        multiply.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int number1 = Integer.parseInt(numberField1.getText());
                int number2 = Integer.parseInt(numberField2.getText());
                int multiply = number1 * number2;
                resultField.setText(Integer.toString(multiply));
            }
        });

        divide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int number1 = Integer.parseInt(numberField1.getText());
                int number2 = Integer.parseInt(numberField2.getText());
                int division = number1 / number2;
                resultField.setText(Integer.toString(division));
            }
        });

        
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                App app = new App();
                app.components();
            }
        });
    }
}