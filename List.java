import java.util.LinkedList;

public class List{
    LinkedList<Integer> actList = new LinkedList<>();
    
    public List(){

    }
    

    public int getActIndex(int _priority){
        
        
        if(_priority < 1 || _priority > 3){
            return Integer.MIN_VALUE;
        }
        
        System.out.println("priority:" + _priority);
        
        int lowerpriority = _priority + 1;
        int index = actList.indexOf(lowerpriority);
        
        
        if(actList.indexOf(lowerpriority) != -1){//get the index of the 1st "2" in the list
            System.out.println("where int is supposed to be inserted:" + actList.indexOf(lowerpriority));
            
            actList.add(actList.indexOf(lowerpriority), _priority);
        }
        else if(actList.indexOf(_priority + 2)!=-1){
            actList.add(actList.indexOf(_priority+2), _priority);
            
        }
        else{
            index = 0;
            actList.add(_priority);
            
        }
        System.out.println("where int was inserted: " + actList.indexOf(_priority));
        return index;//change this this is whats wrong 
        
    }

    
    
    
    
    
    
    public int getListSize(){
        return actList.size();
    }

    public int getListIndex(int index){
        return actList.get(index);
    }
}