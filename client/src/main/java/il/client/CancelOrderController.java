package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CancelOrderController {

    @FXML
    private AnchorPane cancel_order_anchorpane1;

    @FXML
    private AnchorPane cancel_order_anchorpane2;

    @FXML
    private MFXButton noBTN;

    @FXML
    private MFXTextField order_num_filed;

    @FXML
    private Label refund_status_label;

    @FXML
    private MFXButton yesBTN;

    @FXML
    void NoBTNClicked(ActionEvent event) {

    }

    @FXML
    void YesBTNClicked(ActionEvent event) {

    }

}
