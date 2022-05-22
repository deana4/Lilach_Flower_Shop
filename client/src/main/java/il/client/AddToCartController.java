package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AddToCartController {

    Stage stage;

    protected MainPageController main_page_holder;

    @FXML
    private MFXButton orderBTN;

    @FXML
    private MFXTextField sum_filed;

    @FXML
    void CloseATCWin(MouseEvent event) {
        this.stage.close(); //when clicking on text, window closing
    }
    void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void OrderBTNClicked(MouseEvent event) throws IOException {
        // main_page_holder.getMain_first_load_pane().getChildren().clear();
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        URL var = getClass().getResource("Order.fxml");
//        fxmlLoader.setLocation(var);
//        Parent root = fxmlLoader.load();
//        OrderController controller = fxmlLoader.getController();
//        controller.setCart_controller(this);

        this.sum_filed.setText("555");
        this.main_page_holder.LoadOrderPage();
        // main_page_holder.getMain_first_load_pane().getChildren().addAll(root);
    }

    public void LoadCart() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL var = getClass().getResource("AddToCart.fxml");
        fxmlLoader.setLocation(var);
        Parent root = fxmlLoader.load();
        OrderController controller = fxmlLoader.getController();
        controller.setCart_controller(this);
        controller.setSum_label(this.sum_filed.getText());
    }


    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }

    public MainPageController getMain_page_holder() {
        return main_page_holder;
    }

    public String getSum_filed() {
        return sum_filed.getText();
    }
}
