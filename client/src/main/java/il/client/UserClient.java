package il.client;

import il.client.DiffClasses.ComplaintClient;
import il.entities.Complain;
import il.entities.Order;
import il.entities.Store;
import il.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

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
    private boolean isFrozen = false;
    private int plan = 0;
    private LinkedList<Integer> stores;

    private boolean isLogin = false;
    private boolean isWorker = false;

    private ObservableList<ComplaintClient> complaintList  = FXCollections.observableArrayList();
    private ObservableList<OrderClient> orderList = FXCollections.observableArrayList();
    private List<Store> storesOfStore;

    private User UserServer = null;

    private static UserClient user = null;


    private UserClient() {
        priority = new Priority();
        priority.setPriority_level(1);
        user = this;
        {
            ObservableList<CartItem> cart = FXCollections.observableArrayList();
            cart.add(new CartItem("sunflower", 159.90, 2, 1));
            cart.add(new CartItem("rose", 299.99, 2, 2));
            cart.add(new CartItem("rose", 299.99, 2, 2));
            cart.add(new CartItem("rose", 299.99, 2, 2));
            cart.add(new CartItem("rose", 299.99, 2, 2));

            //for trying examples
            orderList.add(new OrderClient( "05/06/2022", "12:00", "16:00", "06/06/2022", "thankyou", "Dean1", "12", "bla","Store 1", cart ,true, "Aviv1"));
            orderList.add(new OrderClient( "03/06/2022", "12:00", "02:00", "05/06/2022", "thankyou", "Liran1", "13", "bla","Store 2", cart ,false,"Aviv2"));
            orderList.add(new OrderClient( "02/06/2022", "12:00", "00:38", "05/06/2022", "thankyou", "Dean2", "14", "bla","Store 1", cart ,true,"Aviv3"));
            orderList.add(new OrderClient( "01/06/2022", "12:00", "12:00", "05/06/2022", "thankyou", "Liran2", "15", "bla","Store 2", cart,false ,"Aviv4"));
            for(int i=0; i<20; i++){
                orderList.add(new OrderClient( "Sunday"+i, "12:0"+i, "13:0"+i, "Sunday"+i, "thankyou"+i, "Dean"+i, "1"+i, "bla"+i,"Store 1", cart ,true,"Aviv"+i));
            }
//            orderList.add(new Order( "05/06/2022", "12:00", "16:00", "06/06/2022", "thankyou", "Dean", "1", "bla","Store 1", cart ));
//            orderList.add(new Order( "03/06/2022", "12:00", "02:00", "05/06/2022", "thankyou", "Dean", "1", "bla","Store 1", cart ));
//            orderList.add(new Order( "02/06/2022", "12:00", "00:38", "05/06/2022", "thankyou", "Dean", "1", "bla","Store 1", cart ));
//            orderList.add(new Order( "01/06/2022", "12:00", "12:00", "05/06/2022", "thankyou", "Dean", "1", "bla","Store 1", cart ));
        }
        {
            complaintList.add(new ComplaintClient(orderList.get(0), "bad flowers","02/06/2022", "12:00"));
            complaintList.add(new ComplaintClient(orderList.get(1), "bad flowers","02/06/2022", "13:00"));
            complaintList.add(new ComplaintClient(orderList.get(2), "bad flowers","02/06/2022", "13:30"));
            complaintList.add(new ComplaintClient(orderList.get(3), "bad flowers","03/06/2022", "18:00"));
            complaintList.add(new ComplaintClient(orderList.get(4), "bad flowers","03/06/2022", "17:30"));
            complaintList.add(new ComplaintClient(orderList.get(5), "bad flowers","07/06/2022", "01:00"));
        }
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

    public ObservableList<OrderClient> getOrderList() {
        return orderList;
    }

    public void setOrderList(ObservableList<OrderClient> orderList) {
        this.orderList = orderList;
    }

    public void setOrderList(List<Order> orders){
        for(int i=0; i<orders.size(); i++){
            orderList.add(new OrderClient(orders.get(i)));
        }
    }

    public ObservableList<ComplaintClient> getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(ObservableList<ComplaintClient> complaintList) {
        this.complaintList = complaintList;
    }

    public void setComplaintList(List<Complain> complaints){
        for(int i=0; i<complaints.size(); i++){
            complaintList.add(new ComplaintClient(complaints.get(i)));
        }
    }

    public int getId() {
        return id;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIdentifyNumbers() {
        return identifyNumbers;
    }

    public void setIdentifyNumbers(String identifyNumbers) {
        this.identifyNumbers = identifyNumbers;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public List<Store> getStoresOfStore() {
        return storesOfStore;
    }

    public void setStoresOfStore(List<Store> storesOfStore) {
        this.storesOfStore = storesOfStore;
    }

    public OrderClient getOrderById(int id){
        OrderClient order;
        for(int i=0; i< orderList.size(); i++){
            order = orderList.get(i);
            if(order.getThis_id() == id){
                return order;
            }
        }
        return null;
    }

    public ComplaintClient getComplaintById(int id){
        ComplaintClient complaint;
        for(int i=0; i< complaintList.size(); i++){
            complaint = complaintList.get(i);
            if(complaint.getThis_id() == id){
                return complaint;
            }
        }
        return null;
    }

    public void removeComplaintById(int id){
        ComplaintClient complaint;
        for(int i=0; i< complaintList.size(); i++){
            complaint = complaintList.get(i);
            if(complaint.getThis_id() == id){
                complaintList.remove(i);
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public LinkedList<Integer> getStores() {
        return stores;
    }

    public void setStores(LinkedList<Integer> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "UserClient{" +
                "priority=" + priority +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", credit=" + credit +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", identifyNumbers='" + identifyNumbers + '\'' +
                ", discount=" + discount +
                ", isFrozen=" + isFrozen +
                ", isLogin=" + isLogin +
                ", isWorker=" + isWorker +
                ", complaintList=" + complaintList +
                ", orderList=" + orderList +
                ", UserServer=" + UserServer +
                '}';
    }
}
