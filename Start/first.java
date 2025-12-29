import javax.swing.*;
import java.awt.*;

public class first {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Use WEST so labels align left
        gbc.anchor = GridBagConstraints.WEST;

        // Make components stretch horizontally
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ======== Name ========
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; // labels should not grow
        gbc.insets = new Insets(10, 10, 5, 10);
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1; // text fields expand
        gbc.insets = new Insets(10, 0, 5, 10);
        panel.add(new JTextField(20), gbc);

        // ======== Email ========
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 0, 5, 10);
        panel.add(new JTextField(20), gbc);

        // ======== Password ========
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(5, 10, 10, 10);
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 0, 10, 10);
        panel.add(new JPasswordField(20), gbc);

        // ======== Register Button ========
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;  // move button to right
        gbc.fill = GridBagConstraints.NONE;    // do not stretch button
        gbc.insets = new Insets(10, 0, 10, 10);

        JButton btn = new JButton("Register");
        gbc.ipadx = 20; // internal width padding
        gbc.ipady = 5;  // internal height padding

        panel.add(btn, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}
