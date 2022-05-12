1public class Act{
    private String name;
    private int priority;

    
    
    public Act(String _name, int _priority){
        name = _name;
        priority = _priority;
    }
    
    
    
    
    
    //getters--------------------------------
    
    public String getName(){
        return name;
    }

    public int getPriority(){
        return priority;
    }

    //setters--------------------------------
    public void setName(String s){
        name = s;
    }

    public void setPriority(int x){
        priority = x;
    }
}