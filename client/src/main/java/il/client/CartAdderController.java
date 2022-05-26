package il.client;

import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.SpinnerValueFactory;

public class CartAdderController{

    @FXML
    private MFXRectangleToggleNode add_to_cart_toggle;

    @FXML
    private MFXSlider amount_chooser;

    private CartItem item;

    AddToCartController CartController;

    @FXML
    void initialize(){

    }

    @FXML
    void ToggleActivated(ActionEvent event) {
        int i=(int)amount_chooser.getValue();
        System.out.println(i);
    }

}
