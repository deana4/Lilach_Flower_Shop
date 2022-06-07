package il.client;

import il.client.DiffClasses.ComplaintClient;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class FullOrderViewController {

    @FXML
    private MFXTextField DeliveryOrPickupText;

    @FXML
    private MFXTextField addressText;

    @FXML
    private MFXTextField amountText;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXTextField dateReceiverText;

    @FXML
    private MFXTextField isGreetingText;

    @FXML
    private MFXTextField receiverNameText;

    @FXML
    private MFXTextField storeText;

    @FXML
    private MFXTextField sumText1;

    @FXML
    private MFXTextField timeReceiverText;

    @FXML
    private MFXTextField userNmaeText;

    private Stage stage;

    private ComplainHandlerController complainHandlerController;

    @FXML
    void initialize(ComplainHandlerController controller,OrderClient order){
        if(order.isDelivery())
            this.DeliveryOrPickupText.setText("Delivery");
        else
            this.DeliveryOrPickupText.setText("PickUp");
        this.addressText.setText(order.getAddress());
        this.dateReceiverText.setText(order.getOrderReceiveDate());
        if(order.getGreeting()=="")
            this.isGreetingText.setText("false");
        else
            this.isGreetingText.setText("true");
        this.receiverNameText.setText(order.getNameReceiver());
        this.storeText.setText(order.getStoreChoosen());
        this.sumText1.setText(String.valueOf(order.getSum()));
        this.timeReceiverText.setText(order.getOrderReceiveTime());
        ComplaintClient complaint = controller.getComplaint();
        this.userNmaeText.setText(complaint.getOrder().getNameOrdering());
        int amount=0;
        ObservableList<CartItem> holder = order.getOrder_items();
        for(int i=0; i<holder.size();i++){
            amount = amount + holder.get(i).getItem_amount();
        }
        this.amountText.setText(Integer.toString(amount));


    }

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }

    /* gets and sets*/

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /* end gets and sets */

}
