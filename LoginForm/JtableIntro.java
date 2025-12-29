import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class JtableIntro extends JFrame {

    JtableIntro(){
        setTitle("JTable Intro");
        setBounds(100, 100, 700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    JLabel name, id, age;
    JTextField tname, tid, tage;
    JButton submit;
    JTable table;
    JScrollPane scroll;


    public void components(){

        name = new JLabel("Name");
        name.setBounds(50,100 , 100, 30);
        add(name);


    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){

            public void run(){

                JtableIntro table = new JtableIntro();
                table.components();
            }
        });
        
    }
    
}
