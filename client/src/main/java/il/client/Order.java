package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.IOException;

public class Order {
    private static int id = 0;
    private int this_id;
    private String orderDate;
    private String orderTime;
    private String orderReceiveTime;
    private String orderReceiveDate;
    private String greeting;
    private String nameReceiver;
    private String phoneReceiver;
    private String Address;
    private String storeChoosen;

    @FXML
    private MFXButton complaint;

    @FXML
    private MFXButton cancel;

    private ObservableList<CartItem> order_items = FXCollections.observableArrayList();

    public Order(String orderDate, String orderTime, String orderReceiveTime, String orderReceiveDate, String greeting, String nameReceiver, String phoneReceiver, String address, String store, ObservableList<CartItem> order_items) {
        this.this_id = id++;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderReceiveTime = orderReceiveTime;
        this.orderReceiveDate = orderReceiveDate;
        this.greeting = greeting;
        this.nameReceiver = nameReceiver;
        this.phoneReceiver = phoneReceiver;
        this.Address = address;
        this.order_items = order_items;
        this.complaint = new MFXButton("Add Complaint");
        this.cancel = new MFXButton("Cancel Order");
        this.storeChoosen = store;
        buttonsInitilize();
    }

    public void buttonsInitilize(){
        complaint.setStyle("-fx-background-color: transparent;" +
                "    -fx-border-color: -mfx-purple;" +
                "    -fx-border-radius: 3;" +
                "    -fx-text-fill: -mfx-purple;");
        cancel.setStyle("-fx-background-color: transparent;" +
                "    -fx-border-color: -mfx-purple;" +
                "    -fx-border-radius: 3;" +
                "    -fx-text-fill: -mfx-purple;");


        complaint.setOnAction(event -> {
            try {
                MyAccountController.getInstance().LoadComplaintPage();
                ComplainController.getInstance().setOrder_number_field(Integer.toString(this_id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cancel.setOnAction(event -> {
            try {
                MyAccountController.getInstance().LoadCancelOrderPage();
                CancelOrderController.getInstance().setOrder_num_filed(Integer.toString(this_id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    /* gets and sets*/

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Order.id = id;
    }

    public int getThis_id() {
        return this_id;
    }

    public void setThis_id(int this_id) {
        this.this_id = this_id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderReceiveTime() {
        return orderReceiveTime;
    }

    public void setOrderReceiveTime(String orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    public String getOrderReceiveDate() {
        return orderReceiveDate;
    }

    public void setOrderReceiveDate(String orderReceiveDate) {
        this.orderReceiveDate = orderReceiveDate;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public String getPhoneReceiver() {
        return phoneReceiver;
    }

    public void setPhoneReceiver(String phoneReceiver) {
        this.phoneReceiver = phoneReceiver;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public ObservableList<CartItem> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(ObservableList<CartItem> order_items) {
        this.order_items = order_items;
    }

    public MFXButton getComplaint() {
        return complaint;
    }

    public void setComplaint(MFXButton complain) {
        this.complaint = complain;
    }

    public MFXButton getCancel() {
        return cancel;
    }

    public void setCancel(MFXButton cancel) {
        this.cancel = cancel;
    }

    public String getStoreChoosen() {
        return storeChoosen;
    }

    public void setStoreChoosen(String storeChoosen) {
        this.storeChoosen = storeChoosen;
    }
    /* end gets and sets*/

    @Override
    public String toString() {
        return "Order{" +
                "this_id=" + this_id +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderReceiveTime='" + orderReceiveTime + '\'' +
                ", orderReceiveDate='" + orderReceiveDate + '\'' +
                ", greeting='" + greeting + '\'' +
                ", nameReceiver='" + nameReceiver + '\'' +
                ", phoneReceiver='" + phoneReceiver + '\'' +
                ", Address='" + Address + '\'' +
                ", order_items=" + order_items +
                '}';
    }
}
