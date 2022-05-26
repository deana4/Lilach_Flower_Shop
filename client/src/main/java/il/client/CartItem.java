package il.client;


public class CartItem {
    private String item_name;
    private String item_price;
    private int item_id;

    public CartItem(String item_name, String item_price, int item_id) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
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
