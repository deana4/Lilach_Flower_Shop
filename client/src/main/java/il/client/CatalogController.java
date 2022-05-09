package il.client;

/**
 * Sample Skeleton for 'catalog.fxml' Controller Class
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import il.entities.Flower;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.json.JSONException;
import org.json.JSONObject;

public class CatalogController {
    private Image image;
    @FXML // fx:id="cart"
    private Button cart; // Value injected by FXMLLoader

    @FXML // fx:id="grid"
    private ScrollPane scrollPane; // Value injected by FXMLLoader


    @FXML // fx:id="buttonEditcatalog"
    private Button buttonEditcatalog; // Value injected by FXMLLoader

    @FXML // fx:id="logolilah"
    private ImageView logolilah; // Value injected by FXMLLoader

    @FXML
    private GridPane gridPane;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    private static List<Flower> flowerlist=null;

    public static List<Flower> getFlowerlist() {
        return flowerlist;
    }

    private void createf() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
        JSONObject cmd = new JSONObject();
        cmd.put("command", "getCatalogItems");
        SimpleClient.getClient().sendToServer(cmd.toString());
        TimeUnit.SECONDS.sleep(3);//need to wait to the server, need to use lock
    }

    public static void setFlowerlist(List<Flower> flowerlist1) {
         flowerlist = flowerlist1;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
        //get connection to the server
        this.SetLogo();
        createf();

        int col = 1;
        int row = 1;

        for(int i=0; i<flowerlist.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ProductView.fxml"));//change secondary.fxml to the fxml file from dean and liran
            AnchorPane panel = fxmlLoader.load();

            ProductView controller = fxmlLoader.getController();
            controller.setData(flowerlist.get(i));

            if(col==5){
                col=1;
                row++;
            }

            gridPane.add(panel, col++, row);

            gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
            gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
            gridPane.setMaxWidth(Region.USE_PREF_SIZE);
            gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
            gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
            gridPane.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(panel, new Insets(10));
        }
    }

    private void SetLogo(){ //loading the logo
        File logoFile = new File("C:\\LilachProj\\client\\src\\main\\resources\\il\\client\\images\\logo.jpeg");
        //"src/main/resources/il/client/images/logo.jpg"
        Image logoImg = new Image(logoFile.toURI().toString());
        logolilah.setImage(logoImg);
    }
}

