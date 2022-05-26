package il.client;

import java.util.LinkedList;

import il.client.DiffClasses.Complaint;
import il.client.DiffClasses.Order;
import il.entities.User;

public class UserClient {
    Priority priority = null;
    private int id = 0;
    private String userName = "Default";
    private String password = "Default";
    private String creditCard = "Default";
    private double credit = 0;
    private String position = "Default";
    private String name = "Default";
    private String phone = "Default";
    private String mail = "Default";
    private String expiryDate = "Default";
    private String identifyNumbers = "Default";
    private double discount = 0;

    private boolean isLogin = false;

    private LinkedList<Complaint> complaintList  = null;
    private LinkedList<Order> orderList = null;

    private User UserServer = null;

    private static UserClient user = null;

    private UserClient() {
        priority = new Priority();
        priority.setPriority_level(1);
        user = this;
    }

    public User getUserServer(){
//        Message message = new Message("GetServerUser");
//        this.UserServer = SimpleClient.getClient().sendToServer(message);
        return UserServer;
    }

    public void setPriority(int priority) {
        this.priority.setPriority_level(priority);
    }
    public int getPriority(){
        return this.priority.getPriority_level();
    }

    public static UserClient getInstance(){
        if(user == null) {
            user = new UserClient();
        }
        return user;
    }
//    public void setUserByServer(User UserServer){
////        complaintList = UserServer.getComplaints(); //get from server complaints and Orders;
////        orderList = UserServer.getOrders();
//        id = UserServer.getId();
//        userName = UserServer.getUserName();
//        password = UserServer.getPassword();
//        creditCard = UserServer.getCreditCard();
//        credit = UserServer.getCredit();
//        position = UserServer.getPosition();
//        name = UserServer.getName();
//        phone = UserServer.getPhone();
//        mail = UserServer.getMail();
//        expiryDate = UserServer.getExpiryDate();
//        identifyNumbers = UserServer.getIdentifyNumbers();
//        discount = UserServer.getDiscount();
//    }
    public void TestLoginFunction(int id, String username, String password, String CreditCard, String name, String position, double discount, String mail){
//        complaintList = UserServer.getComplaints(); //get from server complaints and Orders;
//        orderList = UserServer.getOrders();
        this.id = id;
        this.userName = username;
        this.password = password;
        this.creditCard = CreditCard;
        this.position = position;
        this.name = name;
        this.mail = mail;
        this.discount = discount;
    }

    public String getPosition() {
        return position;
    }
}
