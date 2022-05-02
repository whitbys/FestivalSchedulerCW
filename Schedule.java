import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Schedule{
    JFrame frame = new JFrame("Festival Schedule 2022");
    JPanel panel = new JPanel(new BorderLayout());


    public Schedule(String _name, int _numDays, int _numStages){
        frame.setContentPane(panel);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(750, 250);
        frame.setVisible(true);
    }
}