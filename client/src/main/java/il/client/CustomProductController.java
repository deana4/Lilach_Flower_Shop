package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.awt.event.MouseEvent;

public class CustomProductController {

    @FXML
    private MFXToggleButton active_color_chooser;

    @FXML
    private MFXButton addToCartBTN;

    @FXML
    private MFXButton cancelBTN;

    @FXML
    private ColorPicker dominante_color_chooser;

    @FXML
    private GridPane grid_pane;

    @FXML
    private MFXButton iAmDoneBTN;

    @FXML
    private MFXSlider max_price_slider;

    @FXML
    private MFXSlider min_price_slider;

    @FXML
    private Label no_items_label;

    @FXML
    private MFXScrollPane scroll_pane;

    @FXML
    private AnchorPane side_pick_ancorepane;

    @FXML
    private Label sum_label;

    @FXML
    private MFXComboBox<String> type_chooser;

    @FXML
    void AddToCartBTNClicked(ActionEvent event) {

    }

    @FXML
    void CancelBTNClicked(ActionEvent event) {

    }

    @FXML
    void IAmDoneBTNClicked(ActionEvent event) {

    }

    @FXML
    void ActiveColorClicked(MouseEvent event) {

    }

}
