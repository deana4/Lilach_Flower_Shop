package il.client;

import il.client.controls.ComplainConrtol;
import il.entities.Complain;
import il.entities.Order;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComplainController {

    @FXML
    private MFXButton cancelBTN;

    @FXML
    private MFXButton clearBTN;

    @FXML
    private MFXButton closeComplainBTN;

    @FXML
    private AnchorPane compain_ancorepane1;

    @FXML
    private AnchorPane complain_ancorpane2;

    @FXML
    private AnchorPane complain_ancorpane3;

    @FXML
    private TextArea complain_textarea;

    @FXML
    private MFXTextField order_number_field;

    @FXML
    private MFXButton sendBTN;

    private MainPageController main_page_holder;

    private MyAccountController my_account_page_holder;

    private static ComplainController instance = null;

    public static ComplainController getInstance(){
        if(instance == null){
            System.out.println("Error Occured");
        }
        return instance;
    }

    private Stage stage;

    @FXML
    void initialize(OrderClient order, Stage stage){
        {
            instance = this;
            this.complain_textarea.setPromptText("You can add more words here");
            this.stage = stage;
            this.order_number_field.setText(Integer.toString(order.getThis_id()));
        }
    }

    @FXML
    void CancelBTNClicked(ActionEvent event) throws IOException {
        MyAccountController.getInstance().ComplainRefresh();
//        MyAccountController.getInstance().LoadOrdersHistoryPage();
        this.stage.close();

    }

    @FXML
    void ClearBTNClicked(ActionEvent event) throws IOException {
        this.complain_textarea.setText("");
    }

    @FXML
    void CloseComplainBTNClicked(ActionEvent event) throws IOException {
        MyAccountController.getInstance().ComplainRefresh();
//        MyAccountController.getInstance().LoadOrdersHistoryPage();
        this.stage.close();

    }

    @FXML
    void SendBTNClicked(ActionEvent event) throws IOException {
        String complaint_text = complain_textarea.getText();
        //send complaint to server!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date complaintDate = new Date();
        String strDate = sdf.format(complaintDate);
        String[] date_time = strDate.split(" ");

        Complain new_complaint = new Complain(complaint_text, date_time[0], date_time[1]); //send complaint to server
        ComplainConrtol.newComplain(new_complaint, Integer.parseInt(this.order_number_field.getText()));
        complain_ancorpane2.setVisible(false);
        complain_ancorpane3.setVisible(true);
  //      ComplaintOrdersTabController.getInstance().RemoveOrderById(Integer.parseInt(this.order_number_field.getText()));

    }

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }

    /*set ang get*/

    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }

    public void setMy_account_page_holder(MyAccountController my_account_page_holder) {
        this.my_account_page_holder = my_account_page_holder;
    }

    public MFXTextField getOrder_number_field() {
        return order_number_field;
    }

    public void setOrder_number_field(String order_number_field) {
        this.order_number_field.setText(order_number_field);
    }

    /* end set and get */
}
