package il.client;

public class Priority {
    int priority_level; //1- guest, 2-worker, 3-manager, 4-Owner
    public Priority(){
        this.priority_level = 1;
    }
    public int getPriority_level(){
        return this.priority_level;
    }
    public void setPriority_level(int priority_level) {
        this.priority_level = priority_level;
    }
}
