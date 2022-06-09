package il.client;

import il.client.controls.OrderControl;
import il.entities.Order;
import il.client.controls.OrderControl;
import il.entities.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CancelOrderController {

    @FXML
    private AnchorPane cancel_order_anchorpane1;

    @FXML
    private AnchorPane cancel_order_anchorpane2;

    @FXML
    private AnchorPane cancel_order_anchorpane3;

    @FXML
    private MFXButton noBTN;

    @FXML
    private MFXTextField order_num_filed;

    @FXML
    private MFXButton closeBTN;

    @FXML
    private Label refund_status_label;

    @FXML
    private MFXButton yesBTN;

    private MyAccountController my_account_page_holder;

    private double refund = 0.0;

    private Stage stage;

    private OrderClient this_order;

    private static CancelOrderController instance = null;

    public static CancelOrderController getInstance(){
        if(instance == null){
            System.out.println("Error Occured");
        }
        return instance;
    }

    @FXML
    void initialize(OrderClient order, Stage stage) throws ParseException {
        instance = this;
        this.stage = stage;
        this_order = order;
        this.order_num_filed.setText(Integer.toString(order.getThis_id()));
        setDetailsCancelOrder();

    }

    @FXML
    void NoBTNClicked(ActionEvent event) throws IOException {
//        MyAccountController.getInstance().CancelOrderRefresh();
//        MyAccountController.getInstance().LoadOrdersHistoryPage();
        this.stage.close();
    }

    @FXML
    void YesBTNClicked(ActionEvent event) throws IOException {
        OrderControl.cancelOrder(this.this_order.getThis_id(),this.refund); //doesnt deleting
        this.cancel_order_anchorpane2.setVisible(false);
        this.cancel_order_anchorpane3.setVisible(true);
        System.out.println(UserClient.getInstance().getCredit());
//        this.stage.close();
    }

    @FXML
    void CloseBTNClicked(ActionEvent event) throws IOException {
        MyAccountController.getInstance().CancelOrderRefresh();
        ObservableList<OrderClient> order_list = UserClient.getInstance().getOrderList();
        for(int i=0; i<order_list.size(); i++){
            if(Integer.parseInt(this.order_num_filed.getText()) == order_list.get(i).getThis_id()){
//                order_list.remove(i);
                order_list.get(i).setCanceled(true);
                break;
            }
        }
        UserClient.getInstance().setOrderList(order_list);

        MyAccountController.getInstance().LoadOrdersHistoryPage();

        OrderControl.cancelOrder(Integer.parseInt(this.order_num_filed.getText()),refund);

        System.out.println(Integer.parseInt(this.order_num_filed.getText()));
//        UserClient.getInstance().removeOrderById(Integer.parseInt(this.order_num_filed.getText()));
        UserClient.getInstance().cancelOrderById(Integer.parseInt(this.order_num_filed.getText()));
        UserClient.getInstance().addCredit(refund);
        MyAccountController.getInstance().setCreditTextField(UserClient.getInstance().getCredit());
        this.stage.close();
      //  CancelOrdersTabController.getInstance().RemoveOrderById(Integer.parseInt(this.order_num_filed.getText()));
    }

    public void setDetailsCancelOrder() throws ParseException {
        int order_num = Integer.parseInt(this.order_num_filed.getText());
        System.out.println(order_num +" CancelOrderController");
        OrderClient order = UserClient.getInstance().getOrderById(order_num);
        double order_sum = order.getSum();
        System.out.println("CancelOrderController "+order_sum);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
//        LocalDate receiveDate = LocalDate.parse(order.getOrderReceiveDate());
//        LocalTime receiveTime = LocalTime.parse(order.getOrderReceiveTime());

//        String dateAndTimeOrderReceiver = formatter.format(receiveDate) + formatter.format(receiveTime);
//        System.out.println(dateAndTimeOrderReceiver);
//        LocalDateTime dateTime = LocalDateTime.of(receiveDate,receiveTime);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String order_receiver_date = order.getOrderReceiveDate();
        String order_receiver_time = order.getOrderReceiveTime();

        String dateAndTimeOrderReceiver = order_receiver_date + " " + order_receiver_time;
        System.out.println(dateAndTimeOrderReceiver);
        Date dtOrderReceiver = sdf.parse(dateAndTimeOrderReceiver);
        System.out.println(dtOrderReceiver);

        Date cancelOrder = new Date();

        long difference_In_Time =  cancelOrder.getTime() - dtOrderReceiver.getTime();
        System.out.println(difference_In_Time);
        long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
        long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

        System.out.println("difference in minutes "+difference_In_Minutes);
        System.out.println("difference in hours " + difference_In_Hours);
        System.out.println("difference in days "+ difference_In_Days);
        System.out.println("difference in millisec " + difference_In_Time);

        if(difference_In_Days>=0 && difference_In_Hours >=3){
            this.refund_status_label.setText("100% from "+ Double.valueOf(order_sum)+ "--> "+Double.toString(order_sum));
            this.refund=order_sum;
        }
        if(difference_In_Days==0 && difference_In_Hours<3 && difference_In_Hours>=1){
            this.refund_status_label.setText("50% from "+ Double.valueOf(order_sum)+ "--> "+Double.toString(0.5*order_sum));
            this.refund=0.5*order_sum;
        }
        if(difference_In_Days==0 && difference_In_Hours<1 ){
            this.refund_status_label.setText("0% from "+ Double.valueOf(order_sum)+ "--> "+Double.toString(0*order_sum));
            refund=0.0*order_sum;
        }
        if(difference_In_Days<0){
            this.refund_status_label.setText("You can't cancel this order! You get it");
            this.yesBTN.setDisable(true);
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }


    /* gets and sets*/

    public void setMy_account_page_holder(MyAccountController my_account_page_holder) {
        this.my_account_page_holder = my_account_page_holder;
    }

    public MFXTextField getOrder_num_filed() {
        return order_num_filed;
    }

    public void setOrder_num_filed(String order_num_filed) {
        this.order_num_filed.setText(order_num_filed);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /* end gets and sets*/
}
