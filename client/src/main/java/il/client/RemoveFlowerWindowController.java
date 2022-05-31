package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class RemoveFlowerWindowController {

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXButton deleteProductBtn;

    private Stage stage;
//    private int flowerID;
    private ProductView flowerController;

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void deleteProductBtnClicked(ActionEvent event) throws IOException {
        flowerController.RemoveFlowerFromCatalog(flowerController.getId());
        this.stage.close();
    }

    /*sets and gets*/

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;

    }
    public void setFlowerController(ProductView controller){
        this.flowerController = controller;
    }

    /*end sets and gets*/

}
