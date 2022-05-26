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
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
            this.addItemToTable("Dean", "50$", 1);
            this.addItemToTable("Liran", "100$", 3);
            this.addItemToTable("Ann", "150$", 2);
            this.addItemToTable("Ido", "10$", 10);
            this.addItemToTable("Dean", "50$", 5);
            this.addItemToTable("Dean", "50$", 8);
            this.addItemToTable("Dean", "50$", 7);
        }

        cart_table.setItems(items);
    }

    public void TableInitializeFields(){
        table_Column_name.setCellValueFactory(new PropertyValueFactory<CartItem,String>("item_name"));
        table_Column_price.setCellValueFactory(new PropertyValueFactory<CartItem,String>("item_price"));
        table_Column_id.setCellValueFactory(new PropertyValueFactory<CartItem,Integer>("item_id"));
    }
    public void addItemToTable(String name, String price, int id){
        CartItem item = new CartItem(name,price,id);
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

        this.sum_field.setText("555");
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
