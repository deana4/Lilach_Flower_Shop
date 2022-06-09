package il.client;

import il.client.controls.CatalogControl;
import il.entities.Product;
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

    private DetailsChecker detailChecker;

    private Stage stage;
    private ProductView PVController;
    private ArrayList<String> listFiles;

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

        retValue = detailChecker.EmptyCheck(this.nameText.getText());
        if(retValue == 0){
            this.nameText.setPromptText("Empty");
            ErrorCollector += 1;
        }

        retValue = detailChecker.CheckPriceValues(this.PriceText.getText(), "price");
        if(retValue == 0){
            this.PriceText.setPromptText("Error");
            ErrorCollector += 1;
        }

        retValue = detailChecker.EmptyCheck(this.colorText.getText());
        if(retValue == 0){
            this.colorText.setPromptText("Empty");
            ErrorCollector += 1;
        }

        retValue = detailChecker.EmptyCheck(this.typeText.getText());
        if(retValue == 0){
            this.typeText.setPromptText("Empty");
            ErrorCollector += 1;
        }

        retValue = detailChecker.EmptyCheck(this.ImageURL.getText());
        if(retValue == 0){
            this.ImageURL.setPromptText("Empty");
            ErrorCollector += 1;
        }

        if(saleToggle.isSelected()){
            retValue = detailChecker.CheckPriceValues(this.discountPercentText.getText(), "percent");
            if(retValue == 0){
                this.discountPercentText.setPromptText("Empty");
                ErrorCollector += 1;
            }
        }


        if(ErrorCollector == 0){
            addFlowerToTheServer();
            MainPageController.getInstance().LoadCatalogFromZero();
            this.stage.close();
        }

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
            this.discountPercentText.setDisable(false);
        }else if(!saleToggle.isSelected()){
            this.discountPercentText.setText(String.valueOf(0.0));
            this.discountPercentText.setDisable(true);
        }
    }

    public void addFlowerToTheServer() throws IOException {
        double discountPercentage = 0;
        if(!this.saleToggle.isSelected()){
            discountPercentage = 0.0;
        }else if(this.saleToggle.isSelected()){
            discountPercentage = Double.valueOf(this.discountPercentText.getText());
        }
        Product product = new Product(this.nameText.getText(), Double.valueOf(this.PriceText.getText()), saleToggle.isSelected(),discountPercentage , this.typeText.getText(), this.colorText.getText());
        CatalogControl.addItem(product,this.ImageURL.getText());
        MainPageController.getInstance().addColorToSystem(this.colorText.getText());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
