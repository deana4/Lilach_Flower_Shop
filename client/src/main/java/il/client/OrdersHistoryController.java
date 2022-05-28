package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdersHistoryController {

    @FXML
    private TableColumn<Order, String> address_col;

    @FXML
    private TableColumn<Order, String> date_col;

    @FXML
    private TableColumn<Order, Integer> id_col;

    @FXML
    private TableColumn<Order, String> name_receiver_col;

    @FXML
    private MFXLegacyTableView<Order> orders_table;

    @FXML
    private TableColumn<Order, String> phone_receiver_col;

    @FXML
    private TableColumn<Order, String> time_col;

    @FXML
    private TableColumn<Order, MFXButton> complaint_col;

    @FXML
    private TableColumn<Order, MFXButton> cancel_col;

    ObservableList<Order> items = FXCollections.observableArrayList();

    private MyAccountController my_account_page_holder;

    @FXML
    void initialize(){
        TableInitializeFields();
        items = UserClient.getInstance().getOrderList();
        for(int i=0; i< items.size(); i++){
            System.out.println(items.get(i));
        }
        orders_table.setItems(UserClient.getInstance().getOrderList());
    }
//
    public void TableInitializeFields() {
        id_col.setCellValueFactory(new PropertyValueFactory<Order, Integer>("this_id"));
        date_col.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
        time_col.setCellValueFactory(new PropertyValueFactory<Order, String>("orderTime"));
        phone_receiver_col.setCellValueFactory(new PropertyValueFactory<Order, String>("phoneReceiver"));
        name_receiver_col.setCellValueFactory(new PropertyValueFactory<Order, String>("nameReceiver"));
        address_col.setCellValueFactory(new PropertyValueFactory<Order, String>("Address"));
        complaint_col.setCellValueFactory(new PropertyValueFactory<Order, MFXButton>("complaint"));
        cancel_col.setCellValueFactory(new PropertyValueFactory<Order, MFXButton>("cancel"));

    }

    /* gets and sets*/

    public void setMy_account_page_holder(MyAccountController my_account_page_holder) {
        this.my_account_page_holder = my_account_page_holder;
    }

    /* end gets and sets*/
}
