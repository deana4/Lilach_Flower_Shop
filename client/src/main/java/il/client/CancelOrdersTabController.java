package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.ParseException;

public class CancelOrdersTabController {

    @FXML
    private TableColumn<OrderClient, String> date_col;

    @FXML
    private TableColumn<OrderClient, Integer> id_col;

    @FXML
    private TableColumn<OrderClient, String> name_receiver_col;

    @FXML
    private MFXLegacyTableView<OrderClient> cancel_orders_table;

    @FXML
    private TableColumn<OrderClient, String> phone_receiver_col;

    @FXML
    private TableColumn<OrderClient, String> time_col;

    ObservableList<OrderClient> items = FXCollections.observableArrayList();

    private MyAccountController my_account_page_holder;

    private static CancelOrdersTabController instance = null;

    public static CancelOrdersTabController getInstance(){
        if(instance == null){
            System.out.println("Error Occured");
        }
        return instance;
    }

    @FXML
    void initialize(){
        instance = this;
        TableInitializeFields();
        items = UserClient.getInstance().getOrderList();
//        for(int i=0; i< items.size(); i++){
//            System.out.println(items.get(i));
//        } printing the orders
        cancel_orders_table.setItems(UserClient.getInstance().getOrderList());
    }
    //
    public void TableInitializeFields() {
        cancel_orders_table.setFixedCellSize(40);
        id_col.setCellValueFactory(new PropertyValueFactory<OrderClient, Integer>("this_id"));
        date_col.setCellValueFactory(new PropertyValueFactory<OrderClient, String>("orderDate"));
        time_col.setCellValueFactory(new PropertyValueFactory<OrderClient, String>("orderTime"));
        phone_receiver_col.setCellValueFactory(new PropertyValueFactory<OrderClient, String>("phoneReceiver"));
        name_receiver_col.setCellValueFactory(new PropertyValueFactory<OrderClient, String>("nameReceiver"));

        cancel_orders_table.setRowFactory(s->{
            TableRow<OrderClient> row = new TableRow<OrderClient>();
            row.setOnMouseClicked(mouseEvent -> {
                        System.out.println(row.getItem());
                        try {
                            cancelOrderScreen(row);
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return row;
        });
    }
    public void cancelOrderScreen(TableRow<OrderClient> row) throws IOException, ParseException {
        OrderClient order = UserClient.getInstance().getOrderById(row.getItem().getThis_id());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("CancelOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 632 , 483);
        CancelOrderController controller = fxmlLoader.getController();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Cancel Order");
        stage.setScene(scene);
        stage.show();
        controller.initialize(order,stage);
    }

    public void RemoveOrderById(int id){
        for(int i=0; i<items.size(); i++){
            if(items.get(i).getThis_id() == id){
                this.items.remove(i);
                break;
            }
        }
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
