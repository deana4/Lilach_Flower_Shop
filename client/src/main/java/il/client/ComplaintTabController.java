package il.client;

import il.client.DiffClasses.Complaint;
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

public class ComplaintTabController {

    @FXML
    private TableColumn<Complaint, Integer> complaint_id_col;

    @FXML
    private TableColumn<Complaint, String> date_col;

    @FXML
    private TableColumn<Complaint, MFXButton> handle_col;

    @FXML
    private MFXLegacyTableView<Complaint> complaints_table;

    @FXML
    private TableColumn<Complaint, String> time_col;

    ObservableList<Complaint> items = FXCollections.observableArrayList();

    private MyAccountController my_account_page_holder;

    @FXML
    void initialize(){
        TableInitializeFields();
        items = UserClient.getInstance().getComplaintList();
        for(int i=0; i< items.size(); i++){
            System.out.println(items.get(i));
        }
        complaints_table.setItems(UserClient.getInstance().getComplaintList());
    }
    //
    public void TableInitializeFields() {
        complaints_table.setFixedCellSize(40);
        complaint_id_col.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("this_id"));
        date_col.setCellValueFactory(new PropertyValueFactory<Complaint, String>("complaintDate"));
        time_col.setCellValueFactory(new PropertyValueFactory<Complaint, String>("ComplaintTime"));

        complaints_table.setRowFactory(s->{
            TableRow<Complaint> row = new TableRow<Complaint>();
            row.setOnMouseClicked(mouseEvent -> {
                        System.out.println(row.getItem());
                        try {
                            HandelComplaintScreen(row);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return row;
        });
    }

    public void HandelComplaintScreen(TableRow<Complaint> row) throws IOException {
        Complaint complaint = UserClient.getInstance().getComplaintById(row.getItem().getThis_id());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ComplainHandler.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 698 , 794);
        ComplainHandlerController controller = fxmlLoader.getController();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Complaint Handler");
        stage.setScene(scene);
        stage.show();
        controller.initialize(complaint,stage);
    }

    /* gets and sets*/

    public MyAccountController getMy_account_page_holder() {
        return my_account_page_holder;
    }

    public void setMy_account_page_holder(MyAccountController my_account_page_holder) {
        this.my_account_page_holder = my_account_page_holder;
    }

    /*end sets and gets*/
}