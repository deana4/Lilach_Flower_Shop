/**
 * Sample Skeleton for 'PopWindow.fxml' Controller Class
 */

package il.client;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PopWindow extends ParentClass{

    @FXML
    private Label id_txt;

    @FXML
    private Label name_txt;

    @FXML
    private AnchorPane poped_window;

    @FXML
    private Label price_txt;

    @FXML
    private MFXCheckbox product_on_stock;

    @FXML // fx:id="product_zoomed_image"
    private ImageView product_zoomed_image; // Value injected by FXMLLoader

    @FXML
    private ImageView discount_logo_poped;

    private ProductView PVController;

    private Stage stage;

    private boolean on_discount;

    @FXML
    private Label typeText;

    @FXML
    private Label colorText;


    @FXML
    void CheckBoxClicked(MouseEvent event) {

    }

    @FXML
    void FullSetter(int id, String name, String price, boolean on_discount, Image image, String color, String type){
        this.id_txt.setText(Integer.toString(id));
        this.price_txt.setText(price);
        this.name_txt.setText(name);
        this.on_discount = on_discount;
        this.colorText.setText(color);
        this.typeText.setText(type);
        this.product_zoomed_image.setImage(image);
        if(on_discount){
            System.out.println(on_discount);
            this.discount_logo_poped.setVisible(true);
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void FullSetter(int id, String name, String price, boolean on_discount, Image image){
        this.id_txt.setText(Integer.toString(id));
        this.price_txt.setText(price);
        this.name_txt.setText(name);
        this.on_discount = on_discount;
        this.product_zoomed_image.setImage(image);
        if(on_discount){
            this.discount_logo_poped.setVisible(true);
        }
    }

    @FXML
    void initialize(Stage incomeStage, ProductView incomeController){
        this.stage = incomeStage;
        this.PVController = incomeController;
        product_on_stock.setSelected(true);
//        product_on_stock.setDisable(true);
    }

    /** SETTERS AND GETTERS**/
    public ProductView getPVController() {
        return PVController;
    }

    public void setPVController(ProductView PVController) {
        this.PVController = PVController;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /*END OF THEM*/

}
