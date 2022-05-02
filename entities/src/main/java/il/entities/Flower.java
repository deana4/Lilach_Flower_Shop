package il.entities;

import java.io.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
public class Flower implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url_image;
    private double price;
    private boolean sale;
    private double discount_perc;

    public Flower(String name, double price, String url_image, boolean sale, double discount_perc){
        this.name = name;
        this.price = price;
        this.url_image = url_image;
        this.sale = sale;
        this.discount_perc = discount_perc;
    }

    public Flower(String name, double price){
        this.name = name;
        this.price = price;
        this.url_image = "";
        this.sale=false;
        this.discount_perc = 0;
    }

    public Flower() {

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
            this.discount_perc=0;
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

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}