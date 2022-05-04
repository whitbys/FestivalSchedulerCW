import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

//change layouts and colours

//ways to edit table - insert, clear table

//A band stuff - stages, days, file download



public class Schedule implements ActionListener{
    private final int numInputs = 2;
    private final int numPanels = 3;
    private final int maxNumDays = 3;
    
    
    JFrame frame = new JFrame("Festival Schedule 2022");
    JPanel panel[] = new JPanel[numPanels];
    JTextField textField[] = new JTextField[numInputs];
    JLabel label[] = new JLabel[numInputs];
    JButton sButton = new JButton("Insert");

    DefaultTableModel dtm[] = new DefaultTableModel[maxNumDays];
    JTable tabel[] = new JTable[maxNumDays];
    JPanel tabelPanel[] = new JPanel[maxNumDays];


    public Schedule(String _name, int _numDays){
        panel[0] = new JPanel(new BorderLayout());
        frame.setContentPane(panel[0]);
        frame.setTitle(_name);

        initInputPanel();
        initTablePanel(_numDays);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(750, 250);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        System.out.println("heehee");
    }
    
    private void initInputPanel(){
        panel[1] = new JPanel(new FlowLayout());

        label[0] = new JLabel("Act Name: ");
        label[1] = new JLabel("Priority: ");

        for(int i = 0; i < numInputs; i++){
            textField[i] = new JTextField();
            
            panel[1].add(label[i]);
            panel[1].add(textField[i]);
        }

        sButton.addActionListener(this);
        panel[1].add(sButton);

        panel[0].add(panel[1], BorderLayout.WEST);
    }

    private void initTablePanel(int _days){
        panel[2] = new JPanel(new FlowLayout());

        for(int i = 0; i < _days ; i++){
            dtm[i] = new DefaultTableModel();
            tabel[i] = new JTable(dtm[i]);
            
            dtm[i].addColumn("Time");
            dtm[i].addColumn("Act");
            
            dtm[i].insertRow(0, new Object[] {"Time", "Act"});
            
            

            
            

            panel[2].add(new JScrollPane(tabel[i]));

        }

        panel[0].add(panel[2], BorderLayout.EAST);

    }
}