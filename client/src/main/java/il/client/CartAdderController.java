package il.client;

import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.SpinnerValueFactory;

public class CartAdderController{

    @FXML
    private MFXRectangleToggleNode add_to_cart_toggle;

    @FXML
    private MFXSpinner<Integer> quantity_selector;

    CartItem item;

    AddToCartController CartController;

    @FXML
    void initialize(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,200);
        valueFactory.setValue(1);

    }

    @FXML
    void ToggleActivated(ActionEvent event) {
        quantity_selector.nextIconSupplierProperty();
//        CartController.addItemToTable();
    }

}
