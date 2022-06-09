package il.client;

import il.entities.Employee;
import il.entities.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class PeopleListController {

    @FXML
    private TableColumn<User, String> full_name_col;

    @FXML
    private MFXLegacyTableView<User> people_table;

    @FXML
    private TableColumn<User, String> user_name_col;

    @FXML
    private MFXToggleButton toggleWorker;

    private SystemManagerController controller;

    private int clicks=0;


    @FXML
    private AnchorPane anchorpane_chooser;

    @FXML
    private AnchorPane anchorpane_clients;

    @FXML
    private AnchorPane anchorpane_employees;

    @FXML
    private MFXButton employeeBTN;

    @FXML
    private MFXLegacyTableView<Employee> employee_table;

    @FXML
    private TableColumn<Employee, String> employee_user_name_col;

    @FXML
    private TableColumn<Employee, String> full_employee_name_col;

    ObservableList<User> allClients = FXCollections.observableArrayList();

    ObservableList<Employee> allEmployees = FXCollections.observableArrayList();

    private MyAccountController my_account_page_holder;

    private boolean isWorker=false;


    @FXML
    private MFXButton go_back_userBTN;

    @FXML
    private MFXButton go_back_workerBTN;


    @FXML
    void initialize(){

        this.anchorpane_chooser.setVisible(true);
        this.anchorpane_clients.setVisible(false);
        this.anchorpane_employees.setVisible(false);

//        TableInitializeFields();
//        System.out.println("printring the client in PeopleList");
//        for(int i=0; i< UserClient.getInstance().getAllusers().size(); i++){
//            allClients.add(UserClient.getInstance().getAllusers().get(i));
//        }
//        people_table.setItems(allClients);
    }

    public void TableInitializeFields() {
        people_table.setFixedCellSize(40);
        full_name_col.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        user_name_col.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));

        people_table.setRowFactory(s->{
            TableRow<User> row = new TableRow<User>();
            row.setOnMouseClicked(mouseEvent -> {
                        System.out.println("in PeopleListController "+row.getItem());
                        try {
                            if(row.getItem() != null){initPersonDetails(row);}
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return row;
        });
    }

    public void TableEmployeeInitializeFields() {
        employee_table.setFixedCellSize(40);
        full_employee_name_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        employee_user_name_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));

        employee_table.setRowFactory(s->{
            TableRow<Employee> row = new TableRow<Employee>();
            row.setOnMouseClicked(mouseEvent -> {
                        System.out.println("in PeopleListController "+row.getItem());
                        try {
                            if(row.getItem() != null){initWorkerDetails(row);}
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return row;
        });
    }

    public void initPersonDetails(TableRow<User> row) throws IOException, ParseException {
        //UserClient person = UserClient.getInstance();
        //init the fields of SystemManagerController by the details of the person we just clicked on his row
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SystemManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500  , 534);
        SystemManagerController controller = fxmlLoader.getController();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Change Person Details");
        stage.setScene(scene);
        controller.initialize(stage,isWorker);
        controller.setCreditCardText(row.getItem().getCreditCard());
        controller.setMailText(row.getItem().getMail());
        controller.setPhoneText(row.getItem().getPhone());
        controller.setWorker(false);
        controller.setPasswordText(row.getItem().getPassword());
        controller.setUsernameText(row.getItem().getUserName());
        controller.setPermissionsText(Integer.toString(row.getItem().getPriority()));
        controller.setPerson_id(row.getItem().getId());
        controller.setStage(stage);
        stage.show();
    }

    public void initWorkerDetails(TableRow<Employee> row) throws IOException, ParseException {
        //UserClient person = UserClient.getInstance();
        //init the fields of SystemManagerController by the details of the person we just clicked on his row
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SystemManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500 , 534);
        SystemManagerController controller = fxmlLoader.getController();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Change Person Details");
        stage.setScene(scene);
        controller.initialize(stage,isWorker);
        controller.setWorker(true);
        controller.setPasswordText(row.getItem().getPassword());
        controller.setUsernameText(row.getItem().getUsername());
        controller.setPermissionsText(Integer.toString(row.getItem().getPermission()));
        controller.setPerson_id(row.getItem().getId());
        controller.setStage(stage);
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

    @FXML
    void EmployeeBTNClicked(ActionEvent event) {
        this.anchorpane_chooser.setVisible(false);
        this.anchorpane_clients.setVisible(false);
        this.anchorpane_employees.setVisible(true);

        TableEmployeeInitializeFields();
        for(int i=0; i<UserClient.getInstance().getEmployees().size(); i++){
            allEmployees.add(UserClient.getInstance().getEmployees().get(i));
        }
        isWorker = true;
        employee_table.setItems(allEmployees);

    }

    @FXML
    void UserBTNClicked(ActionEvent event) {
        this.anchorpane_chooser.setVisible(false);
        this.anchorpane_clients.setVisible(true);
        this.anchorpane_employees.setVisible(false);

        TableInitializeFields();
        System.out.println("printing the client in PeopleList");
        for(int i=0; i< UserClient.getInstance().getAllusers().size(); i++){
            allClients.add(UserClient.getInstance().getAllusers().get(i));
        }
        isWorker = false;
        people_table.setItems(allClients);
    }


    @FXML
    void GoBackUserClicked(ActionEvent event) {

        this.anchorpane_chooser.setVisible(true);
        this.anchorpane_clients.setVisible(false);
        this.anchorpane_employees.setVisible(false);
    }

    @FXML
    void GoBackWorkerClicked(ActionEvent event) {

        this.anchorpane_chooser.setVisible(true);
        this.anchorpane_clients.setVisible(false);
        this.anchorpane_employees.setVisible(false);
    }

    /* gets and sets */

    public void setMy_account_page_holder(MyAccountController my_account_page_holder) {
        this.my_account_page_holder = my_account_page_holder;
    }

    /*end gets and sets*/

}

