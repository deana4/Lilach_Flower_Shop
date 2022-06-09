package il.client;

import il.client.events.OrderEvent;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.application.Platform;
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
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class OrdersHistoryController {
    @FXML
    private TableColumn<OrderClient, String> date_col;

    @FXML
    private TableColumn<OrderClient, Integer> id_col;

    @FXML
    private TableColumn<OrderClient, String> name_receiver_col;

    @FXML
    private MFXLegacyTableView<OrderClient> orders_table;

    @FXML
    private TableColumn<OrderClient, String> phone_receiver_col;

    @FXML
    private TableColumn<OrderClient, String> time_col;

    @FXML
    private TableColumn<OrderClient, MFXButton> complaint_col;

    @FXML
    private TableColumn<OrderClient, MFXButton> cancel_col;


    ObservableList<OrderClient> items = FXCollections.observableArrayList();

    private MyAccountController my_account_page_holder;

    @FXML
    void initialize(){
        TableInitializeFields();
        items = UserClient.getInstance().getOrderList();
//        for(int i=0; i< items.size(); i++){
//            System.out.println(items.get(i));
//        } printing the orders
        if(orders_table.getItems().size()!=0){
            this.orders_table.getItems().clear();
        } //?????????
        orders_table.setItems(UserClient.getInstance().getOrderList());
    }
//


    public void TableInitializeFields() {
            orders_table.setFixedCellSize(40);
            id_col.setCellValueFactory(new PropertyValueFactory<OrderClient, Integer>("this_id"));
            date_col.setCellValueFactory(new PropertyValueFactory<OrderClient, String>("orderDate"));
            time_col.setCellValueFactory(new PropertyValueFactory<OrderClient, String>("orderTime"));
            phone_receiver_col.setCellValueFactory(new PropertyValueFactory<OrderClient, String>("phoneReceiver"));
            name_receiver_col.setCellValueFactory(new PropertyValueFactory<OrderClient, String>("nameReceiver"));
//        complaint_col.setCellValueFactory(new PropertyValueFactory<OrderClient, MFXButton>("complaint"));
//        cancel_col.setCellValueFactory(new PropertyValueFactory<OrderClient, MFXButton>("cancel"));

            orders_table.setRowFactory(s -> {
                TableRow<OrderClient> row = new TableRow<OrderClient>();
                row.setOnMouseClicked(mouseEvent -> {
                            System.out.println(row.getItem());
                            try {
                                if (row.getItem() != null) {
                                    detailedOrderScreen(row);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
                return row;
            });
    }
    public void detailedOrderScreen(TableRow<OrderClient> row) throws IOException {
        OrderClient order = UserClient.getInstance().getOrderById(row.getItem().getThis_id());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("DetailedOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 625 , 285);
        DetailedOrderController controller = fxmlLoader.getController();
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Detailed Order");
        stage.setScene(scene);
        stage.show();
        controller.initialize(order,stage);
    }

    /* gets and sets*/

    public ObservableList<OrderClient> getItems() {
        return items;
    }

    public void setItems(ObservableList<OrderClient> items) {
        this.items = items;
    }

    public void setMy_account_page_holder(MyAccountController my_account_page_holder) {
        this.my_account_page_holder = my_account_page_holder;
    }

    /* end gets and sets*/
}
