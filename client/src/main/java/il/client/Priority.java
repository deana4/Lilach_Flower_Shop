package il.client;

public class Priority {
    int priority_level; //5- guest, 4-  , 3- , 2-
    int inner_priority_level;

    public Priority(){
        this.priority_level = 1;
        this.inner_priority_level = 0;
    }
    public int getPriority_level(){
        return this.priority_level;
    }

    public int getInner_priority_level() {
        return inner_priority_level;
    }

    public void setInner_priority_level(int inner_priority_level) {
        this.inner_priority_level = inner_priority_level;
    }

    public void setPriority_level(int priority_level) {
        this.priority_level = priority_level;
    }
}
