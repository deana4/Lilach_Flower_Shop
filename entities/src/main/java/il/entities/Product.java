package il.entities;

import javax.persistence.*;
import java.io.Serializable;


@SuppressWarnings("serial")
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Lob
    @Column(name="Folwer_IMAGE", nullable=false, columnDefinition="mediumblob")
    private byte[] image;
    private double price;
    private boolean sale;
    private double discount_perc;
    private String type;
    private String color;

    public Product(String name, double price, boolean sale, double discount_perc, String type, String color){
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.discount_perc = discount_perc;
        this.type = type;
        this.color = color;
        this.image = null;
    }

    public Product(String name, double price){
        this.name = name;
        this.price = price;
        this.sale=false;
        this.discount_perc = 0;
        this.image = null;
    }

    public Product() {

    }



    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getDiscount_perc() {
        return discount_perc;
    }

    public void setDiscount_perc(double discount_perc) {
        this.discount_perc = discount_perc;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
        if(sale==false)
            this.discount_perc=100;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public boolean isOn_discount() {
        return sale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isSale() {
        return sale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getSalePrice(){
        if(!this.sale)
            return this.price;
        return this.price*(this.discount_perc/100);
    }
}