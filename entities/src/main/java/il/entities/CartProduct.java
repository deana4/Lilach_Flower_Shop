package il.entities;


import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "Carkproducts")
public class CartProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    int amount;
    String name;
    private double price;
    private boolean sale;
    private double discount_perc;
    private int idProduct;

    @ManyToOne
    Order order;

    public CartProduct(){}

    public CartProduct(Product p, int amount){
        this.idProduct = p.getId();
        this.amount=amount;
        this.name=p.getName();
        this.price = p.getPrice();
        this.sale = p.isSale();
        this.discount_perc=p.getDiscount_perc();
    }

    public double getTotalPrice(){
        return this.amount*this.getPrice();
    }

    public void incAmount(){
        this.amount++;
        this.order.setSum(this.order.getSum() + this.getPrice());
    }

    public void decAmount(){
        this.amount--;
        if(this.amount<1){
            this.order.removeProduct(this);
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getPrice() {
        if(!this.sale)
            return price;
        return price*(discount_perc/100);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public double getDiscount_perc() {
        return discount_perc;
    }

    public void setDiscount_perc(double discount_perc) {
        this.discount_perc = discount_perc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
