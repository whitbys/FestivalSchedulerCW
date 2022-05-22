import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

//time, if(table index > a certain value) insert new JRow to say max j


//change layouts and colours, make functionsout of stuff
//A band stuff - stages, days, file download, clear table



public class Schedule implements ActionListener{
    private final int MaxnumInputs = 3;
    private final int numPanels = 3;
    private final int maxNumDays = 3;

    private String name, priority, day, startTime, endTime;
    private int tableIndex, priorityNum,dayNum;
    private int numDays;
    private int numInputs = MaxnumInputs;
    private String mainEventTime = "23:00";
    
    
    JFrame frame = new JFrame("Festival Schedule 2022");
    JPanel panel[] = new JPanel[numPanels];
    JTextField textField[] = new JTextField[MaxnumInputs];
    JLabel label[] = new JLabel[MaxnumInputs];
    JButton sButton = new JButton("Insert");

    DefaultTableModel dtm[] = new DefaultTableModel[maxNumDays];
    JTable tabel[] = new JTable[maxNumDays];
    JPanel tabelPanel[] = new JPanel[maxNumDays];


    List list[] = new List[maxNumDays];


    public Schedule(String _name, int _numDays){
        panel[0] = new JPanel(new BorderLayout());
        frame.setContentPane(panel[0]);
        frame.setTitle(_name);

        initInputPanel(_numDays);
        initTablePanel(_numDays);

        numDays = _numDays;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(750, 250);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        name = textField[0].getText();
        priority = textField[1].getText();
        
        if(numDays == 1){
            day = "1";
        }
        else{
            day = textField[2].getText();
        }
        

        if(StartMenu.isNumeric(priority) == true &&
        StartMenu.isNumeric(day) == true &&
        priority.length() > 0 && name.length() > 0
        && day.length() > 0){
            
            priorityNum = StartMenu.strToInt(priority);
            dayNum = StartMenu.strToInt(day);

            if(priorityNum > 0 && priorityNum < 4 &&
            validDay(numDays, dayNum) == true){
                tableIndex = list[dayNum - 1].getActIndex(priorityNum);
                
                if(tableIndex != Integer.MIN_VALUE){
                    startTime = setStartTime(tableIndex);
                    endTime = setEndTime(startTime);
                    
                    dtm[dayNum - 1].insertRow(tableIndex, new Object[] {startTime, endTime , name});

                    
                    updateTimes(tableIndex, dayNum, startTime, endTime);
                }
                else{
                    textField[1].setText("Please set a valid POSITIVE integer input within range");
                }
            }
        }
        else{
            textField[1].setText("Please set a valid positive INTEGER input");
        }
    }
    
    
    //new functions---------------------------------------
    private String setStartTime(int _tableIndex){
        String time = mainEventTime;
        
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

    private void updateTimes(int _tableIndex, int _dayNum, String _insertedStart, String _insertedEnd){
        //code to update all following row times
        //for loop(length - index)
        //add 20 mins to x, if x goes over hour deal with this
        //return x
        // Object startTime, endTime;
        // String startTimeString, endTimeString;
        int startHour, startMinutes, endHour, endMinutes;

        
        //get the inserted times

        // startTimeString = dtm[_dayNum-1].getValueAt(_tableIndex, 0);
        // endTimeString = dtm[_dayNum-1].getValueAt(_tableIndex, 1);
        
        
        System.out.println(_insertedStart + "\n" + _insertedEnd+ "\n");


        //get the inseted minutes/ hours
        startHour = extractTime(_insertedStart, 0);
        startMinutes = extractTime(_insertedStart, 1);
        endHour = extractTime(_insertedEnd, 0);
        endMinutes = extractTime(_insertedEnd, 1);
        
        
        
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

            dtm[_dayNum-1].setValueAt(componentsToTime(startHour, startMinutes), j, 0);
            dtm[_dayNum-1].setValueAt(componentsToTime(endHour, endMinutes), j, 1);
        }
    }
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
   
   private int extractTime(String _time, int _units){
        String s = "extractTime() error";
    
        if(_units == 0){
        s =  _time.substring(0, 2);

        System.out.println(s+ "\n");
        }
        else if(_units == 1){
        s =  _time.substring(3, 5);
        System.out.println(s + "\n");
        }

        return StartMenu.strToInt(s);
   }

    //23:00, 23:55
    //22:30, 22:55
    //22:00, 22:25
    
    
    //--------------------------------------------
    private void initInputPanel(int _days){
        panel[1] = new JPanel(new FlowLayout());

        label[0] = new JLabel("Act Name: ");
        label[1] = new JLabel("Priority (1-3): ");
        
        switch(_days){
            case 1:
                break;
            case 2:
                label[2] = new JLabel("Day (1-2): ");
                break;
            case 3:
                label[2] = new JLabel("Day (1-3): ");
                break;
        }
        
        
        if(_days == 1){
            numInputs = 2;
        }

        for(int i = 0; i < numInputs; i++){
            textField[i] = new JTextField(20);
            
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
            list[i] = new List();
            
            dtm[i] = new DefaultTableModel();
            tabel[i] = new JTable(dtm[i]);
            
            dtm[i].addColumn("Start Time");
            dtm[i].addColumn("End Time");
            dtm[i].addColumn("Act");

            panel[2].add(new JScrollPane(tabel[i]));

        }
        panel[0].add(panel[2], BorderLayout.EAST);
    }

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