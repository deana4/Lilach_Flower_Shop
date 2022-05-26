package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
    private MFXComboBox<?> problem_chooser;

    @FXML
    private MFXButton sendBTN;

    private MainPageController main_page_holder;

    @FXML
    void initialize(){
        { //initialize combobox
            this.complain_textarea.setPromptText("You can add more words here");
        }
    }

    @FXML
    void CancelBTNClicked(ActionEvent event) {

    }

    @FXML
    void ClearBTNClicked(ActionEvent event) throws IOException {
        this.complain_textarea.setText("");
    }

    @FXML
    void CloseComplainBTNClicked(ActionEvent event) {

    }

    @FXML
    void SendBTNClicked(ActionEvent event) {

    }

    /*set ang get*/

    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }

    /* end set and get */
}
