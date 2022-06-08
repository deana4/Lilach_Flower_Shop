package il.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@SuppressWarnings("serial")
public class Message implements Serializable {
    String message;

    public Message(String message){
        this.message = message;
    }

    LinkedList<Product> listItem=null;
    LinkedList<Store> listStors=null;
    LinkedList<Order> listOrder = null;
    LinkedList<Complain> listComplains = null;
    LinkedList<User> listUsers = null;

    public String getUodateResult() {
        return uodateResult;
    }

    public void setUodateResult(String uodateResult) {
        this.uodateResult = uodateResult;
    }

    public String getSetinfo() {
        return setinfo;
    }

    public void setSetinfo(String setinfo) {
        this.setinfo = setinfo;
    }

    //INFO UPATE
    private String uodateResult;


    private String setinfo;

    public LinkedList<Employee> getListEmploeeys() {
        return listEmploeeys;
    }

    public void setListEmploeeys(LinkedList<Employee> listEmploeeys) {
        this.listEmploeeys = listEmploeeys;
    }

    LinkedList<Employee> listEmploeeys=null;


    //login/register
    String username;
    int idUser;
    String id;
    String pass;
    boolean isWorker;
    String credit_card;
    int plan;
    String name;
    String phone;
    String mail;
    double creadit;
    String priorty;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;



    User user;



    Employee employee;




    int permision;

    Product product;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPermision() {
        return permision;
    }

    public void setPermision(int permision) {
        this.permision = permision;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    int accountStatus;

    public double getCreadit() {
        return creadit;
    }

    public void setCreadit(double creadit) {
        this.creadit = creadit;
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

    String type;
    String color;

    //order
    int orderID;
    Order order;
    int storeID;
    String timeCancel;
    String dateCancel;

    //complain
    Complain complain;
    String answer;
    double refund;
    int complainID;



    int userID;

    int iddatabase;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public int getComplainID() {
        return complainID;
    }

    public void setComplainID(int complainID) {
        this.complainID = complainID;
    }

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public String getTimeCancel() {
        return timeCancel;
    }

    public void setTimeCancel(String timeCancel) {
        this.timeCancel = timeCancel;
    }

    public String getDateCancel() {
        return dateCancel;
    }

    public void setDateCancel(String dateCancel) {
        this.dateCancel = dateCancel;
    }

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

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
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

    public LinkedList<Store> getListStors() {
        return listStors;
    }

    public void setListStors(LinkedList<Store> listStors) {
        this.listStors = listStors;
    }

    public LinkedList<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(LinkedList<Order> listOrder) {
        this.listOrder = listOrder;
    }

    public LinkedList<Complain> getListComplains() {
        return listComplains;
    }

    public void setListComplains(LinkedList<Complain> listComplains) {
        this.listComplains = listComplains;
    }

    public LinkedList<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(LinkedList<User> listUsers) {
        this.listUsers = listUsers;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPriorty() {
        return priorty;
    }

    public void setPriorty(String priorty) {
        this.priorty = priorty;
    }

}