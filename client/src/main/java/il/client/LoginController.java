package il.client;

import il.client.events.LoginEvent;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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


    @Subscribe
    public void compliteLogin(LoginEvent event){
        Platform.runLater(()->{
            if(event.getStatus()){
                //goto var which represent the login option on the Main Controller and change it to 1.
                //change Main Controller AnchorPane to Catalog -> "maybe return to the last page the client was inside"
                System.out.println(event.getResult());
                MainPageController.LoginName = event.getUsername();
                MainPageController.isLogin = true;
                try {
                    this.main_page_holder.UpdateMainController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.loginTries = 1;
            }
            else {
                System.out.println(event.getResult());
                }
        });
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException, InterruptedException {
        EventBus.getDefault().register(this);
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
            //correctLogin = (send msg to server - to find (string = username-password)
            // for specific worker id in the worker table
            //  return 'true' if username found and the password matches the username's id found
            // don't return list of workers, just return true or false according to the result)
        } else {
            LogInControl.logIn(username, password, isWorker);
//            if(username.equals("Dean") && password.equals("Wello")){
//                correctLogin = true;
//                priority.setPriority_level(2);
//                System.out.println("priority" + priority.getPriority_level());
//            }
            //correctLogin = (send msg to server - to find (string = username-password)
            // for specific client id in the client table
            //  return 'true' if username found and the password matches the username's id found
            // don't return list of clients, just return true or false according to the result)
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

