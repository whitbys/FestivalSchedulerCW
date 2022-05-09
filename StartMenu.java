import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Character;


//to add later
//resize panels
//colour panels + error label


//remove the stages parameter 

//JTBD
//construct schedule depending on parameter input
//AL-insert into list depending on priority given
//create a loop around
//add a button to download JTABLE into files

public class StartMenu implements ActionListener{ 
    private final int numParameters = 2;
    private final int numPanels = 4;
    private final int dayStageCharlimit = 1;
    
    private String param[] = new String[numParameters];
    private int numDays;
    
    JFrame frame = new JFrame("Schedule Options");
    JPanel panel[] = new JPanel[numPanels];
    JButton subButton = new JButton("Create Empty Schdule");
    JLabel label[] = new JLabel[numParameters];
    JTextField textField[] = new JTextField[numParameters];

    public StartMenu(){
        panel[0] = new JPanel(new BorderLayout());
        frame.setContentPane(panel[0]);

        initNamePanel();
        initDayPanel();
        initSubmitButton();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(750, 250);
        frame.setVisible(true);
    }


    public static void main(String args[]){
        StartMenu sm = new StartMenu();
    }

    public void actionPerformed(ActionEvent e){
        
        if(validNumber(textField[1].getText()) == true 
        && validName(textField[0].getText()) == true){
            
            numDays = strToInt(textField[1].getText());
            
            frame.dispose();
            Schedule newSchedule = new Schedule(textField[0].getText(), numDays);
        }
    }    
    
    private boolean validName(String s){
        
        if(s.length() == 0){
            textField[0].setText("please enter a name");
            return false;
            
        }
        return true;
        
    }
    
    private boolean validNumber(String s){
        for(int i = 1; i < numParameters; i++){
            if(s.isEmpty()==true || isNumeric(s)==false 
            || s.length()!=dayStageCharlimit 
            || strToInt(s) <= 0 || strToInt(s) >= 4){
                textField[1].setText("Please enter a number within the range specified");
                return false;
            }
        }
        return true;
    }
    
    //add aesthetic stuff later
    private void initNamePanel(){
        panel[1] = new JPanel(new GridLayout());
        
        label[0] = new JLabel("Name of Festival");
        textField[0] = new JTextField(30);

        panel[1].add(label[0]);
        panel[1].add(textField[0]);

        panel[0].add(panel[1], BorderLayout.NORTH);
    }

    private void initDayPanel(){
        panel[2] = new JPanel(new GridLayout());
        
        label[1] = new JLabel("Days(1-3): ");
        textField[1] = new JTextField(1);
        
        panel[2].add(label[1]);
        panel[2].add(textField[1]);

        panel[0].add(panel[2], BorderLayout.CENTER);
    }


    private void initSubmitButton(){
        panel[3] = new JPanel(new GridLayout());
        
        subButton.addActionListener(this);
        
        panel[3].add(subButton);
        
        panel[0].add(panel[3], BorderLayout.SOUTH);
    }

    public static int strToInt(String s){
        
        try{
            int number = Integer.parseInt(s);

            return number;
        }
        catch(NumberFormatException e){
            System.out.println("strToInt is Wrong");
            return Integer.MIN_VALUE;
        }
        
    }

    public static boolean isNumeric(String s){
        try {
            int Value = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("is numeric is false");
            return false;
            
        }
    }

    


















    //getters/setters

    
}