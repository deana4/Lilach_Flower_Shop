package il.client;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddToCartController {

    Stage stage;

    @FXML
    void CloseATCWin(MouseEvent event) {
        this.stage.close(); //when clicking on text, window closing
    }
    void setStage(Stage stage){
        this.stage = stage;
    }
}
