package il.client;

import il.client.controls.CatalogControl;
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
import java.util.ArrayList;

public class EditProductOnCatalogController {

    @FXML
    private MFXTextField ImageURL;

    @FXML
    private MFXTextField PriceText;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXTextField colorText;

    @FXML
    private MFXButton color_apply_btn;

    @FXML
    private MFXTextField discountPercentText;

    @FXML
    private MFXButton discount_apply_btn;

    @FXML
    private MFXButton fileBtn;

    @FXML
    private MFXButton image_apply_btn;

    @FXML
    private MFXTextField nameText;

    @FXML
    private MFXButton name_apply_btn;

    @FXML
    private MFXButton price_apply_btn;

    @FXML
    private ImageView productImage;

    @FXML
    private MFXToggleButton saleToggle;

    @FXML
    private MFXButton setChanges;

    @FXML
    private MFXTextField typeText;

    @FXML
    private MFXButton type_apply_btn;

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
//        CatalogControl.setName(this.nameText.getText(), PVController.getId()); //in NameBTNClicked



//        retValue = detailChecker.CheckPriceValues(this.PriceText.getText(),"price");
//        System.out.println(this.PriceText.getText()+ "EditProduct");
//        if(retValue == 1){
//            CatalogControl.setPrice(PVController.getId(), Double.valueOf(this.PriceText.getText()));
//
//        }else if(retValue == 0){
//            ErrorCollector+=1;
//            PriceText.setText("Error");
//        } //in PriceBTNClicked


        retValue = detailChecker.CheckPriceValues(this.discountPercentText.getText(),"percent");
        if(retValue == 1){
            //        CatalogControl.setDiscountPercentage(PVController.getId(), Double.valueOf(this.discountPercent.getText()));
        }else if(retValue == 0){
            ErrorCollector+=1;
            discountPercentText.setText("Error");
        }


//        CatalogControl.updateImage(this.ImageURL.getText(), PVController.getId()); //in ImageBTNClicked

//        if(ErrorCollector == 0){
//            MainPageController.getInstance().CatalogRefresh();
//            this.stage.close();
//        } //in closeWindow

        //Need to implement on Control//

        CatalogControl.setSale(this.saleToggle.isSelected(),PVController.getId(),Double.valueOf(discountPercentText.getText()));
//        CatalogControl.setColor(PVController.getId(), this.colorText.getText()); //in ColorBTNClicked
//        CatalogControl.setType(PVController.getId(), typeText.getText()); //in TypeBTNClicked
    }
    @FXML
    void closeWindow(ActionEvent event) throws IOException {
        MainPageController.getInstance().CatalogRefresh();
        MainPageController.getInstance().LoadCatalogPage();
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


    @FXML
    void ColorBTNClicked(ActionEvent event) throws IOException {
        String old_color = PVController.getColor();
        String new_color = this.colorText.getText();
        MainPageController.getInstance().removeColorFromSystem(old_color);
        MainPageController.getInstance().addColorToSystem(new_color);
        CatalogControl.setColor(PVController.getId(), this.colorText.getText());
        this.colorText.clear();
        this.colorText.setPromptText("Changed!");
    }

    @FXML
    void DiscountBTNClicked(ActionEvent event) throws IOException {
        int correctness;
        correctness = detailChecker.CheckPriceValues(this.discountPercentText.getText(),"percent");
        if(correctness == 0){
            this.discountPercentText.setPromptText("Error");
            return;
        }
            CatalogControl.setSale(this.saleToggle.isSelected(),PVController.getId(),Double.parseDouble(discountPercentText.getText()));
            this.discountPercentText.clear();
            this.discountPercentText.setPromptText("Changed!");

    }

    @FXML
    void ImageBTNClicked(ActionEvent event) throws IOException {
        CatalogControl.updateImage(this.ImageURL.getText(), PVController.getId());
        this.ImageURL.clear();
        this.ImageURL.setPromptText("Changed!");

//        FileChooser fc = new FileChooser();
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", listFiles));
//        File f = fc.showOpenDialog(null);
//
//        if(f != null){
//            this.ImageURL.setText(f.getAbsolutePath());
//        }
//
//        File file;
//        file = new File(this.ImageURL.getText());
//        byte[] bFile = new byte[(int) file.length()];
//        try{
//            FileInputStream fileInputStream = new FileInputStream(file);
//            fileInputStream.read(bFile);
//            fileInputStream.close();
//            Image img = new Image(new ByteArrayInputStream(bFile));
//            productImage.setImage(img);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        CatalogController.getInstance().setImageById(PVController.getId(),bFile);
    }

    @FXML
    void NameBTNClicked(ActionEvent event) throws IOException {
        CatalogControl.setName(this.nameText.getText(), PVController.getId());
        this.nameText.clear();
        this.nameText.setPromptText("Changed!");
        System.out.println("in EditProductOnCatalog in clicked on apply name");
    }

    @FXML
    void PriceBTNClicked(ActionEvent event) throws IOException {
        int correctness;
        correctness = detailChecker.CheckPriceValues(this.PriceText.getText(),"price");
        System.out.println(this.PriceText.getText()+ "EditProduct");
        if(correctness == 1){
            this.PriceText.setPromptText("Changed!");
            CatalogControl.setPrice(PVController.getId(), Double.parseDouble(this.PriceText.getText()));
            this.PriceText.clear();

        }else if(correctness == 0){
            PriceText.setPromptText("Error");
        }
    }

    @FXML
    void TypeBTNClicked(ActionEvent event) throws IOException {
        CatalogControl.setType(PVController.getId(), typeText.getText());
        this.typeText.clear();
        this.typeText.setPromptText("Changed!");
    }

    @FXML
    void editFinishClicked(ActionEvent event) throws IOException {
        MainPageController.getInstance().CatalogRefresh();
        MainPageController.getInstance().LoadCatalogPage();
        this.stage.close();
    }

    /*gets and sets*/

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /*end gets and sets*/


}
