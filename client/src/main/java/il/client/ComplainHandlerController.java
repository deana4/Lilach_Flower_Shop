package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ComplainHandlerController {

    @FXML
    private TextArea answer_textarea;

    @FXML
    private MFXButton cancelBTN;

    @FXML
    private MFXButton closeBTN;

    @FXML
    private MFXTextField compain_number_filed;

    @FXML
    private AnchorPane complain_handler_ancorpane1;

    @FXML
    private AnchorPane complain_handler_ancorpane2;

    @FXML
    private AnchorPane complain_handler_ancorpane3;

    @FXML
    private TextArea customer_complain_textarea;

    @FXML
    private MFXTextField customer_name_field;

    @FXML
    private MFXTextField hours_field;

    @FXML
    private MFXTextField minutes_field;

    @FXML
    private MFXToggleButton refund_chooser;

    @FXML
    private MFXTextField refund_filed;

    @FXML
    private MFXButton submitBTN;

    @FXML
    void CloseBTNClicked(ActionEvent event) {

    }

    @FXML
    void CancelBTNClicked(ActionEvent event) {

    }

    @FXML
    void RefundClicked(MouseEvent event) {

    }

    @FXML
    void SubmitBTNClicked(ActionEvent event) {

    }

}
