package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AddProductToCatalogDialogController {

    @FXML
    private MFXTextField ImageURL;

    @FXML
    private MFXTextField PriceText;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXTextField nameText;

    @FXML
    private MFXTextField typeText;

    @FXML
    private MFXTextField colorText;

    @FXML
    private MFXButton addItem;

    @FXML
    private MFXButton fileBtn;

    @FXML
    private ImageView productImage;

    @FXML
    private MFXToggleButton saleToggle;

    @FXML
    private MFXTextField discountPercentText;

    DetailsChecker detailChecker;

    private Stage stage;
    private ProductView PVController;
    ArrayList<String> listFiles;

    @FXML
    void initialize(){
        detailChecker = new DetailsChecker();
        listFiles = new ArrayList<>();
        listFiles.add("*.png");
        listFiles.add("*.jpg");
        listFiles.add("*.jpeg");
    }
    @FXML
    void AddItemClicked(ActionEvent event) throws IOException {
        int ErrorCollector = 0;         //collecting errors throw value checks
        int retValue;

        if(ErrorCollector == 0){
            MainPageController.getInstance().CatalogRefresh();
            this.stage.close();
        }

        //Need to implement on Control//

//        CatalogControl.setColor(this.colorText.getText(), PVController.getId());
//        CatalogControl.setType(PVController.getId(), typeText.getText());
//        CatalogControl.updateImage(this.ImageURL.getText(), PVController.getId());
//        CatalogControl.setOnDiscount(this.saleToggle.isSelected(), PVController.getId());

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

        File file;
        file = new File(this.ImageURL.getText());
        byte[] bFile = new byte[(int) file.length()];
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            Image img = new Image(new ByteArrayInputStream(bFile));
            productImage.setImage(img);
        }
        catch (Exception e){
            e.printStackTrace();
        }

//        this.productImage.setImage(this.ImageURL);

    }

    @FXML
    void ToggleClicked(MouseEvent event) { //setting the percentage toggle to respond with the text field.
        if(saleToggle.isSelected()){
            this.discountPercentText.setText(String.valueOf((PVController.getDiscound_precentage())));
            this.discountPercentText.setDisable(false);
        }else if(!saleToggle.isSelected()){
            this.discountPercentText.setText(String.valueOf(0.0));
            this.discountPercentText.setDisable(true);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
