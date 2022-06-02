package il.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Message implements Serializable {
    String message;

    public Message(String message){
        this.message = message;
    }

    LinkedList<Product> listItem=null;
    Product item=null;

    User user=null;


    //login/register
    String username;
    String id;
    String pass;
    boolean isWorker;
    String credit_card;
    static String plan;
    String name;



    String phone;



    String mail;

    //order
    int orderID;
    Order order;
    int storeID;



    int userID;

    int iddatabase;

    static List<Store> stores=null;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    //recive login
    boolean loginStatus;
    String loginResult;

    //recive register
    boolean registerStatus;
    String registerResult;

    //setProduct
    byte[] bFile;
    double price;
    String nameProduct;
    int idProduct;
    boolean sale;
    double discountPer;
    int idItem;
    double newPrice;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getIddatabase() {
        return iddatabase;
    }

    public void setIddatabase(int iddatabase) {
        this.iddatabase = iddatabase;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public double getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(double discountPer) {
        this.discountPer = discountPer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public byte[] getbFile() {
        return bFile;
    }

    public void setbFile(byte[] bFile) {
        this.bFile = bFile;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public String getRegisterResult() {
        return registerResult;
    }

    public void setRegisterResult(String registerResult) {
        this.registerResult = registerResult;
    }



    public boolean isRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(boolean registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public static String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LinkedList<Product> getListItem() {
        return listItem;
    }

    public void setListItem(LinkedList<Product> listItem) {
        this.listItem = listItem;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }


    public static List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

}