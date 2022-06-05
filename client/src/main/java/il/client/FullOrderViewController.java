package il.client;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.font.MFXFontIcon;
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
    void initialize(ComplainHandlerController controller){
        //get from server the relevant details of the order the client complaint about
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
