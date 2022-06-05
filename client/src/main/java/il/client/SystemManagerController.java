package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SystemManagerController {

    @FXML
    private MFXButton addressBtn;

    @FXML
    private MFXTextField addressText;

    @FXML
    private MFXTextField PhoneText;

    @FXML
    private MFXTextField creditCardText;

    @FXML
    private MFXButton creditcardBtn;

    @FXML
    private MFXButton freezeBtn;

    @FXML
    private MFXButton mailBtn;

    @FXML
    private MFXTextField mailText;

    @FXML
    private MFXButton passwordBtn;

    @FXML
    private MFXTextField passwordText;

    @FXML
    private MFXButton permissionsBtn;

    @FXML
    private MFXTextField permissionsText;

    @FXML
    private MFXButton phoneBtn;

    @FXML
    private MFXButton pickUserBtn;

    @FXML
    private MFXToggleButton toggleWorker;

    @FXML
    private MFXButton usernameBtn;

    @FXML
    private MFXTextField usernameText;

    private DetailsChecker checker = new DetailsChecker();

    @FXML
    void initialize(){
//        this.usernameText.setPromptText(UserClient.getInstance().getUserName());
//        this.passwordText.setPromptText(UserClient.getInstance().getPassword());
//        this.creditCardText.setPromptText(UserClient.getInstance().getCreditCard());
//        this.PhoneText.setPromptText(UserClient.getInstance().getPhone());
//        this.mailText.setPromptText(UserClient.getInstance().getMail());
//        this.addressText.setPromptText(UserClient.getInstance().getAddress()); //need to check if they added the address field to User entity
//        this.permissionsText.setPromptText(String.valueOf(UserClient.getInstance().getPriority()));
//        if(UserClient.getInstance().isWorker()){
//            this.toggleWorker.setSelected(true);
//            this.creditCardText.setText("");
//            this.creditCardText.setDisable(true);
//            this.creditcardBtn.setDisable(true);
//            this.addressText.setDisable(true);
//            this.addressText.setText("");
//            this.addressBtn.setDisable(true);
//        }
        this.freezeBtn.setDisable(true);
        this.usernameText.setDisable(true);
        this.usernameBtn.setDisable(true);
        this.passwordText.setDisable(true);
        this.passwordBtn.setDisable(true);
        this.creditCardText.setDisable(true);
        this.creditcardBtn.setDisable(true);
        this.PhoneText.setDisable(true);
        this.phoneBtn.setDisable(true);
        this.addressText.setDisable(true);
        this.addressBtn.setDisable(true);
        this.mailText.setDisable(true);
        this.mailBtn.setDisable(true);
        this.permissionsText.setDisable(true);
        this.permissionsBtn.setDisable(true);
    }

    @FXML
    void changeAddressClicked(ActionEvent event) {

    }

    @FXML
    void changeCreditClicked(ActionEvent event) {
        int correctness = checker.creditCardCheck(this.creditCardText.getText());
        if(correctness == 0){
            this.creditCardText.clear();
            this.creditCardText.setPromptText("Error");
        }
        else{
            //send to server
            this.creditCardText.clear();
            this.creditCardText.setPromptText("Changed!");
        }
    }

    @FXML
    void changeMailClicked(ActionEvent event) {
        int correctness = checker.mailCheck(this.mailText.getText());
        if(correctness == 0){
            this.mailText.clear();
            this.mailText.setPromptText("Error");
        }
        else{
            //send to server
            this.mailText.clear();
            this.mailText.setPromptText("Changed!");
        }
    }

    @FXML
    void changePhoneClicked(ActionEvent event) {
        int correctness = checker.phoneCheck(this.PhoneText.getText());
        if(correctness == 0){
            this.PhoneText.clear();
            this.PhoneText.setPromptText("Error");
        }
        else{
            //send to server
            this.PhoneText.clear();
            this.PhoneText.setPromptText("Changed!");
        }
    }

    @FXML
    void changeUsernameClicked(ActionEvent event) {
        //check if the user name is already exist in DB
        //if(already in server){
        //this.usernameText.clear();
        //this.usernameText.setPromptText("This UserName is already exist);
        //}
        // else{
        //send to server
        this.usernameText.clear();
        this.usernameText.setPromptText("Changed!");
        //}
    }

    @FXML
    void freezeAccountClicked(ActionEvent event) {

    }

    @FXML
    void passwordChangeClicked(ActionEvent event) {
        int correctness = checker.passwordCheck(this.passwordText.getText());
        if(correctness == 0){
            this.passwordText.clear();
            this.passwordText.setPromptText("Error");
        }
        else{
            //send to server
            this.passwordText.clear();
            this.passwordText.setPromptText("Changed!");
        }
    }

    @FXML
    void permissionsBtnClicked(ActionEvent event) {
        int correctness;
        if(toggleWorker.isSelected()) {
            correctness = checker.permissionCheck(this.permissionsText.getText(), "worker");
        } else{
            correctness = checker.permissionCheck(this.permissionsText.getText(), "user");
        }
        if(correctness == 0){
            this.permissionsText.clear();
            this.permissionsText.setPromptText("Error");
        }
        else{
            //send to server
            this.permissionsText.clear();
            this.permissionsText.setPromptText("Changed!");
        }
    }

    @FXML
    void userPickerClicked(ActionEvent event) throws IOException {
        //the init of the buttons should be in the people list by the chosen one. not here!!!
        this.freezeBtn.setDisable(false);
        this.usernameText.setDisable(false);
        this.usernameBtn.setDisable(false);
        this.passwordText.setDisable(false);
        this.passwordBtn.setDisable(false);
        this.permissionsText.setDisable(false);
        this.permissionsBtn.setDisable(false);
        this.usernameText.setPromptText(UserClient.getInstance().getUserName());
        this.passwordText.setPromptText(UserClient.getInstance().getPassword());
        this.permissionsText.setPromptText(String.valueOf(UserClient.getInstance().getPriority()));
        if (toggleWorker.isSelected()) {
            this.creditCardText.clear();
            this.creditCardText.setPromptText(" ");
            this.creditCardText.setDisable(true);
            this.creditcardBtn.setDisable(true);
            this.addressText.clear();
            this.addressText.setPromptText(" ");
            this.addressText.setDisable(true);
            this.addressBtn.setDisable(true);
            this.PhoneText.clear();
            this.PhoneText.setPromptText(" ");
            this.PhoneText.setDisable(true);
            this.phoneBtn.setDisable(true);
            this.mailText.clear();
            this.mailText.setPromptText(" ");
            this.mailText.setDisable(true);
            this.mailBtn.setDisable(true);
        } else {
            this.creditCardText.clear();
            this.creditCardText.setPromptText(" ");
            this.creditCardText.setDisable(false);
            this.creditcardBtn.setDisable(false);
            this.addressText.clear();
            this.addressText.setPromptText(" ");
            this.addressText.setDisable(false);
            this.addressBtn.setDisable(false);
            this.PhoneText.clear();
            this.PhoneText.setPromptText(" ");
            this.PhoneText.setDisable(false);
            this.phoneBtn.setDisable(false);
            this.mailText.clear();
            this.mailText.setPromptText(" ");
            this.mailText.setDisable(false);
            this.mailBtn.setDisable(false);
            this.creditCardText.setPromptText(UserClient.getInstance().getCreditCard());
            this.PhoneText.setPromptText(UserClient.getInstance().getPhone());
            this.mailText.setPromptText(UserClient.getInstance().getMail());
            this.addressText.setPromptText(UserClient.getInstance().getAddress());


            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PeopleList.fxml"));
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(fxmlLoader.load(), 699, 644);
            scene.setFill(Color.TRANSPARENT);
            PeopleListController controller = fxmlLoader.getController();
            controller.initialize(this);
            controller.setStage(stage);
            stage.setTitle("People List");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void workerToggleClicked(MouseEvent event) {
        this.freezeBtn.setDisable(true);
        this.usernameText.setDisable(true);
        this.usernameBtn.setDisable(true);
        this.passwordText.setDisable(true);
        this.passwordBtn.setDisable(true);
        this.creditCardText.setDisable(true);
        this.creditcardBtn.setDisable(true);
        this.PhoneText.setDisable(true);
        this.phoneBtn.setDisable(true);
        this.addressText.setDisable(true);
        this.addressBtn.setDisable(true);
        this.mailText.setDisable(true);
        this.mailBtn.setDisable(true);
        this.permissionsText.setDisable(true);
        this.permissionsBtn.setDisable(true);
    }

}
