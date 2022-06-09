package il.client;

import il.client.DiffClasses.ComplaintClient;
import il.client.controls.ComplainConrtol;
import il.entities.Complain;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ComplainHandlerController {

    @FXML
    private TextArea answe_textarea;

    @FXML
    private MFXButton cancelBTN;

    @FXML
    private MFXButton closeBTN;

    @FXML
    private TextArea compalin_id_textarea;

    @FXML
    private AnchorPane complain_handler_ancorpane1;

    @FXML
    private AnchorPane complain_handler_ancorpane2;

    @FXML
    private AnchorPane complain_handler_ancorpane3;

    @FXML
    private TextArea customer_complain_textarea;

    @FXML
    private TextArea customer_name_textarea;

    @FXML
    private MFXTextField hours_field;

    @FXML
    private Label id_textarea;

    @FXML
    private MFXTextField minutes_field;

    @FXML
    private MFXToggleButton refund_chooser;

    @FXML
    private MFXTextField refund_filed;

    @FXML
    private MFXButton submitBTN;

    @FXML
    private MFXButton viewOrderBTN;

    private OrderClient order;

    private ComplaintClient complaint;

    private MyAccountController my_account_page_holder;

    private Stage stage;

    private int clicks_refund = 0;

    @FXML
    void initialize(ComplaintClient complaint, Stage stage) throws ParseException {
        this.stage = stage;
        this.setComplaint(complaint);
        this.compalin_id_textarea.setText(Integer.toString(complaint.getThis_id()));
        this.customer_name_textarea.setText(complaint.getOrder().getNameOrdering());
        System.out.println("complaint " + this.complaint.getComplaint());
        this.customer_complain_textarea.setText(this.complaint.getComplaint());


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String time = this.complaint.getComplaintTime();
        String date = this.complaint.getComplaintDate();
        String dateAndTimeComplaint = date + " " + time;
        Date dtComplaint = sdf.parse(dateAndTimeComplaint);

        Date handleComplaint = new Date();

        long difference_In_Time = handleComplaint.getTime() - dtComplaint.getTime();
        long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
        long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

        System.out.println("difference in minutes "+difference_In_Minutes);
        System.out.println("difference in hours " + difference_In_Hours);
        System.out.println("difference in days "+ difference_In_Days);
        System.out.println("difference in millisec " + difference_In_Time);

        if(difference_In_Days==0 && difference_In_Hours <= 24){
            this.hours_field.setText(Long.toString(24-difference_In_Hours));
            this.minutes_field.setText(Long.toString(60-difference_In_Minutes));
        }
        if(difference_In_Minutes!=0){
            this.hours_field.setText(Long.toString(23-difference_In_Hours));
            this.minutes_field.setText(Long.toString(60-difference_In_Minutes));
        }
        else{
            this.hours_field.setText("-");
            this.minutes_field.setText("-");
            this.answe_textarea.setText("Time to handle this complaint is up!");
            this.answe_textarea.setDisable(true);
            this.submitBTN.setDisable(true);
            this.refund_filed.setDisable(true);
            this.refund_chooser.setDisable(true);
        }
    }


    @FXML
    void CloseBTNClicked(ActionEvent event) {
        closeWindow(event);
    }

    @FXML
    void CancelBTNClicked(ActionEvent event) {
        closeWindow(event);
    }

    @FXML
    void RefundClicked(MouseEvent event) {
        clicks_refund++;
        if(clicks_refund%2==1) {
            this.refund_filed.setDisable(false);
        }
        else{
            this.refund_filed.setDisable(true);
        }
    }

    @FXML
    void SubmitBTNClicked(ActionEvent event) throws IOException {
        int flag = 1;
        if(this.answe_textarea.getText().equals("")||this.answe_textarea.getText().equals("Enter your answer")){
            this.answe_textarea.clear();
            this.answe_textarea.setPromptText("Enter your answer");
            flag=0;
        }
        //send complaint handle to server
        if(flag!=0) {
            double refund = 0.0;
            if (this.refund_chooser.isSelected()) {
                refund = Double.valueOf(this.refund_filed.getText());
            }

            ComplainConrtol.complainAnswer(this.answe_textarea.getText(), refund, Integer.parseInt(this.compalin_id_textarea.getText()));
            UserClient.getInstance().removeComplaintById(Integer.parseInt(this.compalin_id_textarea.getText()));
            this.complain_handler_ancorpane3.setVisible(true);
            this.complain_handler_ancorpane2.setVisible(false);
        }
        flag=1;
    }

    @FXML
    void ViewOrderBTNClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("FullOrderView.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(fxmlLoader.load(), 639, 371);
        scene.setFill(Color.TRANSPARENT);
        FullOrderViewController controller = fxmlLoader.getController();
        ComplaintClient this_compalint = UserClient.getInstance().getComplaintById(Integer.parseInt(this.compalin_id_textarea.getText()));
        OrderClient order_of_this_compalint = this_compalint.getOrder();
        controller.initialize(this,order_of_this_compalint);
        controller.setStage(stage);
        stage.setTitle("Full Order Details");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }

    /* gets and sets*/

    public TextArea getAnswe_textarea() {
        return answe_textarea;
    }

    public void setAnswe_textarea(TextArea answe_textarea) {
        this.answe_textarea = answe_textarea;
    }

    public MFXButton getCancelBTN() {
        return cancelBTN;
    }

    public void setCancelBTN(MFXButton cancelBTN) {
        this.cancelBTN = cancelBTN;
    }

    public MFXButton getCloseBTN() {
        return closeBTN;
    }

    public void setCloseBTN(MFXButton closeBTN) {
        this.closeBTN = closeBTN;
    }

    public TextArea getCompalin_id_textarea() {
        return compalin_id_textarea;
    }

    public void setCompalin_id_textarea(TextArea compalin_id_textarea) {
        this.compalin_id_textarea = compalin_id_textarea;
    }

    public AnchorPane getComplain_handler_ancorpane1() {
        return complain_handler_ancorpane1;
    }

    public void setComplain_handler_ancorpane1(AnchorPane complain_handler_ancorpane1) {
        this.complain_handler_ancorpane1 = complain_handler_ancorpane1;
    }

    public AnchorPane getComplain_handler_ancorpane2() {
        return complain_handler_ancorpane2;
    }

    public void setComplain_handler_ancorpane2(AnchorPane complain_handler_ancorpane2) {
        this.complain_handler_ancorpane2 = complain_handler_ancorpane2;
    }

    public AnchorPane getComplain_handler_ancorpane3() {
        return complain_handler_ancorpane3;
    }

    public void setComplain_handler_ancorpane3(AnchorPane complain_handler_ancorpane3) {
        this.complain_handler_ancorpane3 = complain_handler_ancorpane3;
    }

    public TextArea getCustomer_complain_textarea() {
        return customer_complain_textarea;
    }

    public void setCustomer_complain_textarea(TextArea customer_complain_textarea) {
        this.customer_complain_textarea = customer_complain_textarea;
    }

    public MFXTextField getHours_field() {
        return hours_field;
    }

    public void setHours_field(MFXTextField hours_field) {
        this.hours_field = hours_field;
    }

    public Label getId_textarea() {
        return id_textarea;
    }

    public void setId_textarea(Label id_textarea) {
        this.id_textarea = id_textarea;
    }

    public MFXTextField getMinutes_field() {
        return minutes_field;
    }

    public void setMinutes_field(MFXTextField minutes_field) {
        this.minutes_field = minutes_field;
    }

    public MFXToggleButton getRefund_chooser() {
        return refund_chooser;
    }

    public void setRefund_chooser(MFXToggleButton refund_chooser) {
        this.refund_chooser = refund_chooser;
    }

    public MFXTextField getRefund_filed() {
        return refund_filed;
    }

    public void setRefund_filed(MFXTextField refund_filed) {
        this.refund_filed = refund_filed;
    }

    public MFXButton getSubmitBTN() {
        return submitBTN;
    }

    public void setSubmitBTN(MFXButton submitBTN) {
        this.submitBTN = submitBTN;
    }

    public OrderClient getOrder() {
        return order;
    }

    public void setOrder(OrderClient order) {
        this.order = order;
    }

    public ComplaintClient getComplaint() {
        return complaint;
    }

    public void setComplaint(ComplaintClient complaint) {
        this.complaint = complaint;
    }

    public MyAccountController getMy_account_page_holder() {
        return my_account_page_holder;
    }

    public void setMy_account_page_holder(MyAccountController my_account_page_holder) {
        this.my_account_page_holder = my_account_page_holder;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getCustomer_name_textarea() {
        return customer_name_textarea.getText();
    }

    public void setCustomer_name_textarea(String customer_name_textarea) {
        this.customer_name_textarea.setText(customer_name_textarea);
    }

    /*end sets and gets*/
}
