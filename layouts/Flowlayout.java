import javax.swing.*;
import java.awt.*;

public class Flowlayout {
    public static void main(String[] args) {

        // Create main window
        JFrame frame = new JFrame("FlowLayout Basic Example");

        // Close the program when X is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel; JPanel default layout is FlowLayout
        JPanel panel = new JPanel(); // uses new FlowLayout() internally

        // Add some buttons to the panel
        panel.add(new JButton("Button 1"));
        panel.add(new JButton("Button 2"));
        panel.add(new JButton("Button 3"));
        panel.add(new JButton("Button 4"));
        panel.add(new JButton("Button 5"));

        // Add panel to frame
        frame.add(panel);

        // Set frame size and center it
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        // Show the window
        frame.setVisible(true);
    }
}


import javax.swing.*;
import java.awt.*;

public class FlowLayoutLeftWithGaps {
    public static void main(String[] args) {

        JFrame frame = new JFrame("FlowLayout LEFT with gaps");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panel
        JPanel panel = new JPanel();

        // Set FlowLayout with LEFT alignment and big gaps
        FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 20, 15);
        panel.setLayout(flow);

        // Add components
        panel.add(new JButton("One"));
        panel.add(new JButton("Two"));
        panel.add(new JButton("Three"));
        panel.add(new JButton("Four"));
        panel.add(new JButton("Five"));

        frame.add(panel);

        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
