package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class EditAccountDetailsController {

    @FXML
    private MFXTextField addressBtn;

    @FXML
    private MFXButton creditcardBtn;

    @FXML
    private MFXButton passwordBtn;

    @FXML
    private MFXTextField phoneBtn;

    @FXML
    private MFXButton usernameBtn;

    MyAccountController accountController;

    @FXML
    void changeAddress(MouseEvent event) {

    }

    @FXML
    void changeCredit(MouseEvent event) {

    }

    @FXML
    void changeMail(MouseEvent event) {

    }

    @FXML
    void changePhone(MouseEvent event) {

    }

    @FXML
    void changeUsername(MouseEvent event) {

    }

    @FXML
    void mailBtn(ActionEvent event) {

    }

    @FXML
    void passwordChange(MouseEvent event) {

    }

    public MyAccountController getAccountController() {
        return accountController;
    }

    public void setAccountController(MyAccountController accountController) {
        this.accountController = accountController;
    }
}
