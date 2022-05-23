import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//time, if(table index > a certain value) insert new JRow to say max j


//change layouts and colours, make functionsout of stuff
//A band stuff - stages, days, file download, clear table


/**
 * Class to provide the user the opportunity to create their festival lineup
 */
public class Schedule implements ActionListener{
    private final int maxNumInputs = 3;
    private final int numPanels = 4;
    private final int maxNumDays = 3;
    private final String mainEventTime = "23:00";

    private String name, priority, day, startTime, endTime;
    private int tableIndex, priorityNum,dayNum;
    private int numDays;
    private int numInputs = maxNumInputs;
    private int areYouSure = 0;
    
    private JFrame frame = new JFrame("Festival Schedule 2022");
    private JPanel panel[] = new JPanel[numPanels];
    private JTextField textField[] = new JTextField[maxNumInputs];
    private JLabel inputLabel[] = new JLabel[maxNumInputs];
    private JButton subButton = new JButton("Insert");
    private JButton backButton = new JButton("Back");
    private DefaultTableModel dtm[] = new DefaultTableModel[maxNumDays];
    private JTable tabel[] = new JTable[maxNumDays];

    private List list[] = new List[maxNumDays];

    /**
     * Creates a new Sc=hedule input window
     * @param _name the name of the festival
     * @param _numDays the number of days the festival lasts for
     */
    public Schedule(String _name, int _numDays){
        panel[0] = new JPanel(new BorderLayout());
        frame.setContentPane(panel[0]);
        
        //add components to window
        initInputPanel(_numDays);
        initTablePanel(_numDays);
        initFilePanel();

        numDays = _numDays;
    
        //frame characteristics
        setFrameSize(_numDays);
        frame.setTitle(_name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    //add act button
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == subButton){
            name = textField[0].getText();
            priority = textField[1].getText();
        
            if(numDays == 1){
                day = "1";
            }
            else{
                day = textField[2].getText();
            }
    
            //input validity check
            if(StartMenu.isNumeric(priority) == true && StartMenu.isNumeric(day) == true &&
            priority.length() > 0 && name.length() > 0 && day.length() > 0){
                priorityNum = StartMenu.strToInt(priority);
                dayNum = StartMenu.strToInt(day);

                if(tableIndex != Integer.MIN_VALUE && validDay(numDays, dayNum) == true){
                    tableIndex = list[dayNum - 1].getActIndex(priorityNum);
                    startTime = setStartTime(tableIndex);
                    endTime = setEndTime(startTime);

                    if(tableIndex < 47){
                        //insert new act to table
                        dtm[dayNum - 1].insertRow(tableIndex, new Object[] {" ", startTime, endTime , name});
                        updateTimes(tableIndex, dayNum, startTime, endTime);
                    }
                    else if(e.getSource() != backButton){
                        displayInputError(numDays);
                    }   
                }
                else if(e.getSource() !=backButton){
                    displayInputError(numDays);
                }  
            }
            else if(e.getSource() !=backButton){
                displayInputError(numDays);
            }   
        }
        
        if(e.getSource() == backButton){
            areYouSure++;

            inputLabel[2].setText("Going Back will delete all progress, press again if certain");
            inputLabel[2].setVisible(true);

            if(areYouSure == 2){
                frame.dispose();
                StartMenu newSM = new StartMenu();
            }
        }
    }
    //sets start time of act depending on the index inserted at in table
    private String setStartTime(int _tableIndex){
        //Standard headline time 23:00
        int hour=23;
        int minutes=0;
        
        if(_tableIndex == 0){
            return mainEventTime;
        }
        else{
            for(int i = 0; i < _tableIndex; i++){
                minutes -= 30;
                if(minutes == -30){
                    hour -= 1;
                    minutes *= -1;
                }       
            }
            return componentsToTime(hour, minutes);
        }  
    }

    //sets end time of act depending on start time
    private String setEndTime(String _startTime){
        int hour, minutes;

        hour = extractTime(_startTime, 0);
        minutes = extractTime(_startTime, 1);

        if(minutes == 0 && hour == 23){
            minutes = 55;
        }
        else if(minutes == 0 && hour != 23){
            minutes = 25;
        }
        else if(minutes > 10){
            minutes = 55;
        }

        return componentsToTime(hour, minutes);
   }

    //updates the start and end times of all rows after newly inserted row
    private void updateTimes(int _tableIndex, int _dayNum, String _insertedStart, String _insertedEnd){
        int startHour, startMinutes, endHour, endMinutes;

        //get the inserted times
        startHour = extractTime(_insertedStart, 0);
        startMinutes = extractTime(_insertedStart, 1);
        endHour = extractTime(_insertedEnd, 0);
        endMinutes = extractTime(_insertedEnd, 1);
        
        //update the times of subsequent rows
        for(int j = _tableIndex + 1; j <list[_dayNum - 1].getListSize(); j++){
            if(startMinutes == 0){
                startMinutes = 30;
                startHour -= 1;
            }
            else if(startMinutes == 30){
                startMinutes = 0;
            }
            if(endMinutes == 25){
                endMinutes = 55;
                endHour -= 1;
            }
            else if(endMinutes == 55){
                endMinutes = 25;
            }

            dtm[_dayNum-1].setValueAt(componentsToTime(startHour, startMinutes), j, 1);
            dtm[_dayNum-1].setValueAt(componentsToTime(endHour, endMinutes), j, 2);
        }
    }
    
    //converts the int values of the manipulated hours/mins into a final time String representation
    private String componentsToTime(int _hour, int _minutes){
        String time;

        if(_hour < 10 && _minutes == 0){
            time = "0" + _hour + ":" + _minutes + "0";
        }
        else if(_minutes == 0){
            time = _hour + ":" + _minutes + "0";
        }
        else if(_hour < 10){
            time = "0" + _hour + ":" + _minutes;
        }
        else{
            time = _hour + ":" + _minutes;
        }

        return time;
   }
   
   //extracts manipulable hours/mins ints from time String representation
    private int extractTime(String _time, int _units){
        String s = "extractTime() error";
    
        if(_units == 0){//hours
        s =  _time.substring(0, 2);
        }
        else if(_units == 1){//minutes
        s =  _time.substring(3, 5);
        }

        return StartMenu.strToInt(s);
    }

    //displays invalid input error message
    private void displayInputError(int _numDays){
        if(_numDays == 1){
            for(int i = 0; i < 2; i++){
                textField[i].setText("ERROR");
            }
        }
        else if(_numDays == 2 || _numDays == 3){
            for(int i = 0; i < 3; i++){
                textField[i].setText("ERROR");
            } 
        }
    }

    private void setFrameSize(int _numDays){
        switch(_numDays){
            case 1:
                frame.setSize(800, 550);
                break;
            case 2:
                frame.setSize(1100, 550);
                break;
            case 3:
                frame.setSize(1500, 550);
                break;
        }
    }
    
    //adds panel for user input
    private void initInputPanel(int _days){
        panel[1] = new JPanel(new FlowLayout());

        inputLabel[0] = new JLabel("Act Name: ");
        inputLabel[1] = new JLabel("Priority (1-3): ");
        
        switch(_days){
            case 1:
                break;
            case 2:
                inputLabel[2] = new JLabel("Day (1-2): ");
                break;
            case 3:
                inputLabel[2] = new JLabel("Day (1-3): ");
                break;
        }
        
        if(_days == 1){
            numInputs = 2;
        }

        for(int i = 0; i < numInputs; i++){
            textField[i] = new JTextField(20);
            
            panel[1].add(inputLabel[i]);
            panel[1].add(textField[i]);
        }

        subButton.addActionListener(this);
        panel[1].add(subButton);

        panel[0].add(panel[1], BorderLayout.NORTH);
    }

    
    //adds panel containing tables to display input
    private void initTablePanel(int _days){
        panel[2] = new JPanel(new FlowLayout());

        for(int i = 0; i < _days ; i++){
            list[i] = new List();
            dtm[i] = new DefaultTableModel();
            tabel[i] = new JTable(dtm[i]);
            
            dtm[i].addColumn("DAY " + (i+1));
            dtm[i].addColumn("Start Time");
            dtm[i].addColumn("End Time");
            dtm[i].addColumn("Act");

            panel[2].add(new JScrollPane(tabel[i]));

        }
        panel[0].add(panel[2], BorderLayout.CENTER);
    }

    private void initFilePanel(){
        panel[3] = new JPanel(new FlowLayout());

        inputLabel[2] = new JLabel("");
            
        backButton.addActionListener(this);
        panel[3].add(backButton);
        panel[3].add(inputLabel[2]);

        inputLabel[2].setVisible(false);

        panel[0].add(panel[3], BorderLayout.SOUTH);
    }


    //number of days validity check
    private boolean validDay(int x, int y){
        boolean b;
        switch(x){
            case 1:
                if(y!=1) b = false;

            case 2:
                if(y<1 || y>2) b = false;

            case 3:
                if(y<1 || y>3) b = false;
 
            default:
                b = true;    
            }

            return b;
    }
}