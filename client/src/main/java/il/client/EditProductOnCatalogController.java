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

import java.io.*;
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
    private MFXTextField typeText;

    @FXML
    private MFXTextField colorText;

    @FXML
    private MFXButton setChanges;

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
    void initialize(ProductView controller){
        detailChecker = new DetailsChecker();
        listFiles = new ArrayList<>();
        listFiles.add("*.png");
        listFiles.add("*.jpg");
        listFiles.add("*.jpeg");
        this.PVController = controller;
        this.nameText.setText(PVController.getProduct_name());
        this.PriceText.setText(Double.toString(PVController.getProduct_price()));
        this.productImage.setImage(PVController.getProductImageNotURL());
        this.colorText.setText(PVController.getColor());
        this.typeText.setText(PVController.getType());
        this.saleToggle.selectedProperty().set(PVController.isOn_discount());
        this.discountPercentText.setText(Double.toString(PVController.getDiscound_precentage()));
        {
            if(saleToggle.isSelected()){
                this.discountPercentText.setText(String.valueOf((PVController.getDiscound_precentage())));
                this.discountPercentText.setDisable(false);
            }else if(!saleToggle.isSelected()){
                this.discountPercentText.setText(String.valueOf(0.0));
                this.discountPercentText.setDisable(true);
            }
        }
    }
    @FXML
    void ChangesApplied(ActionEvent event) throws IOException {
        int ErrorCollector = 0;         //collecting errors throw value checks
        int retValue;
        CatalogControl.setName(this.nameText.getText(), PVController.getId());



        retValue = detailChecker.CheckPriceValues(this.PriceText.getText(),PriceText);
        if(retValue == 1){
            CatalogControl.setPrice(PVController.getId(), Double.valueOf(this.PriceText.getText()));

        }else if(retValue == 0){
            ErrorCollector+=1;
            PriceText.setText("Error");
        }


        retValue = detailChecker.CheckPriceValues(this.discountPercentText.getText(), discountPercentText);
        if(retValue == 1){
            //        CatalogControl.setDiscountPercentage(PVController.getId(), Double.valueOf(this.discountPercent.getText()));
        }else if(retValue == 0){
            ErrorCollector+=1;
            discountPercentText.setText("Error");
        }


        CatalogControl.updateImage(this.ImageURL.getText(), PVController.getId());

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
