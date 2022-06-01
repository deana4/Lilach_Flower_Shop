package il.client;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;

public class UpdateController extends ParentClass{

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
