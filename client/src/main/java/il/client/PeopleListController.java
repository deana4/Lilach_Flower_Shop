package il.client;

import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class PeopleListController {

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private TableColumn<UserClient, String> full_name_col;

    @FXML
    private MFXLegacyTableView<UserClient> people_table;

    @FXML
    private TableColumn<UserClient, String> user_name_col;

    private SystemManagerController controller;

    private Stage stage;

    @FXML
    void initialize(SystemManagerController controller){
        TableInitializeFields();
    }

    public void TableInitializeFields() {
        people_table.setFixedCellSize(40);
        full_name_col.setCellValueFactory(new PropertyValueFactory<UserClient, String>("name"));
        user_name_col.setCellValueFactory(new PropertyValueFactory<UserClient, String>("userName"));

        people_table.setRowFactory(s->{
            TableRow<UserClient> row = new TableRow<UserClient>();
            row.setOnMouseClicked(mouseEvent -> {
                        System.out.println("in PeopleListController "+row.getItem());
                        try {
                            initPersonDetails(row);
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return row;
        });
    }

    public void initPersonDetails(TableRow<UserClient> row) throws IOException, ParseException {
        //UserClient person = UserClient.getInstance();
        //init the fields of SystemManagerController by the details of the person we just clicked on his row
    }

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }

    /* gets and sets */

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /*end gets and sets*/

}
