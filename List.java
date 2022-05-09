import java.util.LinkedList;

public class List{
    LinkedList<Integer> actList = new LinkedList<>();
    
    public List(){

    }
    

    public int getActIndex(int _priority){
        
        
        if(_priority < 1){
            return Integer.MIN_VALUE;
        }
//fix for 1-3 case
        
        if(actList.indexOf(_priority + 1) != -1){
            actList.add(actList.indexOf(_priority+1), _priority);
            
        }
        else if(actList.indexOf(_priority+2)!=-1){
            actList.add(actList.indexOf(_priority+2), _priority);
            
        }
        else{
            actList.add(_priority);
            
        }
        System.out.println("list index: " + actList.indexOf(_priority));
        return actList.indexOf(_priority);
        
    }

    
    
    
    
    
    
    public int getListSize(){
        return actList.size();
    }

    public int getListIndex(int index){
        return actList.get(index);
    }
}