package il.client;

import il.entities.User;
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
import javafx.scene.control.TableRow;
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

    @FXML
    private TableColumn<CartItem, MFXButton> amount_change_btn_col;

    @FXML
    private TableColumn<CartItem, MFXButton> remove_btn_col;

    private double total_sum = 0;

    ObservableList<CartItem> items = FXCollections.observableArrayList();

    private static AddToCartController CartInstance = null;

    public static AddToCartController getInstance(){
        if(CartInstance == null) {
            CartInstance = new AddToCartController();
        }
        return CartInstance;
    }

    @FXML
    void CloseATCWin(MouseEvent event) {
        this.stage.close(); //when clicking on text, window closing
    }
    void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void initialize(){
        CartInstance = this;
        this.TableInitializeFields();
        cart_table.setItems(items);
        setTotalSum();
    }

    public void TableInitializeFields(){
        cart_table.setFixedCellSize(40);
        table_Column_name.setCellValueFactory(new PropertyValueFactory<CartItem,String>("item_name"));
        table_Column_price.setCellValueFactory(new PropertyValueFactory<CartItem,String>("item_price"));
        table_Column_amount.setCellValueFactory(new PropertyValueFactory<CartItem,Integer>("item_amount"));
        table_Column_id.setCellValueFactory(new PropertyValueFactory<CartItem,Integer>("item_id"));
        amount_change_btn_col.setCellValueFactory(new PropertyValueFactory<CartItem,MFXButton>("amountChangeBtn"));
        remove_btn_col.setCellValueFactory(new PropertyValueFactory<CartItem,MFXButton>("removeBtn"));
    }

    public void setTotalSum(){
        total_sum = 0.0;
        System.out.println("items size AddToCartControoler "+items.size());
        if(items.size() == 0){
            total_sum = 0.0;
            sum_field.setText(String.valueOf(total_sum));
            return;
        }
        for(int i=0; i<items.size(); i++){
            total_sum = total_sum + (items.get(i).getItem_price() * items.get(i).getItem_amount());
            System.out.println("total sum in AddToCartController "+ total_sum);
        }
        sum_field.setText(String.valueOf(total_sum));
    }

    public void setChanges(){
        cart_table.setItems(items);
    }
    public void addItemToTable(String name, double price, int id, int amount){
        CartItem item = new CartItem(name,price,amount,id);
        this.items.addAll(item);
    }
    public void addItemToTable(CartItem item, int amount){
        CartItem item_to_add = new CartItem(item.getItem_name(), item.getItem_price() ,amount,item.getItem_id());
        this.items.addAll(item_to_add);
    }
    public void setItemAmount(CartItem itemToChange, int amount){
        for(CartItem item:items){
            if(item.getItem_id() == itemToChange.getItem_id()){
                item.setItem_amount(amount);
            }
        }
        setTotalSum();
    }

    public void removeItemFromTable(int id) throws IOException {
        for(CartItem item: items){
            if(item.getItem_id() == id){
                this.items.remove(item);
                System.out.println("Removed Item:" + item.getItem_name());
            }
        }
        MainPageController.getInstance().LoadCartPage();
    }

    public void setAmountByID(int id, int amount){
        for(CartItem item : items){
            if(item.getItem_id() == id){
                item.setItem_amount(amount);
            }
        }
        setTotalSum();
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
//        this.main_page_holder.LoadOrderPage();
        OrderController order_controller = (OrderController) MainPageController.getInstance().getController_map().get("Order");
        order_controller.setSum_label(Double.toString(total_sum));
        MainPageController.getInstance().LoadOrderPage();
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

    public ObservableList<CartItem> getItemsList() {
        return items;
    }


}
