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
        
        int LPONE = _priority + 1;
        int LPTWO = _priority + 2;
        int index;
        
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
    
    public int getListSize(){
        return actList.size();
    }

    public int getListIndex(int index){
        return actList.get(index);
    }
}