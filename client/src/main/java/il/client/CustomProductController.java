package il.client;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class CustomProductController {

    @FXML
    private TableColumn<ProductView, String> chosen_id_col;

    @FXML
    private TableColumn<ProductView, String> chosen_name_col;

    @FXML
    private MFXLegacyTableView<ProductView> chosen_table;

    @FXML
    private TableColumn<ProductView, String> name_col;

    @FXML
    private TableColumn<ProductView, String> id_col;

    @FXML
    private MFXLegacyTableView<ProductView> products_table;

    @FXML
    private MFXToggleButton active_color_chooser;

    @FXML
    private MFXButton addToCartBTN;

    @FXML
    private MFXButton cancelBTN;

    @FXML
    private GridPane grid_pane;

    @FXML
    private MFXButton iAmDoneBTN;

    @FXML
    private MFXSlider max_price_slider;

    @FXML
    private MFXSlider min_price_slider;

    @FXML
    private Label no_items_label;

    @FXML
    private MFXScrollPane scroll_pane;

    @FXML
    private AnchorPane side_pick_ancorepane;

    @FXML
    private Label sum_label;

    @FXML
    private Label errorLabel;

    @FXML
    private MFXComboBox<String> type_chooser;

    @FXML
    private MFXComboBox<String> colorPicker;

    @FXML
    private MFXListView<ProductView> listOfProducts;

    @FXML
    private MFXListView<ProductView> listOfChosenProducts;

    private ObservableList<ProductView> productList = FXCollections.observableArrayList();

    private ObservableList<ProductView> productListManeuver = FXCollections.observableArrayList();

    private ObservableList<String> types = FXCollections.observableArrayList();

    private ObservableList<ProductView> chosenProducts = FXCollections.observableArrayList();

    private ObservableList<ProductView> temp = FXCollections.observableArrayList();


    private String colorInList = null;
    private int min = 0;
    private int max = 1000;

    @FXML
    void initialize(){
        /* initializing Tables*/
        HashMap<Integer,ProductView> products = CatalogController.getInstance().getProductsControllers();
        for(int i=0; i<products.size()-2;i++){   //convert hashmap of products into Observable list.
            if(products.get(i) != null){
                productList.add(products.get(i));
            }
        }
        StringConverter<ProductView> converter = FunctionalStringConverter.to(product -> (product == null) ? "NULL" : product.getProduct_name());

        listOfProducts.setItems(productList);
        listOfProducts.setConverter(converter);
        listOfProducts.setCellFactory(product -> new ItemCellFactory(listOfProducts, product));
        listOfProducts.features().enableBounceEffect();
        listOfProducts.features().enableSmoothScrolling(0.5);

        listOfChosenProducts.setItems(chosenProducts);
        listOfChosenProducts.setConverter(converter);
        listOfChosenProducts.setCellFactory(product -> new ItemCellFactory(listOfChosenProducts, product));
        listOfChosenProducts.features().enableBounceEffect();
        listOfChosenProducts.features().enableSmoothScrolling(0.5);

        listOfProducts.setOnMouseClicked(action-> {
            ProductView product = listOfProducts.getSelectionModel().getSelectedValues().get(0);
            try {
                LoadProductSelected(product);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        listOfChosenProducts.setOnMouseClicked(action-> {
            ProductView product = listOfChosenProducts.getSelectionModel().getSelectedValues().get(0);
            RemoveProductSelected(product);
        });
        /* Finish table initialize*/
        {
            types.add("Bridal Bouquet");
            types.add("Potted Plant");
            types.add("Flower Decoration");
            types.add("Flower Cluster");
            type_chooser.setItems(types);
        }
        {
            colorPicker.setItems(MainPageController.getInstance().getColors());
            colorPicker.getItems().add(0,"All"); //All color option
        }
    }


    public void addToChosenList(ProductView product){
        if(!this.listOfChosenProducts.getItems().contains(product)){
            this.listOfChosenProducts.getItems().add(product);
        }else if(this.listOfChosenProducts.getItems().contains(product)){
            System.out.println("Value is already picked from catalog list - CUSTOMPRODUCTCONTROLLER");
        }
        this.sum_label.setText(calculateOrderSum(this.listOfChosenProducts.getItems()));
    }


    public String calculateOrderSum(ObservableList<ProductView> list){
        double sum = 0;

        for(int i = 0 ; i<list.size(); i++){
            sum = sum + list.get(i).getProduct_price();
        }
        return Double.toString(sum);
    }


    private void RemoveProductSelected(ProductView product) {
        if(this.listOfChosenProducts.getItems().size() == 1 && this.listOfChosenProducts.getItems().get(0).getId() == product.getId()){
            this.listOfChosenProducts.getItems().remove(this.listOfChosenProducts.getItems().get(0));
        }else if(this.listOfChosenProducts.getItems().size() > 1){
            for(int i = 0 ; i<this.listOfChosenProducts.getItems().size(); i++){
                if(this.listOfChosenProducts.getItems().get(i).getId() == product.getId()){
                    this.listOfChosenProducts.getItems().remove(this.listOfChosenProducts.getItems().get(i));
                    break;
                }
            }
        }
        this.sum_label.setText(calculateOrderSum(this.listOfChosenProducts.getItems()));
    }

    private void LoadProductSelected(ProductView product) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("PopWindowForCustom.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(fxmlLoader.load(), 681, 514);
        scene.setFill(Color.TRANSPARENT);
        PopWindowCustomProduct controller = fxmlLoader.getController();
        controller.FullSetter(product.getId(), product.getProduct_name(), String.valueOf(product.getProduct_price()), product.isOn_discount(), product.getProductImageNotURL(), product.getColor(), product.getType());
        controller.initialize(stage,product,this);
        controller.setStage(stage);
        stage.setTitle("Custom Item View");
        stage.setScene(scene);
        stage.show();
    }

    private static class ItemCellFactory extends MFXListCell<ProductView> {
        private final MFXFontIcon userIcon;
        public ProductView productController;
        public ItemCellFactory(MFXListView<ProductView> listView, ProductView data) {
            super(listView, data);
            productController  = data;
            userIcon = new MFXFontIcon("mfx-gear", 18);
            userIcon.getStyleClass().add("user-icon");
            render(data);
        }

        @Override
        protected void render(ProductView data) {
            super.render(data);
            if (userIcon != null) getChildren().add(0, userIcon);
        }

        public ProductView getProductViewController(){
            return this.productController;
        }
    }

    @FXML
    void AddToCartBTNClicked(ActionEvent event) throws IOException {
        if(this.type_chooser.getValue() == "" || this.type_chooser.getValue() == null || this.listOfChosenProducts.getItems().isEmpty()){
            this.errorLabel.setVisible(true);
        }else {
            this.errorLabel.setVisible(false);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CartAdderDialogCustom.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 405  , 215);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            CartAdderCustomController controller = fxmlLoader.getController();
            controller.setStage(stage);
            controller.initialize(this.listOfChosenProducts.getItems(),this.type_chooser.getValue(),-1);
            stage.setTitle("Add To Cart Section");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void CancelBTNClicked(ActionEvent event) throws IOException {
        MainPageController.getInstance().LoadCatalogPage();
    }

    @FXML
    void IAmDoneBTNClicked(ActionEvent event) throws IOException {
        MainPageController.getInstance().LoadCartPage();
    }

    @FXML
    void setListByPriceMax(MouseEvent event) {
        this.productListManeuver.clear();
        int maxValue = (int) this.max_price_slider.getValue();
        int minValue = (int) this.min_price_slider.getValue();
        this.min = minValue;
        this.max = maxValue;
        System.out.println(productList);
        if (min <= max) {
           checkPrice();
        }
        this.listOfProducts.setItems(productListManeuver);
    }


    @FXML
    void setListByColor() {
        productListManeuver.clear();
        this.colorInList = this.colorPicker.getValue(); //maybe not null
        checkColor();
        this.listOfProducts.setItems(productListManeuver);
    }


    void checkPrice(){
        for(int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProduct_price() >= min && productList.get(i).getProduct_price() <= max) {
                if (this.colorInList != null) {
                    if(colorInList.equals("All")){
                        this.productListManeuver.add(productList.get(i));
                    }else {
                        if (this.productList.get(i).getColor().equals(colorInList)) {
                            this.productListManeuver.add(productList.get(i));
                        }
                    }
                } else if (this.colorInList == null){
                    this.productListManeuver.add(productList.get(i));
                }
            }
        }
    }

//    void checkPrice(){   ***WORKING
//        for(int i = 0; i < productList.size(); i++) {
//            if (productList.get(i).getProduct_price() >= min && productList.get(i).getProduct_price() <= max) {
//                if (this.colorInList != null) {
//                    if (this.productList.get(i).getColor().equals(colorInList)) {
//                        this.productListManeuver.add(productList.get(i));
//                    }
//                } else if (this.colorInList == null){
//                    this.productListManeuver.add(productList.get(i));
//                }
//            }
//        }
//    }

    void checkColor(){
        for(int i = 0; i < productList.size(); i++) {
            if(this.colorInList == "All"){
                if (productList.get(i).getProduct_price() >= min && productList.get(i).getProduct_price() <= max) {
                    this.productListManeuver.add(productList.get(i));
                }
            }else {
                if (this.productList.get(i).getColor().equals(this.colorInList)) {
                    if (productList.get(i).getProduct_price() >= min && productList.get(i).getProduct_price() <= max) {
                        this.productListManeuver.add(productList.get(i));
                    }
                }
            }
        }
    }

}
