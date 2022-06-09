package il.client;

import il.client.controls.ReportControl;
import il.entities.Product;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ProductsReportController {

    @FXML
    private MFXDatePicker end_date_store1;

    @FXML
    private MFXDatePicker end_date_store2;

    @FXML
    private MFXDatePicker start_date_store1;

    @FXML
    private MFXDatePicker start_date_store2;

    @FXML
    private TableColumn<ProductsReportController, Integer> amount_store1_col;

    @FXML
    private TableColumn<ProductsReportController, Integer> amount_store2_col;

    @FXML
    private TableColumn<ProductsReportController, String> name_store1_col;

    @FXML
    private TableColumn<ProductsReportController, String> name_store2_col;

    @FXML
    private MFXComboBox<String> store1_chooser;

    @FXML
    private MFXLegacyTableView<ProductsReportClient> store1_table;

    @FXML
    private MFXComboBox<String> store2_chooser;

    @FXML
    private MFXLegacyTableView<ProductsReportClient> store2_table;

    ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private MFXButton store2;

    @FXML
    void Store1Chooser(ActionEvent event) {

    }

    @FXML
    void Store2Chooser(ActionEvent event) {

    }

    private List<Product> products = new LinkedList<Product>();

    @FXML
    void initialize(){
        CatalogController controller = (CatalogController) MainPageController.getInstance().getController_map().get("Catalog");
        products = controller.getFlowerlist();
        if(UserClient.getInstance().getPriority() == 5){
            this.store2_chooser.setVisible(false);
            this.start_date_store2.setVisible(false);
            this.end_date_store2.setVisible(false);
            this.store2_table.setVisible(false);
            this.store2.setVisible(false);
            this.store1_chooser.setVisible(false);
            int store_id = UserClient.getInstance().getStoreId();
            String store="";
            if(store_id == 1) {store="Haifa";}
            if(store_id == 2) {store="Tel Aviv";}
            if(store_id == 3) {store="Jerusalem";}
            System.out.println(store);
            this.store1_chooser.setValue(store);
            this.store1_chooser.setDisable(true);
        }


        this.store1_chooser.getItems().add("All");
        this.store1_chooser.getItems().add("Haifa");
        this.store1_chooser.getItems().add("Tel Aviv");
        this.store1_chooser.getItems().add("Jerusalem");
        this.store2_chooser.getItems().add("All");
        this.store2_chooser.getItems().add("Haifa");
        this.store2_chooser.getItems().add("Tel Aviv");
        this.store2_chooser.getItems().add("Jerusalem");
    }

    @FXML
    void store1ApplyClicked(ActionEvent event) {
        LocalDate start_date_store1 = this.start_date_store1.getValue();
        LocalDate end_date_store1 = this.end_date_store1.getValue();
        LocalDate startDate = start_date_store1;
        LocalDate endDate = end_date_store1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");

        String start_store1 = formatter.format(startDate);
        String end_store1 = formatter.format(endDate);
        String store = this.store1_chooser.getValue();
        int store_id = -2;
        if (store.equals("Haifa")) {
            store_id = 1;
        }
        if (store.equals("Tel Aviv")) {
            store_id = 2;
        }
        if (store.equals("Jerusalem")) {
            store_id = 3;
        }
        if (store.equals("All")) {
            store_id = -1;
        }
        System.out.println(store_id);
        ReportControl report = new ReportControl(UserClient.getInstance().getOrdersEntity(), UserClient.getInstance().getComplaintsEntity(), start_store1, end_store1, store_id);
//        CatalogController controller = (CatalogController) MainPageController.getInstance().getController_map().get("Catalog");
//        List<Product> products = controller.getFlowerlist();
        List<String> products_name = new LinkedList<String>();
        for (int i = 0; i < products.size(); i++) {
            products_name.add(products.get(i).getName());
        }
       // LinkedList<Order> orders = UserClient.getInstance().getOrdersEntity();
        HashMap<String, Integer> products_amount = new HashMap<String, Integer>();
        ObservableList<ProductsReportClient> to_table = FXCollections.observableArrayList();

        for(String name: products_name){
            products_amount.put(name,report.getcountProduct(name));
            ProductsReportClient p = new ProductsReportClient(name, report.getcountProduct(name));
            to_table.add(p);
        }
        System.out.println("product_amount "+products_amount);
        System.out.println("to_table "+to_table);
        store1_table.setFixedCellSize(40);
        name_store1_col.setCellValueFactory(new PropertyValueFactory<ProductsReportController, String>("name"));
        amount_store1_col.setCellValueFactory(new PropertyValueFactory<ProductsReportController, Integer>("amount"));
        store1_table.setItems(to_table);

//        report.getcountDate(); //date order format -> get how many compalint we have in this day

    }

    @FXML
    void store2ApplyClicked(ActionEvent event) {

        LocalDate start_date_store2 = this.start_date_store2.getValue();
        LocalDate end_date_store2 = this.end_date_store2.getValue();
        LocalDate startDate = start_date_store2;
        LocalDate endDate = end_date_store2;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");

        String start_store2 = formatter.format(startDate);
        String end_store2 = formatter.format(endDate);
        String store = this.store2_chooser.getValue();
        int store_id = -2;
        if (store.equals("Haifa")) {
            store_id = 1;
        }
        if (store.equals("Tel Aviv")) {
            store_id = 2;
        }
        if (store.equals("Jerusalem")) {
            store_id = 3;
        }
        if (store.equals("All")) {
            store_id = -1;
        }
        System.out.println(store_id);
        ReportControl report = new ReportControl(UserClient.getInstance().getOrdersEntity(), UserClient.getInstance().getComplaintsEntity(), start_store2, end_store2, store_id);
        CatalogController controller = (CatalogController) MainPageController.getInstance().getController_map().get("Catalog");
        List<Product> products = controller.getFlowerlist();
        List<String> products_name = new LinkedList<String>();
        for (int i = 0; i < products.size(); i++) {
            products_name.add(products.get(i).getName());
        }
        // LinkedList<Order> orders = UserClient.getInstance().getOrdersEntity();
        HashMap<String, Integer> products_amount = new HashMap<String, Integer>();
        ObservableList<ProductsReportClient> to_table = FXCollections.observableArrayList();

        for(String name: products_name){
            products_amount.put(name,report.getcountProduct(name));
            ProductsReportClient p = new ProductsReportClient(name, report.getcountProduct(name));
            to_table.add(p);
        }
        System.out.println("product_amount "+products_amount);
        System.out.println("to_table "+to_table);
        store2_table.setFixedCellSize(40);
        name_store2_col.setCellValueFactory(new PropertyValueFactory<ProductsReportController, String>("name"));
        amount_store2_col.setCellValueFactory(new PropertyValueFactory<ProductsReportController, Integer>("amount"));
        store2_table.setItems(to_table);
    }

}
