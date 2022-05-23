import java.util.ArrayList;


/**
 * This class provides a modified ArrayList with the added method of getActIndex
 */
public class List{
    private ArrayList<Integer> actList = new ArrayList<>();
    
    
    /**
     * creates a new List
     */
    public List(){

    }
    
    /**
     * determines the index of the act based on a priority level
     * @param _priority This is the priority value of the act
     * @return the index of the act within the list
     */
    public int getActIndex(int _priority){
        
        //check range
        if(_priority < 1 || _priority > 3){
            return Integer.MIN_VALUE;
        }
        
        int LPONE = _priority + 1;
        int LPTWO = _priority + 2;
        int index;
        
        
        //check for lower priorities and insert at the first instance of them
        if(actList.indexOf(LPONE) != -1){
            index = actList.indexOf(LPONE);
            actList.add(index, _priority);
        }
        else if(actList.indexOf(LPTWO)!=-1){
            index = actList.indexOf(LPTWO);
            actList.add(actList.indexOf(_priority+2), _priority);
            
        }
        else{
            actList.add(_priority);
            index = actList.size() - 1;
            
        }
        return index;
        
    }
    
    /**
     * retrieves the size of the list of act priorities
     * @return the size of the list
     */
    public int getListSize(){
        return actList.size();
    }

}