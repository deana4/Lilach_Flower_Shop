package il.client;

import il.client.ProductView;
import il.client.SimpleClient;
import il.entities.Flower;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogController extends ParentClass{

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

    private FXMLLoader my_fxml_loader;

    private static List<Flower> flowerlist=null;

    private MainPageController main_page_holder;

    public static List<Flower> getFlowerlist() {
        return flowerlist;
    }

    public static void setFlowerlist(List<Flower> flowerlist1) {
        flowerlist = flowerlist1;
    }

    private void createf() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
        JSONObject cmd = new JSONObject();
        cmd.put("command", "getCatalogItems");
        SimpleClient.getClient().sendToServer(cmd.toString());
        TimeUnit.MILLISECONDS.sleep(200);//need to wait to the server, need to use lock
    }

    @FXML  // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
        //get connection to the server

        CatalogControl.getItemsList();

        int col = 0;
        int row = 0;

        URL path = getClass().getResource("ProductView.fxml");


        for(int i=0; i<flowerlist.size();i++){
            my_fxml_loader = new FXMLLoader();
            my_fxml_loader.setLocation(path);//change secondary.fxml to the fxml file from dean and liran
            Node node = my_fxml_loader.load();

            ProductView controller = my_fxml_loader.getController();
            controller.setCat_controller(this);
            controller.setData(flowerlist.get(i));

            if(col==2){
                col=0;
                row++;
            }
//            scrollPane.setContent(node);
            GridPane.setConstraints(node,row,col++);
//            GridPane.setColumnIndex(node,i);
//            GridPane.setRowIndex();
            gridPane.getChildren().addAll(node);

//            gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
//            gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
//            gridPane.setMaxWidth(Region.USE_COMPUTED_SIZE);
//            gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
//            gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
//            gridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);

//            GridPane.setMargin(node, new Insets(10));
        }
        scrollPane.setContent(this.gridPane);
    }


    public AnchorPane getSide_pic_anchorpane() {
        return side_pic_anchorpane;
    }

    public void setSide_pic_anchorpane(Parent side_pic) {
        this.side_pic_anchorpane.getChildren().clear();
        this.side_pic_anchorpane.getChildren().addAll(side_pic);
        this.side_pic_anchorpane.setVisible(true);
    }

    public AnchorPane getCatalog_main_anchorpane() {
        return catalog_main_anchorpane;
    }

    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }
}
