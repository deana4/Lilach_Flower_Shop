package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.Extension;
import java.util.ArrayList;

public class EditProductOnCatalogController {

    @FXML
    private MFXTextField ImageURL;

    @FXML
    private MFXTextField PriceText;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXTextField nameText;

    @FXML
    private MFXButton setChanges;

    @FXML
    private MFXButton fileBtn;

    @FXML
    private ImageView productImage;

    private Stage stage;
    private ProductView PVController;
    ArrayList<String> listFiles;

    @FXML
    void initialize(ProductView controller){
        listFiles = new ArrayList<>();
        listFiles.add("*.png");
        listFiles.add("*.jpg");
        listFiles.add("*.jpeg");
        this.PVController = controller;
        this.nameText.setText(PVController.getProduct_name());
        if(PVController.getProduct_priceString().contains(".")){

        }
        this.PriceText.setText(Double.toString(PVController.getProduct_price()));
        this.productImage.setImage(PVController.getProductImageNotURL());
    }
    @FXML
    void ChangesApplied(ActionEvent event) throws IOException {
        CatalogControl.setName(this.nameText.getText(), PVController.getId());
        CatalogControl.setPrice(PVController.getId(), Double.valueOf(this.PriceText.getText()));
        CatalogControl.updateImage(this.ImageURL.getText(),PVController.getId());
        MainPageController.getInstance().CatalogRefresh();
        this.stage.close();
    }
    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }
    @FXML
    void getFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", listFiles));
        File f = fc.showOpenDialog(null);

        if(f != null){
            this.ImageURL.setText(f.getAbsolutePath());
        }

//        this.productImage.setImage(this.ImageURL);

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
