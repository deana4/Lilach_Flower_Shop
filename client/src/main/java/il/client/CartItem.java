package il.client;


import il.entities.CartProduct;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CartItem {
    private String item_name;
    private double item_price;
    private int item_id;
    private int item_amount;

    @FXML
    private MFXButton removeBtn;

    @FXML
    private MFXButton amountChangeBtn;

    public CartItem(String item_name, double item_price, int amount, int item_id) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_id = item_id;
        this.item_amount = amount;
        this.removeBtn = new MFXButton("Remove Item");
        this.amountChangeBtn = new MFXButton("Change Amount");
        buttonsInit();
    }

    public CartItem(CartProduct product){
        this.item_name = product.getName();
        this.item_price = product.getPrice();
        this.item_id = product.getId();
        this.item_amount = product.getAmount();
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
                AddToCartController.getInstance().removeItemFromTable(this.item_id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        amountChangeBtn.setOnAction(event -> {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CartAdderDialog.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 405  , 215);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            CartAdderController controller = fxmlLoader.getController();
            ProductView product_controller = CatalogController.getInstance().getProductsControllers().get(this.item_id);
            System.out.println("in CartItem "+this.item_id+" "+product_controller.getId());
            controller.initialize(stage,product_controller, this.item_id);
            stage.setTitle("Add To Cart Section");
            stage.setScene(scene);
            stage.show();
         //   AddToCartController.getInstance().setAmountByID(this.item_id, amount);
        });
    }

    public int getItem_amount() {
        return item_amount;
    }

    public void setItem_amount(int item_amount) {
        this.item_amount = item_amount;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
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

    @Override
    public String toString() {
        return "CartItem{" +
                "item_name='" + item_name + '\'' +
                ", item_price='" + item_price + '\'' +
                ", item_id=" + item_id +
                '}';
    }
}
