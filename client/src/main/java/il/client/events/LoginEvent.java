package il.client.events;

import il.entities.*;

import java.util.LinkedList;

public class LoginEvent {



    private Boolean status;

    private String result;

    private User user;

    private Employee employee;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

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

    private LinkedList<Complain> complainList=null;
    private LinkedList<Order> orderList=null;
    private LinkedList<Store>  storeList=null;
    private String username;

    public LoginEvent(Boolean status, String result, User user, LinkedList<Complain> complainList, LinkedList<Order> orderList, LinkedList<Store> storeList) {
        this.status = status;
        this.result = result;
        this.user = user;
        this.complainList = complainList;
        this.orderList = orderList;
        this.storeList = storeList;
    }

    public LoginEvent(boolean status, String result){
        this.status = status;
        this.result = result;
    }
    public LoginEvent(boolean status, String result, Employee employee){
        this.status = status;
        this.result = result;
        this.employee = employee;
    }
    public LoginEvent(boolean status, String result, User user){
        this.status = status;
        this.result = result;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginEvent(boolean status, String result, String username){
        this.status = status;
        this.result = result;
        this.username = username;
    }
}
