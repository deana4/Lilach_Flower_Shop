package il.entities;

import java.io.Serializable;
import java.util.LinkedList;

public class Message implements Serializable {
    String message;

    public Message(String message){
        this.message = message;
    }

    LinkedList<Flower> listItem=null;
    Flower item=null;

    User user=null;


    //login
    String id;
    String pass;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LinkedList<Flower> getListItem() {
        return listItem;
    }

    public void setListItem(LinkedList<Flower> listItem) {
        this.listItem = listItem;
    }

    public Flower getItem() {
        return item;
    }

    public void setItem(Flower item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    boolean isWorker;

}
