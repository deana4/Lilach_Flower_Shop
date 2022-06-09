package il.client;

import il.client.controls.UserControl;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class EditAccountDetailsController {


    @FXML
    private MFXTextField creditCardText;

    @FXML
    private MFXButton creditcardBtn;

    @FXML
    private MFXButton mailBtn;

    @FXML
    private MFXTextField mailText;

    @FXML
    private MFXButton passwordBtn;

    @FXML
    private MFXTextField passwordText;

    @FXML
    private MFXButton phoneBtn;

    @FXML
    private MFXTextField phoneText;

    @FXML
    private MFXButton usernameBtn;

    @FXML
    private MFXTextField usernameText;

    private DetailsChecker checker = new DetailsChecker();

    private MyAccountController accountController;

    @FXML
    void initialize(){
        this.usernameText.setPromptText(UserClient.getInstance().getUserName());
        this.passwordText.setPromptText(UserClient.getInstance().getPassword());
        this.creditCardText.setPromptText(UserClient.getInstance().getCreditCard());
        this.phoneText.setPromptText(UserClient.getInstance().getPhone());
        this.mailText.setPromptText(UserClient.getInstance().getMail()); //ido needs to put default there
    }

    @FXML
    void changeCredit(MouseEvent event) throws IOException {
        int correctness = checker.creditCardCheck(this.creditCardText.getText());
        if(correctness==0){
            this.creditCardText.clear();
            this.creditCardText.setPromptText("Error");
        }
        else{
            //send to server the change
            UserControl.setCreditCard(UserClient.getInstance().getId(), this.creditCardText.getText(), UserClient.getInstance().isWorker());
            UserClient.getInstance().setCreditCard(this.creditCardText.getText());
            this.creditCardText.clear();
            this.creditCardText.setPromptText("Changed!");

        }
    }

    @FXML
    void changeMail(MouseEvent event) throws IOException {
        int correctness = checker.mailCheck(this.mailText.getText());
        if(correctness == 0){
            this.mailText.clear();
            this.mailText.setPromptText("Error");
        }
        else{
            //send to server
            UserControl.setMail(UserClient.getInstance().getId(), this.mailText.getText(), UserClient.getInstance().isWorker());
            UserClient.getInstance().setMail(this.mailText.getText());
            this.mailText.clear();
            this.mailText.setPromptText("Changed!");

        }
    }

    @FXML
    void changePhone(MouseEvent event) throws IOException {
        int correctness = checker.phoneCheck(this.phoneText.getText());
        if(correctness == 0){
            this.phoneText.clear();
            this.phoneText.setPromptText("Error");
        }
        else{
            //send to server
            UserControl.setPhone(UserClient.getInstance().getId(), this.phoneText.getText(),UserClient.getInstance().isWorker());
            UserClient.getInstance().setPhone(this.phoneText.getText());
            this.phoneText.clear();
            this.phoneText.setPromptText("Changed!");
        }
    }

    @FXML
    void changeUsername(MouseEvent event) throws IOException {
        //check if the user name is already exist in DB
        //if(already in server){
        //this.usernameText.clear();
        //this.usernameText.setPromptText("This UserName is already exist);
        //}
        // else{
        //send to server
        UserControl.setUserName(UserClient.getInstance().getId(), this.usernameText.getText(), UserClient.getInstance().isWorker());
        UserClient.getInstance().setUserName(this.usernameText.getText());
        this.usernameText.clear();
        this.usernameText.setPromptText("Changed!");
        //}
    }

    @FXML
    void passwordChange(MouseEvent event) throws IOException {
        int correctness = checker.passwordCheck(this.passwordText.getText());
        if(correctness == 0){
            this.passwordText.clear();
            this.passwordText.setPromptText("Error");
        }
        else{
            //send to server
            UserControl.setPassword(UserClient.getInstance().getId(), this.passwordText.getText(),UserClient.getInstance().isWorker());
            UserClient.getInstance().setPassword(this.passwordText.getText());
            this.passwordText.clear();
            this.passwordText.setPromptText("Changed!");
        }
    }

    public MyAccountController getAccountController() {
        return accountController;
    }

    public void setAccountController(MyAccountController accountController) {
        this.accountController = accountController;
    }
}
