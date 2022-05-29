package il.client;

import il.client.DiffClasses.Complaint;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private Order order;

    private Complaint complaint;

    private MyAccountController my_account_page_holder;

    private Stage stage;

    private int clicks_refund = 0;

    @FXML
    void initialize(Complaint complaint, Stage stage){
        this.stage = stage;
        this.setComplaint(complaint);
        this.compalin_id_textarea.setText(Integer.toString(complaint.getThis_id()));
        this.customer_name_textarea.setText(UserClient.getInstance().getName());
        System.out.println("compalint "+this.complaint.getComplaint());
        this.customer_complain_textarea.setText(this.complaint.getComplaint());
        String time = this.complaint.getComplaintTime();
        String[] split_time = time.split(":");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String[] date_time = dtf.format(now).split(" ");
        System.out.println(date_time[0] +" "+date_time[1]);
        System.out.println("complaint hour "+ split_time[0]);
        System.out.println("complaint minutes "+split_time[1]);
        int hours = 24 - Integer.valueOf(split_time[0]);
        this.hours_field.setText(Integer.toString(hours));
        int minutes = 60 - Integer.valueOf(split_time[1]);
        this.minutes_field.setText(Integer.toString(minutes));
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
    void SubmitBTNClicked(ActionEvent event) {
        this.complain_handler_ancorpane3.setVisible(true);
        this.complain_handler_ancorpane2.setVisible(false);
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
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

    /*end sets and gets*/
}
