package il.client;

import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.ParseException;

public class PeopleListController {

    @FXML
    private TableColumn<UserClient, String> full_name_col;

    @FXML
    private MFXLegacyTableView<UserClient> people_table;

    @FXML
    private TableColumn<UserClient, String> user_name_col;

    @FXML
    private MFXToggleButton toggleWorker;

    private SystemManagerController controller;

    private int clicks=0;


    @FXML
    void initialize(SystemManagerController controller){
        TableInitializeFields();
 //     people_table.setItems(/* gets the user table*/);
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
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SystemManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 437 , 473);
        SystemManagerController controller = fxmlLoader.getController();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Change Person Details");
        stage.setScene(scene);
        controller.initialize(stage);
        if(!this.toggleWorker.isSelected()) {
            controller.setCreditCardText(row.getItem().getCreditCard());
            controller.setMailText(row.getItem().getMail());
            controller.setPhoneText(row.getItem().getPhone());
            controller.setWorker(false);
        }else{ controller.setWorker(true); }

        controller.setPasswordText(row.getItem().getPassword());
        controller.setUsernameText(row.getItem().getUserName());
        controller.setPermissionsText(Integer.toString(row.getItem().getPlan()));
        controller.setPerson_id(row.getItem().getId());

        stage.show();
    }

    @FXML
    void workerToggleClicked(MouseEvent event) {
        clicks++;
        if(clicks%2==1){
         people_table.getItems().clear();
         TableInitializeFields();
//       people_table.setItems(/* gets the employee table*/);
        }
        else{
         people_table.getItems().clear();
         TableInitializeFields();
//       people_table.setItems(/* gets the user table*/);
        }
    }

    /* gets and sets */

    /*end gets and sets*/

}
