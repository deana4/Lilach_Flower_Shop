package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AddProductToCatalogController{

    @FXML
    private MFXButton addFlowerBtn;

    @FXML
    private ImageView product_image;

    @FXML
    void initialize(){
    }
    @FXML
    void AddFlowerToCatalog(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddProductToCatalogDialog.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(fxmlLoader.load(), 799, 414);
        scene.setFill(Color.TRANSPARENT);
        AddProductToCatalogDialogController controller = fxmlLoader.getController();
        controller.initialize();
        controller.setStage(stage);
        stage.setTitle("Add Item");
        stage.setScene(scene);
        stage.show();

    }

}
