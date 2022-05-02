import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Character;


//to add later
//resize panels
//colour panels + error label

public class StartMenu implements ActionListener{ 
    private final int numParameters = 3;
    private final int numPanels = 5;
    private final int dayStageCharlimit = 1;
    
    

    private String param[] = new String[numParameters];
    private int numberParam[] = new int[numParameters-1];
    private boolean paramFilled;
    
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
        initStagePanel();
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
        paramFilled = true;


        for(int i = 0; i < numParameters; i++){
            param[i] = textField[i].getText();
        }
        for(int j = 1; j < numParameters; j++){
            if(isNumeric(param[j]) == true && param[j].isEmpty() == false 
            && param[j].length() > dayStageCharlimit){
                numberParam[j-1] = strToInt(param[j]);
            }
            else{
                JLabel errorLabel = new JLabel("Please enter a number within the range specified");
                panel[j+1].add(errorLabel);
                paramFilled = false;
            }
        }

        if(paramFilled == true){
            frame.dispose();

            Schedule newSchedule = new Schedule(param[0], numberParam[0], numberParam[1]);
        }

        
        
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

    private void initStagePanel(){
        panel[3] = new JPanel(new GridLayout());
        
        label[2] = new JLabel("Stages Available(1-3): ");
        textField[2] = new JTextField(1);

        panel[3].add(label[2]);
        panel[3].add(textField[2]);

        panel[0].add(panel[3], BorderLayout.WEST);
    }

    private void initSubmitButton(){
        panel[4] = new JPanel(new GridLayout());
        
        subButton.addActionListener(this);
        
        panel[4].add(subButton);
        
        panel[0].add(panel[4], BorderLayout.SOUTH);
    }

    public int strToInt(String s){
        int number = Integer.parseInt(s);

        return number;
    
    }

    private boolean isNumeric(String s){
        try {
            int Value = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
            return false;
        }
    }

















    //getters/setters

    
}