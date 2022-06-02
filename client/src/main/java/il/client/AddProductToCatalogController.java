package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AddProductToCatalogController{

    @FXML
    private MFXButton addFlowerBtn;

    @FXML
    private ImageView product_image;

    @FXML
    void initialize(){
        System.out.println("Im here");
    }
    @FXML
    void AddFlowerToCatalog(ActionEvent event) {

    }

}
