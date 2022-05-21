package il.client;


import il.entities.Flower;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class UpdateController {

    @FXML
    private Button submit_button;

    @FXML
    private TextField update_text;

    private String new_price;

    private ProductView product;

    private Stage stage;




    @FXML
    void Submit_Button_Clicked(MouseEvent event) throws IOException, ClassNotFoundException, InterruptedException, JSONException {
        this.new_price = update_text.getText();
        product.setProduct_price(this.new_price);
        stage.close();

        CatalogControl.setPrice(product.getId(), product.getProduct_price());
    }



    public void getProductView(ProductView to_change, Stage stage){
        this.product = to_change;
        this.stage = stage;
    }

}
