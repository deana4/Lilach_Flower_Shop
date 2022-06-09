package il.client;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import il.client.controls.LogInControl;
import il.entities.Store;
import il.entities.User;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.JSONException;

import javax.swing.*;

public class MainPageController extends ParentClass {
//    private RepaintManager rm;     //This is a singleton Class which provides the system abilities of changing screens, refresh the system etc.
//    private Dimension dim;

    public class MyThread extends Thread{


        public void run(){
            System.out.println("running in thread");
            try {
                initFunction();
                initButtons();
                MainPageController.getInstance().login_btn.setDisable(false);
                MainPageController.getInstance().register_btn.setDisable(false);
                MainPageController.getInstance().home_button.setDisable(false);
                MainPageController.getInstance().catalog_button.setDisable(false);
//                HomeController controller = ((HomeController)MainPageController.getInstance().controller_map.get("Home"));
//                HomeController.getInstance().setDeterminateSpinnerDisable();
                System.out.println("finish thread");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

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

    @FXML
    private MFXButton catalog_button;

    @FXML
    private MFXButton home_button;

    public static boolean isLogin; //this bool var indicates that the user logged in

    public static String LoginName;

    private double xOffset;
    private double yOffset;

    private Stage stage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private HBox windowHeader;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXFontIcon minimizeIcon;

    @FXML
    private MFXFontIcon alwaysOnTopIcon;

    @FXML
    private MFXFontIcon refreshFeed;

    private Scene scence;

    private ObservableList<String> colors = FXCollections.observableArrayList(); //colors in the system

    private HashMap<String, Parent> root_map = new HashMap<String, Parent>();  //Hashmap of roots by names

    private HashMap<String,Object> controller_map = new HashMap<String,Object>(); //Hashmap of Controllers by names

    private UserClient user;

    public static List<Store> allStores = null;

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

    void MainPageRefresh() throws IOException {
        java.lang.System.gc();//  % garbage-collect
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPage.fxml"));
        Parent mainLayout = fxmlLoader.load();
        Scene scene = new Scene(mainLayout, 1300, 800);
        this.stage.setScene(scene);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
//        initFunction();
        this.stage = App.getAppStage();
        MyThread thread = new MyThread();
        thread.start();
        System.out.println("This code is outside of the thread");
        LoadFirstPage();
    }


    private void initButtons(){
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        LogInControl.logout(UserClient.getInstance().getId());
                        UserClient.getInstance().resetUserClient();
                        Platform.exit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
//        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
            refreshFeed.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        try {
                            MainPageController.getInstance().Refresh(true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) this.main_first_load_pane.getScene().getWindow()).setIconified(true));
        alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean newVal = !stage.isAlwaysOnTop();
            alwaysOnTopIcon.pseudoClassStateChanged(PseudoClass.getPseudoClass("always-on-top"), newVal);
            stage.setAlwaysOnTop(newVal);
        });

        windowHeader.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        windowHeader.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });

    }

    private void initFunction() throws IOException {
        instance = this;
        SetUser();
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("Catalog.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            CatalogController controller = fxmlLoader.getController();
            root_map.remove("Catalog");
            root_map.put("Catalog",root);
            controller_map.remove("Catalog");
            controller_map.put("Catalog",controller);
            ((CatalogController)controller_map.get("Catalog")).setMain_page_holder(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("AddToCart.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            AddToCartController controller = fxmlLoader.getController();
            root_map.remove("Cart");
            root_map.put("Cart",root);
            controller_map.remove("Cart");
            controller_map.put("Cart",controller);
            ((AddToCartController)controller_map.get("Cart")).setMain_page_holder(this);
        }
        {
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

        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("Home.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            HomeController controller = fxmlLoader.getController();
            root_map.remove("Home");
            controller_map.remove("Home");
            root_map.put("Home",root);
            controller_map.put("Home",controller);
            ((HomeController)controller_map.get("Home")).setMain_page_holder(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("Order.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            OrderController controller = fxmlLoader.getController();
            if(!root_map.containsKey("Order")){
                root_map.put("Order",root);
            }
            if(!controller_map.containsKey("Order")) {
                controller_map.put("Order", controller);
            }
            ((OrderController)controller_map.get("Order")).setMain_page_holder(this);
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
        ((AddToCartController)controller_map.get("Cart")).setTotalSum();
        AddToCartController.getInstance().setNo_items_labelFalse();
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
        URL var = getClass().getResource("MyAccount.fxml");
        fxmlLoader.setLocation(var);
        Parent root = fxmlLoader.load();
        MyAccountController controller = fxmlLoader.getController();
        controller.setMain_page_holder(this);
        this.main_first_load_pane.getChildren().addAll(root);
    }

//    @FXML
//    void LogoutBTNClicked(ActionEvent event) throws IOException {
//        {
//            this.login_btn.setVisible(true);
//            this.register_btn.setVisible(true);
//            this.user_wellcome.setVisible(false);
//            this.user_wellcome.clear();
//            this.myacc_btn.setVisible(false);
//            this.mycart_btn.setVisible(false);
//            this.Logout_btn.setVisible(false);
//            this.setLogin(false);
//            this.setLoginName("Default");
//        }
//        UserClient.getInstance().setPriority(1);
//        MainPageController.getInstance().Refresh();
//        LogInControl.logout(UserClient.getInstance().getId());
//        LoadHomePage();
//    }

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
        LogInControl.logout(UserClient.getInstance().getId());
        UserClient.getInstance().resetUserClient();
        MainPageController.getInstance().Refresh(true);

//
//        LoadHomePage();
    }

    @FXML
    void Refresh(MouseEvent event) throws IOException {
        Refresh(false);
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

    public AnchorPane getMain_first_load_pane() {
        return main_first_load_pane;
    }

    public void setMain_first_load_pane(AnchorPane main_first_load_pane) {
        this.main_first_load_pane = main_first_load_pane;
    }

    public HashMap<String, Parent> getRoot_map() {
        return root_map;
    }

    public void addColorToSystem(String color){
        this.colors.add(color);
    }

    public void removeColorFromSystem(String color){
        for(int i=0; i<colors.size(); i++)
        {
            if(colors.get(i).equals(color)){
                colors.remove(i);
            }
        }
    }

    public ObservableList<String> getColors(){ ///check this loop!!!!!!!!!!!!!!!!!!!!!
        ObservableList<String> noDuplicateColors = FXCollections.observableArrayList();
        for(String color: colors){
            if(!noDuplicateColors.contains(color)){
                noDuplicateColors.add(color);
            }
        }
        return noDuplicateColors;
    }

    public Scene getScence() {
        return scence;
    }
    public void setScene(Scene scene){
        this.scence=scene;
    }

    public Store getStore(String store){
        for(int i=0;i<this.allStores.size(); i++){
            if(allStores.get(i).getAddress() == store){
                return allStores.get(i);
            }
        }
        return null;
    }

    public void setRoot_map(HashMap<String, Parent> root_map) {
        this.root_map = root_map;
    }

    public HashMap<String, Object> getController_map() {
        return controller_map;
    }

    public void setController_map(HashMap<String, Object> controller_map) {
        this.controller_map = controller_map;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static List<Store> getAllStores() {
        return allStores;
    }

    public static void setAllStores(List<Store> allStores) {
        MainPageController.allStores = allStores;
    }

    public Object getControllerByKey(String key){
        return controller_map.get(key);
    }

    /*-------------------------------------- Special Functions --------------------------------------*/
    public void UpdateMainController() throws IOException { //check permissions
        if(this.isLogin()){
            if(!UserClient.getInstance().isWorker()) {
                this.mycart_btn.setVisible(true);
            }
            this.login_btn.setVisible(false);
            this.register_btn.setVisible(false);
            this.user_wellcome.setVisible(true);
            this.user_wellcome.setText("Welcome, " + this.LoginName /* Get Last Login Name */);
            this.myacc_btn.setVisible(true);
            this.Logout_btn.setVisible(true);
            HomeRefresh();
            LoadHomePage();
        }else{
            return;
        }
        return;
    }
    /*  ---------------------------------------  Screen Loaders  --------------------------------------- */
    public void Refresh(boolean completely) throws IOException {
        System.out.println("REFRESHING SYSTEM START");
//        Parent root;
//        URL var;
//        FXMLLoader fxmlLoader;
        Runtime.getRuntime().gc();
        if(completely){
            //MainPageRefresh();
            //refreshCompletely();
            System.out.println("Refreshing System Completely");
            refreshCompletely();
        }else if(!completely){
            System.out.println("Refreshing System Main Pages");
            LoginRefresh();
            AddToCartRefresh();
            RegisterRefresh();
            OrderRefresh();
            HomeRefresh();
            MyAccountRefresh();
            CatalogRefresh();
        }
        System.out.println("REFRESHING SYSTEM FINISHED");
//        LoadHomePage();
    }


    public void refreshCompletely() throws IOException {
        App.refreshSystemCompletely(true);
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
    public void MyAccountRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("MyAccount.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        MyAccountController controller = fxmlLoader.getController();
        root_map.remove("MyAccount");
        root_map.put("MyAccount",root);
        controller_map.remove("MyAccount");
        controller_map.put("MyAccount",controller);
        ((MyAccountController)controller_map.get("MyAccount")).setMain_page_holder(this);
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
        FXMLLoader fxmlLoader;
        URL var;
        Parent root;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("Register.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        RegisterController controller = fxmlLoader.getController();
        if(!root_map.containsKey("Register")){
            root_map.put("Register",root);
        }
        if(!controller_map.containsKey("Register")){
            controller_map.put("Register",controller);
        }
        this.main_first_load_pane.getChildren().clear();
        ((RegisterController)controller_map.get("Register")).setMain_controller(this);
        this.main_first_load_pane.getChildren().addAll(root);
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


    public void LoadFirstPage() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        this.main_first_load_pane.getChildren().clear();
        var = getClass().getResource("Home.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        HomeController controller = fxmlLoader.getController();
        this.login_btn.setDisable(true);
        this.register_btn.setDisable(true);
        this.home_button.setDisable(true);
        this.catalog_button.setDisable(true);
        root_map.put("Home",root);
        controller_map.put("Home",controller);
        this.main_first_load_pane.getChildren().addAll(root);

    }

   public void LoadCatalogFromZero() throws IOException {
        FXMLLoader fxmlLoader;
        URL var;
        Parent root;
       fxmlLoader = new FXMLLoader();
       var = getClass().getResource("Catalog.fxml");
       fxmlLoader.setLocation(var);
       root = fxmlLoader.load();
       CatalogController controller = fxmlLoader.getController();
       root_map.remove("Catalog");
       controller_map.remove("Catalog");
       root_map.put("Catalog",root);
       controller_map.put("Catalog",controller);
       this.main_first_load_pane.getChildren().clear();
       ((CatalogController)controller_map.get("Catalog")).setMain_page_holder(this);
       this.main_first_load_pane.getChildren().addAll(root_map.get("Catalog"));
   }

    public void AddCustomProductPage() throws IOException {
        FXMLLoader fxmlLoader;
        URL var;
        Parent root;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("CustomProduct.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        this.main_first_load_pane.getChildren().clear();
        this.main_first_load_pane.getChildren().addAll(root);
    }
    /* --------------------------------------- END --------------------------------------- */
}


