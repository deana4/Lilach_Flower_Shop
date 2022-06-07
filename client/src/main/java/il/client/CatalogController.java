package il.client;

import il.client.controls.*;
import il.client.controls.CatalogControl;
import il.client.events.CatalogItemsEvent;
import il.entities.Product;
import il.entities.Store;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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


    @FXML
    private AnchorPane catalog_anchorpane2;

    @FXML
    private GridPane gridPane2;


    @FXML
    private MFXScrollPane scrollPane2;


    @FXML
    private AnchorPane side_pic_anchorpane2;

    private FXMLLoader my_fxml_loader;

    private static List<Product> flowerlist=null;

    private MainPageController main_page_holder;

//    private LinkedList<ProductView> flowersFXML;
    private HashMap<Integer,ProductView> productsControllers;

    public static List<Product> getFlowerlist() {
        return flowerlist;
    }

    private static CatalogController catalogInstance = null;

    public static CatalogController getInstance(){
        if(catalogInstance == null) {
            catalogInstance = new CatalogController();
        }
        return catalogInstance;
    }

    public static void setFlowerlist(List<Product> flowerlist1) {
        flowerlist = flowerlist1;
    }

    @Subscribe
    public void setFlowerlist(CatalogItemsEvent event) throws IOException {
        Platform.runLater(()->{
                    flowerlist = event.getItems();
                    MainPageController.allStores = event.getStores();
                    for(int i=0; i<MainPageController.allStores.size(); i++){
                         RegisterController reg_controller = (RegisterController) MainPageController.getInstance().getControllerByKey("Register");
                         reg_controller.store_choose.getItems().add(MainPageController.allStores.get(i).getAddress());
                         OrderController order_controller = (OrderController) MainPageController.getInstance().getControllerByKey("Order");
                         order_controller.store_chooser.getItems().add(MainPageController.allStores.get(i).getAddress());
                 }

                    int col = 0;
                    int row = 0;

                    URL path = getClass().getResource("ProductView.fxml");

                    sortBySale(); //sorting the catalog items for making the items on sale - first

                    for(int i=0; i<flowerlist.size();i++){
                        my_fxml_loader = new FXMLLoader();
                        my_fxml_loader.setLocation(path);//change secondary.fxml to the fxml file from dean and liran
                        Node node = null;
                        try {
                            node = my_fxml_loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //     flowersFXML.add(node);
                        ProductView controller = my_fxml_loader.getController();
                        controller.setCat_controller(this);
                        try {
                            controller.setData(flowerlist.get(i));
                            productsControllers.put(i,controller);   //idiots
                            MainPageController.getInstance().addColorToSystem(flowerlist.get(i).getColor());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(col==3){
                            col=0;
                            row++;
                        }

                        GridPane.setConstraints(node,col++,row);
                        gridPane.getChildren().addAll(node);

                        gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                        gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        gridPane.setMaxWidth(Region.USE_COMPUTED_SIZE);
                        gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                        gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        gridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);

                        GridPane.setMargin(node, new Insets(10));
                    }

                    if(col==3){
                        col=0;
                        row++;
                    }

                    try {
                        Parent root_add_product = LoadAddProduct();
                        GridPane.setConstraints(root_add_product,0,row);
                        gridPane.getChildren().addAll(root_add_product);
                        GridPane.setMargin(root_add_product , new Insets(10));

                        Parent custom_add_product = LoadCustomProduct();
                        GridPane.setConstraints(custom_add_product,1,row);
                        gridPane.getChildren().addAll(custom_add_product);
                        GridPane.setMargin(custom_add_product , new Insets(10));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    scrollPane.setContent(this.gridPane);
                }
                );

    }


    @FXML  // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException, InterruptedException {
        catalogInstance = this;
        EventBus.getDefault().register(this);
        CatalogControl.getItemsList();
        this.productsControllers = new HashMap<>();
    }


    public void sortBySale(){
        List<Product> sortedListBySale = new LinkedList<Product>();
        for(int i=0; i<flowerlist.size(); i++){ //sort by discount
            Product tempProduct = flowerlist.get(i);
            if(tempProduct.isOn_discount()){
                sortedListBySale.add(tempProduct);
            }
        }
        for(int i=0; i<flowerlist.size(); i++){ //sort by discount
            Product tempProduct = flowerlist.get(i);
            if(!tempProduct.isOn_discount()){
                sortedListBySale.add(tempProduct);
            }
        }
        this.setFlowerlist(sortedListBySale);
    }




    public void setAnchorpang2Visibale(){
        this.catalog_anchorpane2.setVisible(true);
    }

    public void setAnchorpang2NotVisibale(){
        this.catalog_anchorpane2.setVisible(false);
    }

    public void setProductsAnchorpane2() throws IOException, ClassNotFoundException, InterruptedException {
        //get connection to the server

//        CatalogControl.getItemsList();

        int col = 0;
        int row = 0;

        URL path = getClass().getResource("ProductView.fxml");
        scrollPane2.setContent(null);
        gridPane2.getChildren().removeAll();
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

            GridPane.setConstraints(node,col++,row);
            gridPane2.getChildren().addAll(node);

            gridPane2.setMinWidth(Region.USE_COMPUTED_SIZE);
            gridPane2.setPrefWidth(Region.USE_COMPUTED_SIZE);
            gridPane2.setMaxWidth(Region.USE_COMPUTED_SIZE);
            gridPane2.setMinHeight(Region.USE_COMPUTED_SIZE);
            gridPane2.setPrefHeight(Region.USE_COMPUTED_SIZE);
            gridPane2.setMaxHeight(Region.USE_COMPUTED_SIZE);

            GridPane.setMargin(node, new Insets(10));
        }
        scrollPane2.setContent(this.gridPane2);
    }

    public AnchorPane getSide_pic_anchorpane() {
        return side_pic_anchorpane;
    }

    public void setSide_pic_anchorpane(Parent side_pic) {
        this.side_pic_anchorpane.getChildren().clear();
        this.side_pic_anchorpane.getChildren().addAll(side_pic);
        this.side_pic_anchorpane.setVisible(true);
    }

    public void setSide_pic_anchorpane2(Parent side_pic) {
        this.side_pic_anchorpane2.getChildren().clear();
        this.side_pic_anchorpane2.getChildren().addAll(side_pic);
        this.side_pic_anchorpane2.setVisible(true);
    }

    public AnchorPane getCatalog_main_anchorpane() {
        return catalog_main_anchorpane;
    }

    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }

    public HashMap<Integer, ProductView> getProductsControllers() {
        return productsControllers;
    }

    public void setProductsControllers(HashMap<Integer, ProductView> productsControllers) {
        this.productsControllers = productsControllers;
    }

//    public void setFlowersFXML(LinkedList<ProductView> flowersFXML) {
//        this.flowersFXML = flowersFXML;
//    }

    public void AddFlowerToCatalog(Product product) throws IOException {
        CatalogControl.addItem(product); // need to implement on Control
        MainPageController.getInstance().CatalogRefresh();
    }

    public Parent LoadAddProduct() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("AddProductToCatalog.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        return root;
    }

    public Parent LoadCustomProduct() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("AddCutomProductToCatalog.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        return root;
    }
}
