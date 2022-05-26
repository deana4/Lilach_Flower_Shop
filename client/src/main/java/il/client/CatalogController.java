package il.client;

import il.client.events.CatalogItemsEvent;
import il.entities.Flower;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


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

    @Subscribe
    public void setFlowerlist(CatalogItemsEvent event) throws IOException {
        Platform.runLater(()->{
        flowerlist = event.getItems();
        int col = 0;
        int row = 0;

        URL path = getClass().getResource("ProductView.fxml");
        for (Flower flower : flowerlist) {
            my_fxml_loader = new FXMLLoader();
            my_fxml_loader.setLocation(path);//change secondary.fxml to the fxml file from dean and liran
            Node node = null;
            try {
                node = my_fxml_loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ProductView controller = my_fxml_loader.getController();
            controller.setCat_controller(this);
            try {
                controller.setData(flower);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (col == 2) {
                col = 0;
                row++;
            }
            GridPane.setConstraints(node, row, col++);
            gridPane.getChildren().addAll(node);
        }
        scrollPane.setContent(this.gridPane);
        });
    }

    @FXML  // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException, InterruptedException {
        EventBus.getDefault().register(this);
        CatalogControl.getItemsList();
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
