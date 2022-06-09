package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AlreadyCanceledOrder {

    @FXML
    private AnchorPane cancel_order_anchorpane1;

    @FXML
    private AnchorPane cancel_order_anchorpane3;

    @FXML
    private MFXButton closeBTN;

    private Stage stage;

    @FXML
    void CloseBTNClicked(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void initialize(Stage stage){
        this.stage = stage;
    }

}
