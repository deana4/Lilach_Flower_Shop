package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CartAdderCustomController {
    @FXML
    private AnchorPane DialogPane;

    @FXML
    private MFXTextField amountText;

    @FXML
    private MFXButton applyBtn;

    @FXML
    private MFXFontIcon closeIcon;

    private Stage stage;
    private ProductView PVController;
    ObservableList<ProductView> list = FXCollections.observableArrayList();

    @FXML
    void initialize(Stage stage, ObservableList<ProductView> list){
        this.stage = stage;
        this.list = list;
    }
    @FXML
    void applyClicked(ActionEvent event) throws IOException {
        boolean isFound = false;
        DialogPane.setOnMouseClicked(e -> {
            applyBtn.setText("Apply");
        });
        int checkNum = AmountCheck(this.amountText.getText(),this.amountText);
        if(checkNum == 0){
            applyBtn.setText("Error: Amount 0");
        }else if(checkNum == 1){
            AddToCartController cont = (AddToCartController)MainPageController.getInstance().getController_map().get("Cart");
            ObservableList<CartItem> cart = AddToCartController.getInstance().getItemsList();
            for(CartItem item: cart){
                if(item.getItem_id() == PVController.getId()){
                    AddToCartController.getInstance().getItemsList().remove(item);
                    AddToCartController.getInstance().addItemToTable(item,Integer.parseInt(amountText.getText()));
                    System.out.println("in true");
                    isFound = true;
                }
            }
            if(isFound == false) {
                System.out.println("in false");
                for(ProductView product : this.list){
                    PVController = product;
                    cont.addItemToTable(PVController.getProduct_name(), PVController.getProduct_price(), getPVController().getId(), Integer.parseInt(this.amountText.getText()));
                }

            }
            cont.setChanges();
            System.out.println(cont.items); //!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println("added");
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }

    /* Special Function */
    private int AmountCheck (String number, MFXTextField field_to_check){
        //checks the chars (need to contain only digits)
        if(field_to_check.getText()==null || field_to_check.getText().equals("")||field_to_check.getText().contains("Incorrect")){
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect");
            return 0;
        }
        if(number == "0") {
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect");
        }
        char[] number_char = number.toCharArray();
        for(int i=0; i<number.length(); i++){
            if(number_char[i]<'0' || number_char[i]>'9'){
                field_to_check.clear();
                field_to_check.setPromptText("Incorrect");
                return 0;
            }

            }
        return 1;
    }

    /* End of Special Function*/

    /* Getters And Setters */

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ProductView getPVController() {
        return PVController;
    }

    public void setPVController(ProductView PVController) {
        this.PVController = PVController;
    }

    /* END G AND S */
}
