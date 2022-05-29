package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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

    private static CancelOrderController instance = null;

    public static CancelOrderController getInstance(){
        if(instance == null){
            System.out.println("Error Occured");
        }
        return instance;
    }

    @FXML
    void initialize(){
        instance = this;
    }

    @FXML
    void NoBTNClicked(ActionEvent event) throws IOException {
//        MyAccountController.getInstance().CancelOrderRefresh();
        MyAccountController.getInstance().LoadOrdersHistoryPage();
    }

    @FXML
    void YesBTNClicked(ActionEvent event) throws IOException {
        //sent to server that the user want to cancel this order!!!!!!
        this.cancel_order_anchorpane2.setVisible(false);
        this.cancel_order_anchorpane3.setVisible(true);

    }

    @FXML
    void CloseBTNClicked(ActionEvent event) throws IOException {
        MyAccountController.getInstance().CancelOrderRefresh();
        MyAccountController.getInstance().LoadOrdersHistoryPage();
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

    /* end gets and sets*/
}
