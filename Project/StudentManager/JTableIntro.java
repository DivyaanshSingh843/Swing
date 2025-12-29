import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableIntro extends JFrame {

    public JTableIntro() {
        setTitle("JTable Example");
        setBounds(100, 100, 700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

    }

    JTable table;
    JScrollPane scroll;
    DefaultTableModel model;

    public void component() {

        String[] columns = { "Name", "Age", "ID" };
        model = new DefaultTableModel();

        model.setColumnIdentifiers(columns);
        table = new JTable(model);
        // table.setModel(model);
        scroll = new JScrollPane(table);
        scroll.setBounds(350, 50, 300, 200);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll);

        


    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JTableIntro obj = new JTableIntro();
                obj.component();
            }
        });
    }
}