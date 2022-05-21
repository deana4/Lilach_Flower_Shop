package il.client;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import il.client.DiffClasses.Priority;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;

public class MainPageController extends ParentClass{
    @FXML
    private MFXButton myacc_btn;

    @FXML
    private MFXButton mycart_btn;

    @FXML
    private MFXButton Logout_btn;

    @FXML
    private MFXButton login_btn;

    @FXML
    private AnchorPane main_first_load_pane;

    @FXML
    private MFXButton register_btn;

    @FXML
    private MFXTextField user_wellcome;

    public static boolean isLogin; //this bool var indicates that the user logged in

    public static String LoginName;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
        init_priority();
    }

    @FXML
    void HomeBTNClicked(MouseEvent event) throws IOException {
        LoadPage("Home.fxml");
    }

    @FXML
    void LoginBTNClicked(ActionEvent event) throws IOException {
        this.main_first_load_pane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL var = getClass().getResource("Login.fxml");
        fxmlLoader.setLocation(var);
        Parent root = fxmlLoader.load();
        LoginController controller = fxmlLoader.getController();
        controller.setMain_page_holder(this);
        this.main_first_load_pane.getChildren().addAll(root);
    }

    @FXML
    void ChangeScreen(String screen){

    }

    @FXML
    void RegisterBTNClicked(ActionEvent event) throws IOException, JSONException {
        LoadPage("Register.fxml");
    }

    @FXML
    void CatalogBTNClicked(ActionEvent event) throws IOException {
        this.main_first_load_pane.getChildren().clear();
        FXMLLoader homeLoader = new FXMLLoader();
        URL home_var = getClass().getResource("Catalog.fxml");
        homeLoader.setLocation(home_var);
        Parent home_root = homeLoader.load();
        CatalogController home_controller = homeLoader.getController();
        this.main_first_load_pane.getChildren().addAll(home_root);
    }

    @FXML
    void LogoutBTNClicked(ActionEvent event) throws IOException {
        {
            this.login_btn.setVisible(true);
            this.register_btn.setVisible(true);
            this.user_wellcome.setVisible(false);
            this.user_wellcome.clear();
            this.myacc_btn.setVisible(false);
            this.mycart_btn.setVisible(false);
            this.Logout_btn.setVisible(false);
            this.setLogin(false);
            this.setLoginName("Default");
        }

        priority.setPriority_level(1);

        LoadPage("Home.fxml");
    }

    public void LoadPage(String string) throws IOException {  //fxml pages loader on the main page.
        this.main_first_load_pane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL var = getClass().getResource(string);
        fxmlLoader.setLocation(var);
        Parent root = fxmlLoader.load();
        this.main_first_load_pane.getChildren().addAll(root);
    }


    /* Setters and Getters */
    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    /* END Setters and Getters */





    /* Special Functions */
    public void UpdateMainController() throws IOException { //check permissions
        if(this.isLogin()){
            this.login_btn.setVisible(false);
            this.register_btn.setVisible(false);
            this.user_wellcome.setVisible(true);
            this.user_wellcome.setText("Welcome, " + this.LoginName /* Get Last Login Name */);
            this.myacc_btn.setVisible(true);
            this.mycart_btn.setVisible(true);
            this.Logout_btn.setVisible(true);
            LoadPage("Home.fxml");
        }else{
            return;
        }
        return;
    }
    /* END */
}


