package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class HomeController {

    private MainPageController main_page_holder;


    @FXML
    private MFXRectangleToggleNode r1;

    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }

    @FXML
    void initialize(){
        r1.setLabelLeadingIcon(MFXFontIcon.getRandomIcon(16, Color.BLACK));
        r1.setLabelTrailingIcon(MFXFontIcon.getRandomIcon(16, Color.BLACK));
    }
}