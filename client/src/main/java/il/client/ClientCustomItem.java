package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ClientCustomItem {
    static int id = 0;
    private int customId;
    private String type;
    private int amount;
    private String price;
    private ObservableList<ProductView> customItems = FXCollections.observableArrayList();

    @FXML
    private MFXButton removeBtn;

    @FXML
    private MFXButton amountChangeBtn;

    ClientCustomItem(String type, int amount,String price, ObservableList<ProductView> customList){
        this.customId = id;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.customItems = customList;
        this.removeBtn = new MFXButton("Remove Item");
        this.amountChangeBtn = new MFXButton("Change Amount");
        buttonsInit();
    }

    ClientCustomItem(ClientCustomItem customItem){
        this.customId = customItem.getCustomId();
        this.type = customItem.getType();
        this.amount = customItem.getAmount();
        this.price = customItem.getPrice();
        this.customItems = customItem.getCustomItems();
        this.removeBtn = new MFXButton("Remove Item");
        this.amountChangeBtn = new MFXButton("Change Amount");
        buttonsInit();
    }

    public void buttonsInit(){
        removeBtn.setStyle("-fx-background-color: transparent;" +
                "    -fx-border-color: -mfx-purple;" +
                "    -fx-border-radius: 3;" +
                "    -fx-text-fill: -mfx-purple;");
        amountChangeBtn.setStyle("-fx-background-color: transparent;" +
                "    -fx-border-color: -mfx-purple;" +
                "    -fx-border-radius: 3;" +
                "    -fx-text-fill: -mfx-purple;");


        removeBtn.setOnAction(event -> {
            try {
                AddToCartController.getInstance().removeItemFromCustomTable(this.customId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        amountChangeBtn.setOnAction(event -> {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CartAdderDialogCustom.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 405  , 215);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            CartAdderCustomController controller = fxmlLoader.getController();
            controller.setStage(stage);
            controller.initialize(this.customItems, this.type, this.customId);
            stage.setTitle("Add To Cart Section");
            stage.setScene(scene);
            stage.show();
            //   AddToCartController.getInstance().setAmountByID(this.item_id, amount);
        });
    }

    public MFXButton getRemoveBtn() {
        return removeBtn;
    }

    public void setRemoveBtn(MFXButton removeBtn) {
        this.removeBtn = removeBtn;
    }

    public MFXButton getAmountChangeBtn() {
        return amountChangeBtn;
    }

    public void setAmountChangeBtn(MFXButton amountChangeBtn) {
        this.amountChangeBtn = amountChangeBtn;
    }

    public static int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ObservableList<ProductView> getCustomItems() {
        return customItems;
    }

    public void setCustomItems(ObservableList<ProductView> customItems) {
        this.customItems = customItems;
    }
}
