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


    private boolean onColorPick = false;
    private boolean onPricePick = false;

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
            ProductView product = listOfProducts.getSelectionModel().getSelectedValues().get(0);
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
        }
    }


    public synchronized void addToChosenList(ProductView product){
        temp.clear();
        int flag = 0;
        for(ProductView prod : chosenProducts){
            temp.add(prod);
        }
        this.chosenProducts.clear();
        for(ProductView prod : temp){
            if(prod.getId() == product.getId()){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            temp.add(product);
        }
        for(ProductView prod : temp){
            this.chosenProducts.add(prod);
        }
        this.listOfChosenProducts.setItems(chosenProducts);
        this.sum_label.setText(calculateOrderSum(chosenProducts));
    }

    public String calculateOrderSum(ObservableList<ProductView> list){
        double sum = 0;
        for(ProductView product : list){
            sum = sum + product.getProduct_price();
        }
        return Double.toString(sum);
    }

    private synchronized  void RemoveProductSelected(ProductView product) {
        temp.clear();
        for(ProductView prod : chosenProducts){
            if(prod.getId() != product.getId()){
                temp.add(prod);
            }
        }
        if(chosenProducts.size() == 1){
            if(chosenProducts.get(0).getId() == product.getId()) {
                temp.clear();
            }
        }
        for(int i = 0 ; i < temp.size() ; i++){
            System.out.println(temp.get(i).getProduct_name() + "Printing on CustomProductController under Remove ProductSelected Function");
        }
        this.chosenProducts.clear();
        this.chosenProducts.addAll(temp);
        this.listOfChosenProducts.setItems(chosenProducts);
        this.sum_label.setText(calculateOrderSum(chosenProducts));
    }
//    private void RemoveProductSelected(ProductView product) {
//        temp.clear();
//        if(chosenProducts.size() == 1){
//            if(chosenProducts.get(0).getId() == product.getId()){
//                chosenProducts.clear();
//            }
//        }else {
//            for(ProductView prod : chosenProducts){
//                temp.add(prod);
//            }
//            this.chosenProducts.clear();
//            for(ProductView prod : temp){
//                if(prod.getId() == product.getId()){
//                    temp.remove(prod);
//                    break;
//                }
//            }
//            for(ProductView prod : temp){
//                this.chosenProducts.add(prod);
//            }
//        }
//        this.listOfChosenProducts.setItems(chosenProducts);
//    }

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
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("CartAdderDialogCustom.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 405  , 215);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        CartAdderCustomController controller = fxmlLoader.getController();
        controller.initialize(stage,chosenProducts);
        stage.setTitle("Add To Cart Section");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void CancelBTNClicked(ActionEvent event) {

    }

    @FXML
    void IAmDoneBTNClicked(ActionEvent event) {

    }

//    @FXML
//    void setListByPriceMax(MouseEvent event) {
//        int maxValue = (int) this.max_price_slider.getValue();
//        int minValue = (int) this.min_price_slider.getValue();
//        this.onPricePick = false;
//        if(minValue>maxValue) {
//            listOfProducts.setItems(null);
//            listOfProducts.setItems(this.productListManeuver);
//        }
//        else if(onColorPick == true){
//            if(minValue <= maxValue){
//                for(ProductView product : productListManeuver){
//                    if(!(product.getProduct_price()>=minValue) || !(product.getProduct_price() <= maxValue)){
//                        productListManeuver.remove(product);
//                    }
//                }
//            }
//        }else if(onColorPick = false){
//            if(minValue <= maxValue){
//                for(ProductView product : productListManeuver){
//                    if(!(product.getProduct_price()>=minValue) || !(product.getProduct_price() <= maxValue)){
//                        productListManeuver.remove(product);
//                    }
//                }
//                for(ProductView product : productList){
//                    if((product.getProduct_price()>=minValue && product.getProduct_price() <= maxValue) && !productListManeuver.contains(product)){
//                        productListManeuver.add(product);
//                    }
//                }
//            }
//        }
//
//        this.onPricePick = true;
//        this.onColorPick = false;
//        this.listOfProducts.setItems(productListManeuver);
//    }

    @FXML
    synchronized void setListByPriceMax(MouseEvent event) {
        this.productListManeuver.clear();
        int maxValue = (int) this.max_price_slider.getValue();
        int minValue = (int) this.min_price_slider.getValue();
        if(minValue <= maxValue){
            for(ProductView product : productList){
                if(product.getProduct_price() >= minValue && product.getProduct_price() <= maxValue){
                    productListManeuver.add(product);
                }
            }
        }
        this.listOfProducts.setItems(productListManeuver);
    }

    @FXML
    synchronized void setListByColor() {
        this.productListManeuver.clear();
        for (ProductView product : productList) {
            if(product.getColor().equals(this.colorPicker.getValue())) {
                productListManeuver.add(product);
            }
        }
        this.listOfProducts.setItems(productListManeuver);
    }



//    @FXML
//    void setListByColor(){
//        this.onColorPick = false;
//        if(onPricePick == true){
//            for(ProductView product : productListManeuver){
//                if(!product.getColor().equals(this.colorPicker.getValue())){
//                    productListManeuver.remove(product);
//                }
//            }
//        }else if(onPricePick == false){
//            for(ProductView product : productListManeuver){
//                if(!product.getColor().equals(this.colorPicker.getValue())){
//                    productListManeuver.remove(product);
//                }
//            }
//            for(ProductView product : productList){
//                if((product.getColor().equals(this.colorPicker.getValue())) && !productListManeuver.contains(product)){
//                    productListManeuver.add(product);
//                }
//            }
//        }
//        this.onPricePick = false;
//        this.onColorPick = true;
//        this.listOfProducts.setItems(productListManeuver);
//    }
}
