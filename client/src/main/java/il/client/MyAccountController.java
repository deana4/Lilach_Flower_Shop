package il.client;

import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoaderBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class MyAccountController{

    private double xOffset;
    private double yOffset;
    private ToggleGroup toggleGroup;

    @FXML
    private StackPane contentPane;

    @FXML
    private VBox navBar;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private MFXScrollPane scrollPane;

    private MainPageController main_page_holder;

    private HashMap<String, Parent> root_map = new HashMap<String, Parent>();  //Hashmap of roots by names

    private HashMap<String,Object> controller_map = new HashMap<String,Object>(); //Hashmap of Controllers by names

    private static MyAccountController instance = null;

    public static MyAccountController getInstance(){
        if(instance == null){
            System.out.println("Error Occured");
        }
        return instance;
    }


    @FXML
    void initialize() throws IOException {
        initializeLoader();
        initFunction();

    }

    private void initializeLoader() {
        MFXLoader loader = new MFXLoader();
        System.out.println("MyAccount isworker?"+UserClient.getInstance().isWorker()+UserClient.getInstance().getUserName());
        if (UserClient.getInstance().isWorker()==false){
            System.out.println("in if");
        loader.addView(MFXLoaderBean.of("MyAccount", loadURL("EditAccountDetails.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-variant7-mark", "Account Preferences")).setDefaultRoot(true).get());
        loader.addView(MFXLoaderBean.of("Orders", loadURL("OrdersHistory.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-list-dropdown", "Orders")).get());
        loader.addView(MFXLoaderBean.of("Cancel Orders", loadURL("CancelOrders.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-x-circle", "Cancel Orders")).get());
        loader.addView(MFXLoaderBean.of("Complaints on Orders", loadURL("ComplaintOrders.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-plus", "Complaints on Orders")).get());
        }

        if (UserClient.getInstance().isWorker() && UserClient.getInstance().getPlan()==4){
        loader.addView(MFXLoaderBean.of("Complaints", loadURL("ComplaintsTab.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-message", "Complaints")).get());
        }

        if(UserClient.getInstance().isWorker() && UserClient.getInstance().getPlan()==5){
        loader.addView(MFXLoaderBean.of("System Admin Panel", loadURL("PeopleList.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-users", "System Admin Panel")).get());
        }

        loader.setOnLoadedAction(beans -> {
            List<ToggleButton> nodes = beans.stream()
                    .map(bean -> {
                        ToggleButton toggle = (ToggleButton) bean.getBeanToNodeMapper().get();
                        toggle.setOnAction(event -> contentPane.getChildren().setAll(bean.getRoot()));
                        if (bean.isDefaultView()) {
                            contentPane.getChildren().setAll(bean.getRoot());
                            toggle.setSelected(true);
                        }
                        return toggle;
                    })
                    .toList();
            navBar.getChildren().setAll(nodes);
        });
        loader.start();
    }

    private ToggleButton createToggle(String icon, String text) {
        MFXIconWrapper wrapper = new MFXIconWrapper(icon, 24, 32);

        MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
        toggleNode.setAlignment(Pos.CENTER_LEFT);
        toggleNode.setMaxWidth(Double.MAX_VALUE);
        toggleNode.setToggleGroup(toggleGroup);

        return toggleNode;
    }

    public static URL loadURL(String path) {
        return MyAccountController.class.getResource(path);
    }

    private void initFunction() throws IOException {
        instance = this;
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("Complain.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            ComplainController controller = fxmlLoader.getController();
            root_map.put("Complaint", root);
            controller_map.put("Complaint", controller);
            ((ComplainController) controller_map.get("Complaint")).setMy_account_page_holder(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("OrdersHistory.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            OrdersHistoryController controller = fxmlLoader.getController();
            root_map.put("OrdersHistory", root);
            controller_map.put("OrdersHistory", controller);
            ((OrdersHistoryController) controller_map.get("OrdersHistory")).setMy_account_page_holder(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("CancelOrder.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            CancelOrderController controller = fxmlLoader.getController();
            root_map.put("CancelOrder", root);
            controller_map.put("CancelOrder", controller);
            ((CancelOrderController) controller_map.get("CancelOrder")).setMy_account_page_holder(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("EditAccountDetails.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            EditAccountDetailsController controller = fxmlLoader.getController();
            root_map.put("AccountPref", root);
            controller_map.put("AccountPref", controller);
            ((EditAccountDetailsController) controller_map.get("AccountPref")).setAccountController(this);
        }
        {
            fxmlLoader = new FXMLLoader();
            var = getClass().getResource("ComplaintsTab.fxml");
            fxmlLoader.setLocation(var);
            root = fxmlLoader.load();
            ComplaintTabController controller = fxmlLoader.getController();
            root_map.put("ComplaintsTab", root);
            controller_map.put("ComplaintsTab", controller);
            ((ComplaintTabController) controller_map.get("ComplaintsTab")).setMy_account_page_holder(this);
        }
//        {
//            fxmlLoader = new FXMLLoader();
//            var = getClass().getResource("PeopleList.fxml");
//            fxmlLoader.setLocation(var);
//            root = fxmlLoader.load();
//            PeopleListController controller = fxmlLoader.getController();
//            root_map.put("PeopleList", root);
//            controller_map.put("PeopleList", controller);
//            ((PeopleListController) controller_map.get("PeopleList")).setMy_account_page_holder(this);
//        }





    }

    public void LoadComplaintPage() throws IOException {
        this.contentPane.getChildren().clear();
        ((ComplainController)controller_map.get("Complaint")).setMy_account_page_holder(this);
        this.contentPane.getChildren().addAll(root_map.get("Complaint"));
    }

    public void LoadOrdersHistoryPage() throws IOException {
        this.contentPane.getChildren().clear();
        ((OrdersHistoryController)controller_map.get("OrdersHistory")).setMy_account_page_holder(this);
        this.contentPane.getChildren().addAll(root_map.get("OrdersHistory"));
    }

    public void LoadCancelOrderPage() throws IOException {
        this.contentPane.getChildren().clear();
        ((CancelOrderController)controller_map.get("CancelOrder")).setMy_account_page_holder(this);
        this.contentPane.getChildren().addAll(root_map.get("CancelOrder"));
    }

    public void LoadAccountPref() throws IOException {
        this.contentPane.getChildren().clear();
        ((EditAccountDetailsController)controller_map.get("AccountPref")).setAccountController(this);
        this.contentPane.getChildren().addAll(root_map.get("AccountPref"));
    }

    public void LoadComplaintsTab() {
        this.contentPane.getChildren().clear();
        ((ComplaintTabController)controller_map.get("ComplaintsTab")).setMy_account_page_holder(this);
        this.contentPane.getChildren().addAll(root_map.get("ComplaintsTab"));
    }



    public void ComplainRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("Complain.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        ComplainController controller = fxmlLoader.getController();
        root_map.remove("Complaint");
        controller_map.remove("Complaint");
        root_map.put("Complaint",root);
        controller_map.put("Complaint",controller);
        ((ComplainController)controller_map.get("Complaint")).setMy_account_page_holder(this);
    }

    public void ComplaintsTabRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("ComplaintsTab.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        ComplaintTabController controller = fxmlLoader.getController();
        root_map.remove("ComplaintsTab");
        controller_map.remove("ComplaintsTab");
        root_map.put("ComplaintsTab",root);
        controller_map.put("ComplaintsTab",controller);
        ((ComplaintTabController)controller_map.get("ComplaintsTab")).setMy_account_page_holder(this);
    }

    public void CancelOrderRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("CancelOrder.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        CancelOrderController controller = fxmlLoader.getController();
        root_map.remove("CancelOrder");
        controller_map.remove("CancelOrder");
        root_map.put("CancelOrder",root);
        controller_map.put("CancelOrder",controller);
        ((CancelOrderController)controller_map.get("CancelOrder")).setMy_account_page_holder(this);
    }

    public void AccountPrefRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("EditAccountDetails.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        EditAccountDetailsController controller = fxmlLoader.getController();
        root_map.remove("AccountPref");
        controller_map.remove("AccountPref");
        root_map.put("AccountPref",root);
        controller_map.put("AccountPref",controller);
        ((EditAccountDetailsController)controller_map.get("AccountPref")).setAccountController(this);
    }

    /*gets and sets*/

    public MainPageController getMain_page_holder() {
        return main_page_holder;
    }

    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }

    /*end sets ang gets*/
}
