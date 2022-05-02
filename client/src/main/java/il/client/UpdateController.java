package il.client;


import il.entities.Flower;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
    void Submit_Button_Clicked(MouseEvent event) throws IOException, ClassNotFoundException, InterruptedException {
        this.new_price = update_text.getText();
        product.setProduct_price(this.new_price);
        stage.close();
        String command = "#updatePrice ID:"+Integer.toString(product.getId())+","+"new price:"+this.new_price;
        Flower flower=new Flower(product.getProduct_name(), product.getProduct_price(), product.getProduct_image(), product.isOn_discount(),product.getDiscound_precentage());

        updateServerNewPrice(command);

    }

    public void getProductView(ProductView to_change, Stage stage){
        this.product = to_change;
        this.stage = stage;
    }

    private void updateServerNewPrice(String command) throws IOException, ClassNotFoundException, InterruptedException {
        SimpleClient.getClient().sendToServer(command);
        TimeUnit.SECONDS.sleep(3);//need to wait to the server, need to use lock
    }

}
