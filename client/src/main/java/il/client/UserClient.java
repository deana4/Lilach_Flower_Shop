package il.client;

import il.client.DiffClasses.Complaint;
import il.entities.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    private ObservableList<Complaint> complaintList  = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();

    private User UserServer = null;

    private static UserClient user = null;

    private UserClient() {
        priority = new Priority();
        priority.setPriority_level(1);
        user = this;
        {
            ObservableList<CartItem> cart = FXCollections.observableArrayList();
            cart.add(new CartItem("sunflower", "150", 2, 2));
            cart.add(new CartItem("rose", "10", 2, 2));
//            MFXButton complaint_btn = new MFXButton();
//            complaint_btn.setText("Add Complaint");
            //for trying examples
            for(int i=0; i<20; i++)
            orderList.add(new Order( "Sunday"+i, "12:0"+i, "13:0"+i, "Sunday"+i, "thankyou"+i, "Dean"+i, "1"+i, "bla"+i, cart ));
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

    public ObservableList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ObservableList<Order> orderList) {
        this.orderList = orderList;
    }
}
