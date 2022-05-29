package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddToCartController{

    Stage stage;

    protected MainPageController main_page_holder;


    @FXML
    private MFXLegacyTableView<CartItem> cart_table;

    @FXML
    private MFXButton orderBTN;

    @FXML
    private MFXTextField sum_field;

    @FXML
    private TableColumn<CartItem, Integer> table_Column_id;

    @FXML
    private TableColumn<CartItem, String> table_Column_name;

    @FXML
    private TableColumn<CartItem, String> table_Column_price;

    @FXML
    private TableColumn<CartItem, Integer> table_Column_amount;

    private int total_sum = 0;

    ObservableList<CartItem> items = FXCollections.observableArrayList();

    @FXML
    void CloseATCWin(MouseEvent event) {
        this.stage.close(); //when clicking on text, window closing
    }
    void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void initialize(){
        this.TableInitializeFields();
        {
            this.addItemToTable("Dean", 50, 1,1);
            total_sum += 50;
            this.addItemToTable("Liran", 100,1, 3);
            total_sum += 100;
            this.addItemToTable("Ann", 100, 2,2);
            total_sum += 150;
            this.addItemToTable("Ido", 100, 3,10);
            total_sum += 10;
            this.addItemToTable("Dean", 100, 1,5);
            total_sum += 50;
            this.addItemToTable("Dean", 100,1, 8);
            total_sum += 50;
            this.addItemToTable("Dean", 100, 19,7);
            total_sum += 50;
        }

        cart_table.setItems(items);

        sum_field.setText(String.valueOf(total_sum));
    }

    public void TableInitializeFields(){
        table_Column_name.setCellValueFactory(new PropertyValueFactory<CartItem,String>("item_name"));
        table_Column_price.setCellValueFactory(new PropertyValueFactory<CartItem,String>("item_price"));
        table_Column_amount.setCellValueFactory(new PropertyValueFactory<CartItem,Integer>("item_amount"));
        table_Column_id.setCellValueFactory(new PropertyValueFactory<CartItem,Integer>("item_id"));
    }

    public void addItemToTable(String name, double price, int id, int amount){
        CartItem item = new CartItem(name,price,id,amount);
        this.items.addAll(item);
    }


    @FXML
    void OrderBTNClicked(MouseEvent event) throws IOException {
        // main_page_holder.getMain_first_load_pane().getChildren().clear();
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        URL var = getClass().getResource("Order.fxml");
//        fxmlLoader.setLocation(var);
//        Parent root = fxmlLoader.load();
//        OrderController controller = fxmlLoader.getController();
//        controller.setCart_controller(this);
        this.main_page_holder.LoadOrderPage();
        // main_page_holder.getMain_first_load_pane().getChildren().addAll(root);
    }

    public void LoadCart() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL var = getClass().getResource("AddToCart.fxml");
        fxmlLoader.setLocation(var);
        Parent root = fxmlLoader.load();
        OrderController controller = fxmlLoader.getController();
        controller.setCart_controller(this);
        controller.setSum_label(this.sum_field.getText());
    }


    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }

    public MainPageController getMain_page_holder() {
        return main_page_holder;
    }

    public String getSum_filed() {
        return sum_field.getText();
    }

}
