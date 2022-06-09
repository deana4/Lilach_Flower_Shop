package il.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Store store;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", orphanRemoval = true)
    private List<CartProduct> products;


    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
    private Complain complain;

    private String dateReceive;
    private String timeReceive;
    private String dateOrder;
    private String timeOrder;
    private double sum;
    private String greeting;
    private String nameReceives;
    private String phoneReceives;
    private String address;
    private int status; //1- cancel ,2-delivered, 3-pending
    private String type;
    private boolean isCanceled;

    public Order(User user, Store store, String dateReceive, String timeReceive, String dateOrder, String timeOrder, double sum, String greeting, String nameReceives, String phoneReceives, String address, boolean isCanceled) {
        this.user = user;
        this.store = store;
        this.dateReceive = dateReceive;
        this.timeReceive = timeReceive;
        this.dateOrder = dateOrder;
        this.timeOrder = timeOrder;
        this.sum = sum;
        this.greeting = greeting;
        this.nameReceives = nameReceives;
        this.phoneReceives = phoneReceives;
        this.address = address;
        this.status=3;
        this.isCanceled = isCanceled;
        this.products = new ArrayList<CartProduct>();
    }

    public Order() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Order getOrderForClient(){
        Order o = new Order(null, null, this.dateReceive, this.timeReceive, this.dateOrder, this.timeOrder, this.sum, this.greeting, this.nameReceives, this.phoneReceives, this.address,false);
        o.setId(this.id);
        for(CartProduct p : this.products){
            o.addProduct(p);
            if(this.complain!=null)
                o.setComplain(this.complain.getComplainForClient());
        }

        return o;
    }

    public String getPhoneReceives() {
        return phoneReceives;
    }

    public void setPhoneReceives(String phoneReceives) {
        this.phoneReceives = phoneReceives;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CartProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CartProduct> products) {
        this.products = products;
    }

    public void addProduct(CartProduct product){
        this.products.add(product);
        product.setOrder(this);
        this.sum+=product.getTotalPrice();
    }

    public void removeProduct(CartProduct product){
        this.products.remove(product);
        this.sum-=product.getTotalPrice();
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }


    //    public HashMap<Product, Integer> getOrderD() {
//        return orderD;
//    }
//
//    public void setOrderD(HashMap<Product, Integer> orderD) {
//        this.orderD = orderD;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return  user;
    }

    public void setUser(User user) {
        this. user = user;
    }

    public String getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(String dateReceive) {
        this.dateReceive = dateReceive;
    }

    public String getTimeReceive() {
        return timeReceive;
    }

    public void setTimeReceive(String timeReceive) {
        this.timeReceive = timeReceive;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder = timeOrder;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getNameReceives() {
        return nameReceives;
    }

    public void setNameReceives(String nameReceives) {
        this.nameReceives = nameReceives;
    }

    public String getPhoneRecives() {
        return phoneReceives;
    }

    public void setPhoneRecives(String phoneRecives) {
        this.phoneReceives = phoneRecives;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
        complain.setOrder(this);
    }

}