package il.client;

/**
 * Sample Skeleton for 'catalog.fxml' Controller Class
 */

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import il.entities.Flower;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

public class CatalogController {
    private Image image;
    @FXML // fx:id="cart"
    private Button cart; // Value injected by FXMLLoader

    @FXML
    private MFXSlider Scroll;

    @FXML // fx:id="buttonEditcatalog"
    private Button buttonEditcatalog; // Value injected by FXMLLoader

    @FXML // fx:id="logolilah"
    private ImageView logolilah; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private MFXButton login_btn;

    @FXML
    private AnchorPane main_first_load_pane;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
//        File logoFile = new File("C:\\LilachProj\\client\\src\\main\\resources\\il\\client\\images\\logo.jpeg");
//        Image logoImg = new Image(logoFile.toURI().toString());
//        logolilah.setImage(logoImg);
    }



    @FXML
    void HomeBTNClicked(MouseEvent event) throws IOException {
        this.main_first_load_pane.getChildren().removeAll();
        FXMLLoader homeLoader = new FXMLLoader();
        URL home_var = getClass().getResource("Home.fxml");
        homeLoader.setLocation(home_var);
        Parent home_root = homeLoader.load();
        HomeController home_controller = homeLoader.getController();
        this.main_first_load_pane.getChildren().addAll(home_root);

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL pop_var = getClass().getResource("PopWindow.fxml");
        fxmlLoader.setLocation(pop_var);
        Parent pop_root = fxmlLoader.load();
        PopWindow controller = fxmlLoader.getController();
        File logoFile = new File("C:\\LilachProj\\server\\src\\main\\resources\\images\\Lotus.png");
        Image img = new Image(logoFile.toURI().toString());
        controller.FullSetter(1,"Hey", "Price", true,img);

        home_controller.getCatalog_main_anchorpane().getChildren().addAll(pop_root);
        home_controller.getCatalog_main_anchorpane().setVisible(true);
    }


    @FXML
    void LoginBTNClicked(ActionEvent event) throws IOException {
        this.main_first_load_pane.getChildren().removeAll();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL var = getClass().getResource("Login.fxml");
        fxmlLoader.setLocation(var);
        Parent root = fxmlLoader.load();
        this.main_first_load_pane.getChildren().addAll(root);
    }

}


