package il.client;

import il.client.controls.UserControl;
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

    private Stage stage;

    private boolean isWorker=false;

    private int person_id;

    private boolean worker = false;

    @FXML
    void initialize(Stage stage, boolean worker){
        this.worker = worker;
//        this.mailText.setVisible(false);
//        this.mailBtn.setVisible(false);
//        this.creditCardText.setVisible(false);
//        this.creditcardBtn.setVisible(false);
//        this.PhoneText.setVisible(false);
//        this.phoneBtn.setVisible(false);
//        this.freezeBtn.setVisible(false);
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
        System.out.println("Sytem Manager "+this.worker);
        if(this.worker){
            initWorker();
        }
        else{
            initClient();
        }


    }

    @FXML
    void changeCreditClicked(ActionEvent event) throws IOException {
        int correctness = checker.creditCardCheck(this.creditCardText.getText());
        if(correctness == 0){
            this.creditCardText.clear();
            this.creditCardText.setPromptText("Error");
        }
        else{
            //send to server
            UserControl.setCreditCard(getPerson_id(), this.creditCardText.getText(), isWorker());
            this.creditCardText.clear();
            this.creditCardText.setPromptText("Changed!");
        }
    }

    @FXML
    void changeMailClicked(ActionEvent event) throws IOException {
        int correctness = checker.mailCheck(this.mailText.getText());
        if(correctness == 0){
            this.mailText.clear();
            this.mailText.setPromptText("Error");
        }
        else{
            //send to server
            UserControl.setMail(getPerson_id(), this.mailText.getText(), isWorker());
            this.mailText.clear();
            this.mailText.setPromptText("Changed!");
        }
    }

    @FXML
    void changePhoneClicked(ActionEvent event) throws IOException {
        int correctness = checker.phoneCheck(this.PhoneText.getText());
        if(correctness == 0){
            this.PhoneText.clear();
            this.PhoneText.setPromptText("Error");
        }
        else{
            //send to server
            UserControl.setPhone(getPerson_id(), this.PhoneText.getText(), isWorker());
            this.PhoneText.clear();
            this.PhoneText.setPromptText("Changed!");
        }
    }

    @FXML
    void changeUsernameClicked(ActionEvent event) throws IOException {
        //check if the user name is already exist in DB
        //if(already in server){
        //this.usernameText.clear();
        //this.usernameText.setPromptText("This UserName is already exist);
        //}
        // else{
        //send to server
        UserControl.setUserName(getPerson_id(), this.usernameText.getText(), isWorker());
        this.usernameText.clear();
        this.usernameText.setPromptText("Changed!");
        //}
    }

    @FXML
    void freezeAccountClicked(ActionEvent event) throws IOException {
        UserControl.setAccountStatus(getPerson_id(), 0 , isWorker);
    }

    @FXML
    void passwordChangeClicked(ActionEvent event) throws IOException {
        int correctness = checker.passwordCheck(this.passwordText.getText());
        if(correctness == 0){
            this.passwordText.clear();
            this.passwordText.setPromptText("Error");
        }
        else{
            //send to server
            UserControl.setPassword(getPerson_id(), this.passwordText.getText(), isWorker());
            this.passwordText.clear();
            this.passwordText.setPromptText("Changed!");
        }
    }

    @FXML
    void permissionsBtnClicked(ActionEvent event) throws IOException {
        int correctness;
        if(isWorker) {
            correctness = checker.permissionCheck(this.permissionsText.getText(), "worker");
            System.out.println(correctness+ this.permissionsText.getText());

        } else{
            correctness = checker.permissionCheck(this.permissionsText.getText(), "user");
            System.out.println(correctness + " "+this.permissionsText.getText());
        }
        if(correctness == 0){
            this.permissionsText.clear();
            this.permissionsText.setPromptText("Error");
        }
        else{
            //send to server
            UserControl.setPermission(getPerson_id(), Integer.valueOf(this.permissionsText.getText()), isWorker());
            this.permissionsText.clear();
            this.permissionsText.setPromptText("Changed!");
        }
    }

    public void initWorker(){
        this.mailText.setEditable(false);
        this.creditCardText.clear();
        this.creditCardText.setPromptText(" ");
        this.creditCardText.setDisable(true);
        this.creditcardBtn.setDisable(true);
        this.PhoneText.clear();
        this.PhoneText.setPromptText(" ");
        this.PhoneText.setDisable(true);
        this.phoneBtn.setDisable(true);
        this.mailText.clear();
        this.mailText.setPromptText(" ");
        this.mailText.setDisable(true);
        this.mailBtn.setDisable(true);
        this.usernameText.setPromptText(getUsernameText());
        this.passwordText.setPromptText(getPasswordText());
        this.permissionsText.setPromptText(getPermissionsText());
        this.freezeBtn.setDisable(false);
        this.usernameText.setDisable(false);
        this.usernameBtn.setDisable(false);
        this.passwordText.setDisable(false);
        this.passwordBtn.setDisable(false);
        this.permissionsText.setDisable(false);
        this.permissionsBtn.setDisable(false);
        this.mailText.setVisible(false);
        this.mailBtn.setVisible(false);
        this.creditCardText.setVisible(false);
        this.creditcardBtn.setVisible(false);
        this.PhoneText.setVisible(false);
        this.phoneBtn.setVisible(false);
        this.freezeBtn.setVisible(false);
    }

    public void initClient() {
        this.creditCardText.clear();
        this.creditCardText.setPromptText(" ");
        this.creditCardText.setDisable(false);
        this.creditcardBtn.setDisable(false);
        this.PhoneText.clear();
        this.PhoneText.setPromptText(" ");
        this.PhoneText.setDisable(false);
        this.phoneBtn.setDisable(false);
        this.mailText.clear();
        this.mailText.setPromptText(" ");
        this.mailText.setDisable(false);
        this.mailBtn.setDisable(false);
        this.creditCardText.setPromptText(getCreditCardText());
        this.PhoneText.setPromptText(getPhoneText());
        this.mailText.setPromptText(getMailText());
        this.usernameText.setPromptText(getUsernameText());
        this.passwordText.setPromptText(getPasswordText());
        this.permissionsText.setPromptText(getPermissionsText());
        this.freezeBtn.setDisable(false);
        this.usernameText.setDisable(false);
        this.usernameBtn.setDisable(false);
        this.passwordText.setDisable(false);
        this.passwordBtn.setDisable(false);
        this.permissionsText.setDisable(false);
        this.permissionsBtn.setDisable(false);
        this.mailText.setVisible(true);
        this.mailBtn.setVisible(true);
        this.creditCardText.setVisible(true);
        this.creditcardBtn.setVisible(true);
        this.PhoneText.setVisible(true);
        this.phoneBtn.setVisible(true);
        this.freezeBtn.setVisible(true);
    }


    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }

    /* gets and sets */

    public String getPhoneText() {
        return PhoneText.getText();
    }

    public void setPhoneText(String phoneText) {
        PhoneText.setText(phoneText);
    }

    public String getCreditCardText() {
        return creditCardText.getText();
    }

    public void setCreditCardText(String creditCardText) {
        this.creditCardText.setText(creditCardText);
    }

    public String getMailText() {
        return mailText.getText();
    }

    public void setMailText(String mailText) {
        this.mailText.setText(mailText);
    }

    public String getPasswordText() {
        return passwordText.getText();
    }

    public void setPasswordText(String passwordText) {
        this.passwordText.setText(passwordText);
    }

    public String getPermissionsText() {
        return permissionsText.getText();
    }

    public void setPermissionsText(String permissionsText) {
        this.permissionsText.setText(permissionsText);
    }

    public String getUsernameText() {
        return usernameText.getText();
    }

    public void setUsernameText(String usernameText) {
        this.usernameText.setText(usernameText);
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public Stage getStage() { return stage; }

    public void setStage(Stage stage) { this.stage = stage; }

    /* end sets and gets*/
}
