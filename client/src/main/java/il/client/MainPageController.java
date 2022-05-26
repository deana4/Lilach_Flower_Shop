package il.client;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import il.client.UserClient;
import org.json.JSONException;

public class MainPageController extends ParentClass {     //This is a singleton Class which provides the system abilities of changing screens, refresh the system etc.
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

    private HashMap<String, Parent> root_map = new HashMap<String, Parent>();  //Hashmap of roots by names

    private HashMap<String,Object> controller_map = new HashMap<String,Object>(); //Hashmap of Controllers by names

    private UserClient user;

    private static MainPageController instance = null;

    public static MainPageController getInstance(){
        if(instance == null){
            System.out.println("Error Occured");
        }
        return instance;
    }

    public void SetUser(){
        user = UserClient.getInstance();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
        instance = this;
        SetUser();
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("AddToCart.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            AddToCartController controller = fxmlLoader.getController();
            root_map.put("Cart",root);
            controller_map.put("Cart",controller);
            ((AddToCartController)controller_map.get("Cart")).setMain_page_holder(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("Login.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            LoginController controller = fxmlLoader.getController();
            root_map.put("Login",root);
            controller_map.put("Login",controller);
            ((LoginController)controller_map.get("Login")).setMain_page_holder(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("Register.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            RegisterController controller = fxmlLoader.getController();
            root_map.put("Register",root);
            controller_map.put("Register",controller);
            ((RegisterController)controller_map.get("Register")).setMain_controller(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("Catalog.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            CatalogController controller = fxmlLoader.getController();
            root_map.put("Catalog",root);
            controller_map.put("Catalog",controller);
            ((CatalogController)controller_map.get("Catalog")).setMain_page_holder(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("Order.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            OrderController controller = fxmlLoader.getController();
            root_map.put("Order",root);
            controller_map.put("Order",controller);
            ((OrderController)controller_map.get("Order")).setMain_page_holder(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("Home.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            HomeController controller = fxmlLoader.getController();
            root_map.put("Home",root);
            controller_map.put("Home",controller);
            ((HomeController)controller_map.get("Home")).setMain_page_holder(this);
        }

    }


/*-------------------------------------- Response Functions-------------------------------------- */
    @FXML
    void HomeBTNClicked(MouseEvent event) throws IOException {
        LoadHomePage();
    }

    @FXML
    void LoginBTNClicked(ActionEvent event) throws IOException {
        LoadLoginPage();
    }

    @FXML
    void MyCartBTNClicked(ActionEvent event) throws IOException {
        LoadCartPage();
    }

    @FXML
    void RegisterBTNClicked(ActionEvent event) throws IOException, JSONException {
        LoadRegisterPage();
    }

    @FXML
    void CatalogBTNClicked(ActionEvent event) throws IOException {
        LoadCatalogPage();
    }

    @FXML
    void MyAccBTNClicked(ActionEvent event) throws IOException { //Need to be changed
        this.main_first_load_pane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL var = getClass().getResource("Complain.fxml");
        fxmlLoader.setLocation(var);
        Parent root = fxmlLoader.load();
        ComplainController controller = fxmlLoader.getController();
        controller.setMain_page_holder(this);
        this.main_first_load_pane.getChildren().addAll(root);
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
        UserClient.getInstance().setPriority(1);
        MainPageController.getInstance().Refresh();

        LoadHomePage();
    }

    @FXML
    void Refresh(MouseEvent event) throws IOException {
        Refresh();
    }

    /* -------------------------------------- Setters and Getters -------------------------------------- */

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

    /*-------------------------------------- Special Functions --------------------------------------*/
    public void UpdateMainController() throws IOException { //check permissions
        if(this.isLogin()){
            this.login_btn.setVisible(false);
            this.register_btn.setVisible(false);
            this.user_wellcome.setVisible(true);
            this.user_wellcome.setText("Welcome, " + this.LoginName /* Get Last Login Name */);
            this.myacc_btn.setVisible(true);
            this.mycart_btn.setVisible(true);
            this.Logout_btn.setVisible(true);
            LoadHomePage();
        }else{
            return;
        }
        return;
    }
    /*  ---------------------------------------  Screen Loaders  --------------------------------------- */
    public void Refresh() throws IOException {
        System.out.println("REFRESHING SYSTEM START");
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        LoginRefresh();
        AddToCartRefresh();
        RegisterRefresh();
        CatalogRefresh();
        OrderRefresh();
        HomeRefresh();
        System.out.println("REFRESHING SYSTEM FINISHED");
        LoadHomePage();
    }
    public void LoginRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("Login.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        LoginController controller = fxmlLoader.getController();
        root_map.remove("Login");
        root_map.put("Login",root);
        controller_map.remove("Login");
        controller_map.put("Login",controller);
        ((LoginController)controller_map.get("Login")).setMain_page_holder(this);
    }

    public void AddToCartRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("AddToCart.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        AddToCartController controller = fxmlLoader.getController();
        root_map.put("Cart",root);
        controller_map.put("Cart",controller);
        ((AddToCartController)controller_map.get("Cart")).setMain_page_holder(this);
    }
    public void RegisterRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("Register.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        RegisterController controller = fxmlLoader.getController();
        root_map.put("Register",root);
        controller_map.put("Register",controller);
        ((RegisterController)controller_map.get("Register")).setMain_controller(this);
    }
    public void CatalogRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("Catalog.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        CatalogController controller = fxmlLoader.getController();
        root_map.put("Catalog",root);
        controller_map.put("Catalog",controller);
        ((CatalogController)controller_map.get("Catalog")).setMain_page_holder(this);
    }
    public void OrderRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("Order.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        OrderController controller = fxmlLoader.getController();
        root_map.put("Order",root);
        controller_map.put("Order",controller);
        ((OrderController)controller_map.get("Order")).setMain_page_holder(this);
    }
    public void HomeRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("Home.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        HomeController controller = fxmlLoader.getController();
        root_map.put("Home",root);
        controller_map.put("Home",controller);
        ((HomeController)controller_map.get("Home")).setMain_page_holder(this);
    }

    public void LoadLoginPage() throws IOException {
        this.main_first_load_pane.getChildren().clear();
        ((LoginController)controller_map.get("Login")).setMain_page_holder(this);
        this.main_first_load_pane.getChildren().addAll(root_map.get("Login"));
    }
    public void LoadCartPage() throws IOException {
        this.main_first_load_pane.getChildren().clear();
        ((AddToCartController)controller_map.get("Cart")).setMain_page_holder(this);
        this.main_first_load_pane.getChildren().addAll(root_map.get("Cart"));
    }
    public void LoadRegisterPage() throws IOException {
        this.main_first_load_pane.getChildren().clear();
        ((RegisterController)controller_map.get("Register")).setMain_controller(this);
        this.main_first_load_pane.getChildren().addAll(root_map.get("Register"));
    }
    public void LoadOrderPage() throws IOException {
        this.main_first_load_pane.getChildren().clear();
        ((OrderController)controller_map.get("Order")).setMain_page_holder(this);
        this.main_first_load_pane.getChildren().addAll(root_map.get("Order"));
    }
    public void LoadCatalogPage() throws IOException {
        this.main_first_load_pane.getChildren().clear();
        ((CatalogController)controller_map.get("Catalog")).setMain_page_holder(this);
        this.main_first_load_pane.getChildren().addAll(root_map.get("Catalog"));
    }
    public void LoadHomePage() throws IOException {
        this.main_first_load_pane.getChildren().clear();
        ((HomeController)controller_map.get("Home")).setMain_page_holder(this);
        this.main_first_load_pane.getChildren().addAll(root_map.get("Home"));
    }
    /* --------------------------------------- END --------------------------------------- */
}


