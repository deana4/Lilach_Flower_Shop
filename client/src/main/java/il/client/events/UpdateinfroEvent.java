package il.client.events;

import il.entities.Employee;
import il.entities.User;

import java.util.LinkedList;

public class UpdateinfroEvent {

    private String result;
    private LinkedList <Employee> listEmp;
    private LinkedList <User> listUser;


    public UpdateinfroEvent(String result,LinkedList <User> listUser, LinkedList <Employee> listEmp) {
        this.result = result;
        this.listEmp=listEmp;
        this.listUser=listUser;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}