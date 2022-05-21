package il.client;

import il.client.MainPageController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.json.JSONException;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class LoginController {

    @FXML
    private MFXToggleButton workerChecker;

    @FXML
    private MFXButton loginBTN;

    @FXML
    private MFXPasswordField password_field;

    @FXML
    private MFXTextField username_field;

    private MainPageController main_page_holder;

    private boolean isWorker;

    private static boolean correctLogin;

    private int loginTries = 1;

    public static void setCorrectLogin(boolean correctLogin_){
        correctLogin = correctLogin_;
    }

    @FXML
    void LoginSumbitted(ActionEvent event) throws IOException, JSONException {
        String username = username_field.getText();
        String password = password_field.getText();
        boolean usernameFound,passwordFound;  //this vars will get '1' value if we found the correct values in the DB
        usernameFound = passwordFound = false;

        if(username == "" || password == ""){ // taking care of null strings
            password_field.clear();
            username_field.setText("Username Or Password Empty");
        }else if(password.contains("-")){

        }
        LogInControl.logIn(username, password, isWorker);


        //runLater

        System.out.println("");
        if(correctLogin){
            //goto var which represent the login option on the Main Controller and change it to 1.
            //change Main Controller AnchorPane to Catalog -> "maybe return to the last page the client was inside"
            MainPageController.LoginName = username;
            MainPageController.isLogin = true;
            try {
                this.main_page_holder.UpdateMainController();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.loginTries = 1;
        }else {
            password_field.clear();
            username_field.setText("Username Or Password incorrect");
            this.loginTries++;
            if(this.loginTries == 6){
                System.out.println(this.loginTries);
                TranslateTransition transition = new TranslateTransition();
                transition.setDuration(Duration.millis(60000)); //1 minute
                transition.setNode(loginBTN);
                transition.setAutoReverse(false);
                loginBTN.setDisable(true);
                loginBTN.setText("Waiting 1 Minute");
                transition.setOnFinished(evt -> {
                    loginBTN.setDisable(false);
                    loginBTN.setText("Submit");
                }); //disable the submit button for 1 minute

                transition.play();
            }
        }

    }

    public Button getLoginBTN() {
        return loginBTN;
    }

    @FXML
    void WorkerChecked(MouseEvent event) {
        this.isWorker = workerChecker.isSelected(); //if the GUI user checked for 'worker login' return 'true'
    }

    /* Getters and Setters */

    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }
    /*END*/

}

