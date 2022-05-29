package il.client;


public class CartItem {
    private String item_name;
    private double item_price;
    private int item_id;
    private int item_amount;

    public CartItem(String item_name, double item_price, int amount, int item_id) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_id = item_id;
        this.item_amount = amount;
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



    @Override
    public String toString() {
        return "CartItem{" +
                "item_name='" + item_name + '\'' +
                ", item_price='" + item_price + '\'' +
                ", item_id=" + item_id +
                '}';
    }
}
