import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class panels {

    public static void main(String[] args) {

        JPanel redpanel = new JPanel();
        redpanel.setBackground(Color.red);
        redpanel.setBounds(0, 0, 250, 250);

        JPanel bluepanel = new JPanel();
        redpanel.setBackground(Color.BLUE);
        redpanel.setBounds(250, 0, 250, 250);



        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750,750);
        frame.setVisible(true);

        frame.add(redpanel);
    }
    
}
