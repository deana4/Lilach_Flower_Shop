package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AddCustomProductToCatalogController {

    @FXML
    private MFXButton addFlowerBtn;

    @FXML
    private ImageView product_image;

    @FXML
    void CustomProductClicked(ActionEvent event) throws IOException {
        MainPageController.getInstance().AddCustomProductPage();
    }

}
