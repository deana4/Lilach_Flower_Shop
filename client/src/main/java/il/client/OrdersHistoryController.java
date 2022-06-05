package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class OrdersHistoryController {
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
        orders_table.setFixedCellSize(40);
        id_col.setCellValueFactory(new PropertyValueFactory<Order, Integer>("this_id"));
        date_col.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
        time_col.setCellValueFactory(new PropertyValueFactory<Order, String>("orderTime"));
        phone_receiver_col.setCellValueFactory(new PropertyValueFactory<Order, String>("phoneReceiver"));
        name_receiver_col.setCellValueFactory(new PropertyValueFactory<Order, String>("nameReceiver"));
        complaint_col.setCellValueFactory(new PropertyValueFactory<Order, MFXButton>("complaint"));
        cancel_col.setCellValueFactory(new PropertyValueFactory<Order, MFXButton>("cancel"));

        orders_table.setRowFactory(s->{
            TableRow<Order> row = new TableRow<Order>();
            row.setOnMouseClicked(mouseEvent -> {
                        System.out.println(row.getItem());
                        try {
                            detailedOrderScreen(row);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return row;
        });
    }
    public void detailedOrderScreen(TableRow<Order> row) throws IOException {
        Order order = UserClient.getInstance().getOrderById(row.getItem().getThis_id());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("DetailedOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 625 , 285);
        DetailedOrderController controller = fxmlLoader.getController();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Detailed Order");
        stage.setScene(scene);
        stage.show();
        controller.initialize(order,stage);
    }

    /* gets and sets*/

    public ObservableList<Order> getItems() {
        return items;
    }

    public void setItems(ObservableList<Order> items) {
        this.items = items;
    }

    public void setMy_account_page_holder(MyAccountController my_account_page_holder) {
        this.my_account_page_holder = my_account_page_holder;
    }

    /* end gets and sets*/
}
