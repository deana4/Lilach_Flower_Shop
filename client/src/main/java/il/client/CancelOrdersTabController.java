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
import javafx.scene.paint.Color;
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
        for(int i=0; i< items.size(); i++){
            if(items.get(i).isCanceled())
                items.remove(i);
        }
        cancel_orders_table.setItems(items);
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
                            if(row.getItem() != null){
                                if(row.getItem().isCanceled()){
                                    alreadyCancelOrderScreen();
                                }else if(!row.getItem().isCanceled()){
                                    cancelOrderScreen(row);
                                }
                            }
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

        if(order.isCanceled()){
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AlreadyCanceledOrder.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 647 , 500);
            scene.setFill(Color.TRANSPARENT);
            AlreadyCanceledOrder controller = fxmlLoader.getController();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Already Canceled Order");
            stage.setScene(scene);
            stage.show();
            controller.initialize(stage);
        }
        else {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CancelOrder.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 647, 500);
            CancelOrderController controller = fxmlLoader.getController();
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Cancel Order");
            stage.setScene(scene);
            stage.show();
            controller.initialize(order, stage);
        }
    }

    public void alreadyCancelOrderScreen() throws IOException, ParseException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AlreadyCanceledOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 647 , 500);
        AlreadyCanceledOrder controller = fxmlLoader.getController();
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Cancel Order");
        stage.setScene(scene);
        stage.show();
        controller.initialize(stage);
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
