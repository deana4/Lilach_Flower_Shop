package il.client;

import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoaderBean;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

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
     //   loader.addView(MFXLoaderBean.of("Complains", loadURL("Complain.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-circle-dot", "Complaints")).setDefaultRoot(true).get());
        loader.addView(MFXLoaderBean.of("Orders", loadURL("OrdersHistory.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-list-dropdown", "Orders")).setDefaultRoot(true).get());
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

    public void ComplainRefresh() throws IOException {
        Parent root;
        URL var;
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader();
        var = getClass().getResource("Complain.fxml");
        fxmlLoader.setLocation(var);
        root = fxmlLoader.load();
        ComplainController controller = fxmlLoader.getController();
        root_map.put("Complaint",root);
        controller_map.put("Complaint",controller);
        ((ComplainController)controller_map.get("Complaint")).setMy_account_page_holder(this);
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
        root_map.put("CancelOrder",root);
        controller_map.put("CancelOrder",controller);
        ((CancelOrderController)controller_map.get("CancelOrder")).setMy_account_page_holder(this);
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
