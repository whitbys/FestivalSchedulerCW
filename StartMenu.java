import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.lang.Character;

/**
 * This class represents a start menu which allows the user to input parameters for their festival structure
 */
public class StartMenu implements ActionListener{ 
    private final int numParameters = 2;
    private final int numPanels = 4;
    private final int dayStageCharlimit = 1;
    private final int nameCharlimit = 50;
    
    private String param[] = new String[numParameters];
    private int numDays;
    
    private JFrame frame = new JFrame("Schedule Options");
    private JPanel panel[] = new JPanel[numPanels];
    private JButton subButton = new JButton("Create Empty Schdule");
    private JLabel label[] = new JLabel[numParameters];
    private JTextField textField[] = new JTextField[numParameters];

    
    /**
     * Creates a new Start Menu
     */
    public StartMenu(){
        panel[0] = new JPanel(new BorderLayout());
        frame.setContentPane(panel[0]);

        
        //add components to window
        initNamePanel();
        initDayPanel();
        initSubmitButton();

        //frame characteristics
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(650, 110);
        frame.setVisible(true);
    }

    public static void main(String args[]){
        StartMenu sm = new StartMenu();
    }

    //create scedule button
    public void actionPerformed(ActionEvent e){
        if(validNumber(textField[1].getText()) == true 
        && validName(textField[0].getText()) == true){
            
            numDays = strToInt(textField[1].getText());
            
            frame.dispose();
            Schedule newSchedule = new Schedule(textField[0].getText(), numDays);
        }
        else{
            textField[0].setText("ERROR");
            textField[1].setText("ERROR");
        }
    }
        
    
    //name validity
    private boolean validName(String s){
        if(s.length() == 0){
            return false;   
        }
        return true;
    }
    
    //number of days validity
    private boolean validNumber(String s){
        if(s.isEmpty()==true || isNumeric(s)==false || s.length()!=dayStageCharlimit 
        || strToInt(s) <= 0 || strToInt(s) >= 4){
                return false;
            }
        return true;
    }

    //panel for festival name input
    private void initNamePanel(){
        panel[1] = new JPanel(new GridLayout());
        
        label[0] = new JLabel("Name of Festival");
        textField[0] = new JTextField(30);

        panel[1].add(label[0]);
        panel[1].add(textField[0]);

        panel[0].add(panel[1], BorderLayout.NORTH);
    }

    //panel for number of days input
    private void initDayPanel(){
        panel[2] = new JPanel(new GridLayout());
        
        label[1] = new JLabel("Days(1-3): ");
        textField[1] = new JTextField(1);
        
        panel[2].add(label[1]);
        panel[2].add(textField[1]);

        panel[0].add(panel[2], BorderLayout.CENTER);
    }


    //panel for submit button
    private void initSubmitButton(){
        panel[3] = new JPanel(new GridLayout());
        
        subButton.addActionListener(this);
        
        panel[3].add(subButton);
        
        panel[0].add(panel[3], BorderLayout.SOUTH);
    }

    /**
     * converts a String type to an int type
     * @param s String to be converted
     * @return the String as an int type
     */
    public static int strToInt(String s){
        try{
            int number = Integer.parseInt(s);
            return number;
        }
        catch(NumberFormatException e){
            return Integer.MIN_VALUE;
        }
    }

    /**
     * Numeric validity check for a String
     * @param s String to be checked if it is numeric
     * @return boolean value based on if the String contains only numeric characters or not
     */
    public static boolean isNumeric(String s){
        try {
            int Value = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
