import java.util.LinkedList;

public class List{
    LinkedList<Integer> actList = new LinkedList<>();
    
    public List(){

    }
    

    public int getActIndex(int _priority){
        
        
        if(_priority < 1 || _priority > 3){
            return Integer.MIN_VALUE;
        }
        

        
        if(actList.indexOf(_priority + 1) != -1){
            System.out.println("where int is supposed to be inserted:" + actList.indexOf(_priority+1));
            actList.add(actList.indexOf(_priority+1), _priority);
        }
        else if(actList.indexOf(_priority + 2)!=-1){
            actList.add(actList.indexOf(_priority+2), _priority);
            
        }
        else{
            actList.add(_priority);
            
        }
        System.out.println("where int was inserted: " + actList.indexOf(_priority));
        return actList.indexOf(_priority);
        
    }

    
    
    
    
    
    
    public int getListSize(){
        return actList.size();
    }

    public int getListIndex(int index){
        return actList.get(index);
    }
}