package il.client;

import il.entities.Flower;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class CatalogController {

    @FXML
    private AnchorPane side_pic_anchorpane;

    @FXML
    private GridPane gridPane;

    @FXML // fx:id="grid"
    private ScrollPane scrollPane; // Value injected by FXMLLoader


    @FXML
    private BorderPane mainBorder;

    @FXML
    private AnchorPane catalog_main_anchorpane;

    private static List<Flower> flowerlist=null;

    public static List<Flower> getFlowerlist() {
        return flowerlist;
    }

    public static void setFlowerlist(List<Flower> flowerlist1) {
        flowerlist = flowerlist1;
    }



    @FXML  // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
        CatalogControl.getItemsList();


        //runLater
        int col = 0;
        int row = 0;

        for(int i=0; i<flowerlist.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ProductView.fxml"));//change secondary.fxml to the fxml file from dean and liran
            AnchorPane panel = fxmlLoader.load();

            ProductView controller = fxmlLoader.getController();
            controller.setData(flowerlist.get(i));

            if(col==3){
                col=0;
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


    public AnchorPane getSide_pic_anchorpane() {
        return side_pic_anchorpane;
    }

    public AnchorPane getCatalog_main_anchorpane() {
        return catalog_main_anchorpane;
    }
}
