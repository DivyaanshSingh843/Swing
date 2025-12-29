import javax.swing.*;

public class label {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Label");

        ImageIcon icon = new ImageIcon("logo.jpg"); // Your image file
        JLabel label = new JLabel("icon", icon, JLabel.LEFT);            // Label with image only

        frame.add(label);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
