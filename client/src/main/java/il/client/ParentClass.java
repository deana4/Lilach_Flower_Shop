package il.client;

import il.client.DiffClasses.Priority;

public class ParentClass {
    static Priority priority;
    ParentClass(){
    }
    void init_priority(){
        priority = new Priority();
        priority.setPriority_level(1);
    }
}
