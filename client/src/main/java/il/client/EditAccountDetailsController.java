package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class EditAccountDetailsController {

    @FXML
    private MFXTextField AddressText;

    @FXML
    private MFXButton addressBtn;

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
        this.mailText.setPromptText(UserClient.getInstance().getMail());
        this.AddressText.setPromptText(UserClient.getInstance().getAddress()); //need to check if they added the address field to User entity
    }

    @FXML
    void changeAddress(MouseEvent event) {

    }

    @FXML
    void changeCredit(MouseEvent event) {
        int correctness = checker.creditCardCheck(this.creditCardText.getText());
        if(correctness==0){
            this.creditCardText.clear();
            this.creditCardText.setPromptText("Error");
        }
        else{
            //send to server the change
            this.creditCardText.clear();
            this.creditCardText.setPromptText("Changed!");
        }
    }

    @FXML
    void changeMail(MouseEvent event) {
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
    void changePhone(MouseEvent event) {
        int correctness = checker.phoneCheck(this.phoneText.getText());
        if(correctness == 0){
            this.phoneText.clear();
            this.phoneText.setPromptText("Error");
        }
        else{
            //send to server
            this.phoneText.clear();
            this.phoneText.setPromptText("Changed!");
        }
    }

    @FXML
    void changeUsername(MouseEvent event) {
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
    void passwordChange(MouseEvent event) {
        int correctness = checker.passwordCheck(this.passwordText.getText());
        System.out.println(correctness);
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

    public MyAccountController getAccountController() {
        return accountController;
    }

    public void setAccountController(MyAccountController accountController) {
        this.accountController = accountController;
    }
}
