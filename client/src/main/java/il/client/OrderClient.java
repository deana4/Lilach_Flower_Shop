package il.client;

import il.entities.Order;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.IOException;
import java.text.ParseException;

public class OrderClient {
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
    private double sum;
    private boolean isDelivery;
    private String nameOrdering;

    @FXML
    private MFXButton complaint;

    @FXML
    private MFXButton cancel;

    private ObservableList<CartItem> order_items = FXCollections.observableArrayList();

    public OrderClient(String orderDate, String orderTime, String orderReceiveTime, String orderReceiveDate, String greeting, String nameReceiver, String phoneReceiver, String address, String store, ObservableList<CartItem> order_items, boolean isDelivery, String nameOrdering) {
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
        this.isDelivery = isDelivery;
        this.nameOrdering = nameOrdering;
        for(int i=0; i<order_items.size(); i++){
            sum = sum + (order_items.get(i).getItem_price() * order_items.get(i).getItem_amount());
        }
        buttonsInitilize();
    }

    public OrderClient(Order order){
        this.this_id = order.getId();
        this.orderDate = order.getDateOrder();
        this.orderTime = order.getTimeOrder();
        this.sum = order.getSum();
        this.orderReceiveTime = order.getTimeReceive();
        this.orderReceiveDate = order.getDateReceive();
        this.greeting = order.getGreeting();
        this.nameReceiver = order.getNameReceives();
        this.phoneReceiver = order.getPhoneReceives();
        this.Address = order.getAddress();
        //this.nameOrdering = order.getUser().getName();
        for(int i=0; i<order.getProducts().size(); i++){
            this.order_items.add(new CartItem(order.getProducts().get(i)));
        }
      //  this.storeChoosen = order.getStore().getAddress();
      //  this.isDelivery = order.isDelivery();
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
                CancelOrderController.getInstance().setDetailsCancelOrder();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        });
    }
    /* gets and sets*/

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        OrderClient.id = id;
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

    public double getSum() { return sum; }

    public void setSum(double sum) { this.sum = sum; }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public String getNameOrdering() {
        return nameOrdering;
    }

    public void setNameOrdering(String nameOrdering) {
        this.nameOrdering = nameOrdering;
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
