/**
 * Sample Skeleton for 'PopWindow.fxml' Controller Class
 */

package il.client;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    private boolean on_discount;


    @FXML
    void CheckBoxClicked(MouseEvent event) {

    }


    @FXML
    void FullSetter(String id, String name, String price, boolean on_discount, Image image){
        this.id_txt.setText(id);
        this.price_txt.setText(price);
        this.name_txt.setText(name);
        this.on_discount = on_discount;
        this.product_zoomed_image.setImage(image);
        if(on_discount){
            this.discount_logo_poped.setVisible(true);
        }
    }

    @FXML
    void initialize(){
        product_on_stock.setSelected(true);
        product_on_stock.setDisable(true);
    }

}
