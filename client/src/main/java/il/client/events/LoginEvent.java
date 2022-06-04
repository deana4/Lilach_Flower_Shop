package il.client.events;

import il.entities.*;

import java.util.LinkedList;

public class LoginEvent {


    private LinkedList<Complain> complainList=null;
    private LinkedList<Order> orderList=null;

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    private boolean isWorker;

    private String username;
    private int permission;


    private boolean loginStatus;

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String result;


    public LinkedList<Complain> getComplainList() {
        return complainList;
    }

    public void setComplainList(LinkedList<Complain> complainList) {
        this.complainList = complainList;
    }

    public LinkedList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(LinkedList<Order> orderList) {
        this.orderList = orderList;
    }

    public LinkedList<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(LinkedList<Store> storeList) {
        this.storeList = storeList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private LinkedList<Store>  storeList=null;
    private User user;

    public LoginEvent(boolean status, String result){
        this.loginStatus = status;
        this.result = result;
    }

    public LoginEvent(boolean statsu, User user, LinkedList<Complain> complains, LinkedList<Order> orders, LinkedList<Store> stores){
        this.loginStatus = statsu;
        this.storeList = stores;
        this.orderList = orders;
        this.complainList = complains;
        this.user = user;
        this.username = user.getUserName();
        this.isWorker = false;
    }


    public LoginEvent(String username, int permission){
        this.loginStatus = true;
        this.isWorker = true;
        this.username = username;
        this.permission = permission;
    }


}
