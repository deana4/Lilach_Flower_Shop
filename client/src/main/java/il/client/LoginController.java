package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.fxml.FXML;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController extends ParentClass{

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

    public static void setCorrectLogin(boolean b) {
        correctLogin = b;
    }


    @FXML
    void LoginSumbitted(ActionEvent event) throws IOException {
        String username = username_field.getText();
        String password = password_field.getText();
        boolean usernameFound,passwordFound;  //this vars will get '1' value if we found the correct values in the DB
        usernameFound = passwordFound = false;

        if(username == "" || password == ""){ // taking care of null strings
            password_field.clear();
            username_field.setText("Username Or Password Empty");
        }else if(password.contains("-")){

        }
        if(isWorker){
            LogInControl.logIn(username, password, isWorker);
//            UserClient.getInstance().setUserByServer(UserClient.getInstance().getUserServer());
            //correctLogin = (send msg to server - to find (string = username-password)
            // for specific worker id in the worker table
            //  return 'true' if username found and the password matches the username's id found
            // don't return list of workers, just return true or false according to the result)
        } else {
            LogInControl.logIn(username, password, isWorker);
//            UserClient.getInstance().setUserByServer(UserClient.getInstance().getUserServer());
            if(username.equals("Dean") && password.equals("Wello")){
                correctLogin = true;
                System.out.println("priority" + UserClient.getInstance().getPriority());
                UserClient.getInstance().TestLoginFunction(1,"Dean","Wello", "4580458045804580", "Dean Amar", "Super Worker", 1000, "Deana1704@gmail.com");
                if(UserClient.getInstance().getPosition().equals("Super Worker")){
                    UserClient.getInstance().setPriority(2);
                }
            }
            //correctLogin = (send msg to server - to find (string = username-password)
            // for specific client id in the client table
            //  return 'true' if username found and the password matches the username's id found
            // don't return list of clients, just return true or false according to the result)
        }
        if(correctLogin){
            //goto var which represent the login option on the Main Controller and change it to 1.
            //change Main Controller AnchorPane to Catalog -> "maybe return to the last page the client was inside"
            MainPageController.LoginName = username;
            MainPageController.isLogin = true;
            MainPageController.getInstance().CatalogRefresh(); //Catalog, MyAccount, Cart
            this.main_page_holder.UpdateMainController();
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

