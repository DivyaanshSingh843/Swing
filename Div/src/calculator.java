import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class calculator extends JFrame {
    public calculator() {
        setTitle("Calculator Application");
        setLayout(null);
        setBounds(800,50, 350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
}

    JLabel level, MC, MR, Mplus, Mminus, MS, Mpower;
    JTextField nameField;
    JButton percent, CE, C, cross, half, square, squareroot, devide, seven, eight, nine, multiply, four, five, six, minus, one, two, three, plus, both, zero, dot, equals;
    
    public void components() {
        level = new JLabel();
        level.setText("Standard:");
        level.setBounds(50, 30, 100, 30);
        add(level);
        MC = new JLabel();
        MC.setText("MC");
        MC.setBounds(20, 150, 50, 30);
        add(MC);
        MR = new JLabel();
        MR.setText("MR");
        MR.setBounds(70, 150, 50, 30);
        add(MR);
        Mplus = new JLabel();
        Mplus.setText("M+");
        Mplus.setBounds(120, 150, 50, 30);
        add(Mplus);
        Mminus = new JLabel();
        Mminus.setText("M-");
        Mminus.setBounds(170, 150, 50, 30);
        add(Mminus);
        MS = new JLabel();
        MS.setText("MS");
        MS.setBounds(220, 150, 50, 30);
        add(MS);
        Mpower = new JLabel();
        Mpower.setText("M^");
        Mpower.setBounds(270, 150, 50, 30);
        add(Mpower);
        percent = new JButton("%");
        percent.setBounds(0, 190, 87, 45);
        add(percent);
        CE = new JButton("CE");
        CE.setBounds(87, 190, 87, 45);
        add(CE);
        C = new JButton("C");
        C.setBounds(174, 190, 87, 45);
        add(C);
        cross = new JButton("⌫");
        cross.setBounds(261, 190, 87, 45);
        add(cross);
        half = new JButton("1/X");
        half.setBounds(0, 235, 87, 45);
        add(half);
        square = new JButton("X²");
        square.setBounds(87, 235, 87, 45);
        add(square);
        squareroot = new JButton("2√X");
        squareroot.setBounds(174, 235, 87, 45);
        add(squareroot);
        devide = new JButton("/");
        devide.setBounds(261, 235, 87, 45);
        add(devide);
        seven = new JButton("7");
        seven.setBounds(0, 280, 87, 45);
        add(seven);
        eight = new JButton("8");
        eight.setBounds(87, 280, 87, 45);
        add(eight);
        nine = new JButton("9");
        nine.setBounds(174, 280, 87, 45);
        add(nine);
        multiply = new JButton("*");
        multiply.setBounds(261, 280, 87, 45);
        add(multiply);
        four = new JButton("4");
        four.setBounds(0, 325, 87, 45);
        add(four);
        five = new JButton("5");
        five.setBounds(87, 325, 87, 45);
        add(five);
        six = new JButton("6");
        six.setBounds(174, 325, 87, 45);
        add(six);
        minus = new JButton("-");
        minus.setBounds(261, 325, 87, 45);
        add(minus);
        one = new JButton("1");
        one.setBounds(0, 370, 87, 45);
        add(one);
        two = new JButton("2");
        two.setBounds(87, 370, 87, 45);
        add(two);
        three = new JButton("3");
        three.setBounds(174, 370, 87, 45);
        add(three);
        plus = new JButton("+");
        plus.setBounds(261, 370, 87, 45);
        add(plus);
        both = new JButton("+/-");
        both.setBounds(0, 410, 87, 45);
        add(both);
        zero = new JButton("0");
        zero.setBounds(87, 410, 87, 45);
        add(zero);
        dot = new JButton(".");
        dot.setBounds(174, 410, 87, 45);
        add(dot);
        equals = new JButton("=");
        equals.setBounds(261, 410, 87, 45);
        add(equals);

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                calculator cal = new calculator();
                cal.components();
            }
        });
    }
    
}
