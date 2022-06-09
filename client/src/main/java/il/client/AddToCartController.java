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
import javafx.scene.control.Label;
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
    private Label no_items_label;

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





    @FXML
    private MFXLegacyTableView<ClientCustomItem> cart_CustomTable;

    @FXML
    private TableColumn<ClientCustomItem, MFXButton> amount_change_btn_Custom;

    @FXML
    private TableColumn<ClientCustomItem, Integer> table_Column_amount_Custom;

    @FXML
    private TableColumn<ClientCustomItem, MFXButton> remove_btn_col_Custom;

    @FXML
    private TableColumn<ClientCustomItem, Integer> table_Column_id_Custom;

    @FXML
    private TableColumn<ClientCustomItem, String> table_Column_name_Custom;

    @FXML
    private TableColumn<ClientCustomItem, String> table_Column_price_Custom;





    private double total_sum = 0;
    private double total_sum_custom = 0;

    ObservableList<CartItem> items = FXCollections.observableArrayList();

    ObservableList<ClientCustomItem> customItems = FXCollections.observableArrayList();


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
        cart_CustomTable.setItems(customItems);
        setTotalSum();
    }

    public void TableInitializeFields(){
        {
            cart_table.setFixedCellSize(40);
            table_Column_name.setCellValueFactory(new PropertyValueFactory<CartItem,String>("item_name"));
            table_Column_price.setCellValueFactory(new PropertyValueFactory<CartItem,String>("item_price"));
            table_Column_amount.setCellValueFactory(new PropertyValueFactory<CartItem,Integer>("item_amount"));
            table_Column_id.setCellValueFactory(new PropertyValueFactory<CartItem,Integer>("item_id"));
            amount_change_btn_col.setCellValueFactory(new PropertyValueFactory<CartItem,MFXButton>("amountChangeBtn"));
            remove_btn_col.setCellValueFactory(new PropertyValueFactory<CartItem,MFXButton>("removeBtn"));
        }

        {
            this.cart_CustomTable.setFixedCellSize(40);
            table_Column_name_Custom.setCellValueFactory(new PropertyValueFactory<ClientCustomItem,String>("type"));
            table_Column_price_Custom.setCellValueFactory(new PropertyValueFactory<ClientCustomItem,String>("price"));
            table_Column_amount_Custom.setCellValueFactory(new PropertyValueFactory<ClientCustomItem,Integer>("amount"));
            table_Column_id_Custom.setCellValueFactory(new PropertyValueFactory<ClientCustomItem,Integer>("id"));
            amount_change_btn_Custom.setCellValueFactory(new PropertyValueFactory<ClientCustomItem,MFXButton>("amountChangeBtn"));
            remove_btn_col_Custom.setCellValueFactory(new PropertyValueFactory<ClientCustomItem,MFXButton>("removeBtn"));
        }
    }

    public void addItemToCustomTable(ClientCustomItem item, int amount){
        ClientCustomItem item_to_add = new ClientCustomItem(item);
        this.customItems.addAll(item_to_add);
    }

    public void setTotalSum(){
        total_sum = 0.0;
        total_sum_custom = 0.0;
        System.out.println("items size AddToCartController in CART CONTROLLER"+items.size() + customItems.size());
        if(items.size() == 0 && customItems.size() ==0){
            total_sum = 0.0;
            sum_field.setText(String.valueOf(total_sum));
            return;
        }
        for(int i=0; i<items.size(); i++){
            total_sum = total_sum + (items.get(i).getItem_price() * items.get(i).getItem_amount());
        }

        for(int i=0; i<customItems.size(); i++){
            total_sum_custom = total_sum_custom + (Double.valueOf(customItems.get(i).getPrice()) * customItems.get(i).getAmount());
        }
        System.out.println("total sum in AddToCartController "+ (total_sum + total_sum_custom));

        sum_field.setText(String.valueOf(total_sum +total_sum_custom));
    }

    public void setChanges(){
        total_sum = 0.0;
        cart_table.setItems(items);
        for(int i=0; i<items.size(); i++){
            total_sum = total_sum + (items.get(i).getItem_price() * items.get(i).getItem_amount());
        }
        this.sum_field.setText(Double.toString(total_sum+total_sum_custom));
    }

    public void setChangesCustom(){
        total_sum_custom = 0.0;
        cart_CustomTable.setItems(this.customItems);
        for(int i=0; i<customItems.size(); i++){
            total_sum_custom = total_sum_custom + (Double.valueOf(customItems.get(i).getPrice()) * Double.valueOf(customItems.get(i).getAmount()));
        }
        System.out.println(total_sum_custom);
        double sum = total_sum+total_sum_custom;
        this.sum_field.setText(Double.toString(sum));
    }

    public void addItemToTable(String name, double price, int id, int amount){
        CartItem item = new CartItem(name,price,amount,id);
        this.items.addAll(item);
    }
    public void addItemToTable(CartItem item, int amount){
        CartItem item_to_add = new CartItem(item.getItem_name(), item.getItem_price() ,amount,item.getItem_id());
        this.items.addAll(item_to_add);
    }


    public void removeItemFromTable(int id) throws IOException {
        double price_of_product=0.0;
        for(int i=0; i<this.items.size(); i++){
            if(items.get(i).getItem_id() == id){
                price_of_product = items.get(i).getItem_price() * items.get(i).getItem_amount();
                System.out.println("Removed Item:" + items.get(i).getItem_name() + " "+ price_of_product);
                this.items.remove(items.get(i));
                System.out.println("Removed Item");
                break;
            }
        }
        this.total_sum = total_sum - price_of_product;
        this.sum_field.setText(Double.toString(total_sum+total_sum_custom));
     //   MainPageController.getInstance().LoadCartPage();
    }

    public void removeItemFromCustomTable(int id) throws IOException {
        double price_of_product=0.0;
        for(int i=0; i<this.customItems.size(); i++){
            if(customItems.get(i).getCustomId() == id){
                price_of_product = Double.valueOf(customItems.get(i).getPrice()) * Double.valueOf(customItems.get(i).getAmount());
                System.out.println("Removed Item:" + customItems.get(i).getType() + " "+ price_of_product);
                this.customItems.remove(customItems.get(i));
                System.out.println("Removed Item");
                break;
            }
        }
        this.total_sum_custom = total_sum_custom - price_of_product;
        this.sum_field.setText(Double.toString(total_sum+total_sum_custom));
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
        if(items.size()!=0 || customItems.size() != 0) {
            OrderController order_controller = (OrderController) MainPageController.getInstance().getController_map().get("Order");
            if(UserClient.getInstance().getPlan()==3 && (total_sum+total_sum_custom)>50) { //yearly membership
                order_controller.setSum_label(Double.toString(((total_sum+total_sum_custom) * 0.9) - UserClient.getInstance().getCredit()));
            }
            else{
                order_controller.setSum_label(Double.toString((total_sum+total_sum_custom)- UserClient.getInstance().getCredit()));
            }
            OrderController.getInstance().setCart(this.items);
            OrderController.getInstance().setCustomCart(this.customItems);

            for(int i=0;i<MainPageController.allStores.size(); i++){
                if(!(order_controller.store_chooser.getItems().contains(MainPageController.allStores.get(i).getAddress()))){
                    order_controller.store_chooser.getItems().add(MainPageController.allStores.get(i).getAddress());
                }
            }

            if(UserClient.getInstance().getPlan() == 1){
                String store = UserClient.getInstance().getStoresOfStore().get(0).getAddress();
                order_controller.store_chooser.getItems().clear();
                order_controller.store_chooser.getItems().add(store);
            }

            this.no_items_label.setVisible(false);
            MainPageController.getInstance().LoadOrderPage();
        } else{
            this.no_items_label.setVisible(true);
        }
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


    public CartItem getCartItemById(int id){
        for(int i=0; i< items.size(); i++){
            if(items.get(i).getItem_id() == id){
                return items.get(i);
            }
        }
        return null;
    }

    public void removeCartItemById(int id){
        for(int i=0; i<items.size(); i++){
            if(items.get(i).getItem_id() == id){
                items.remove(i);
            }
        }
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

    public void setNo_items_labelFalse() {
        no_items_label.setVisible(false);
    }

    public void setNo_items_labelTrue() {
        this.no_items_label.setVisible(true);
    }

    public ObservableList<ClientCustomItem> getCustomItems() {
        return customItems;
    }

    public void setCustomItems(ObservableList<ClientCustomItem> customItems) {
        this.customItems = customItems;
    }
}
