package il.entities;





import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.boot.cfgxml.spi.MappingReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@SuppressWarnings("serial")
@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // not must
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName ="default";
    @Column(name = "user_password")
    private String password;
    private String creditCard;
    private double credit;
    private String position;
    @Column(name = "full_name")
    private String name;
    private String phone;
    private String mail;
    private String expiryDate;
    private String identifyNumbers;
    private double discount;
    private int accountStatus;
    private boolean login;
    private int priority;  //3 year member, 2 store wide member, 1 species store member


    @OneToMany (mappedBy = "user")
    private List<Order> listOrders;

    @OneToMany(mappedBy = "user")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Complain> listComplains;



    @ManyToMany
    private List<Store> liststore;



    public LinkedList<Order> getOrdersForClient(){
        List<Order> orders = this.getListOrders();
        LinkedList<Order> newOrders = new LinkedList<>();
        for(Order o : orders)
            newOrders.add(o.getOrderForClient());
        return newOrders;
    }

    public LinkedList<Complain> getComplainsForClient(){
        List<Complain> complains = this.getListComplains();
        LinkedList<Complain> newComplains = new LinkedList<>();
        for(Complain o : complains)
            newComplains.add(o.getComplainForClient());
        return newComplains;
    }

    public LinkedList<Store> getStoresForClient(){
        List<Store> stores = this.getListstore();
        LinkedList<Store> newStores = new LinkedList<>();
        for(Store o : stores)
            newStores.add(o.getStoreForClient());
        return newStores;
    }



    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
    }

    public void setListComplains(List<Complain> listComplains) {
        this.listComplains = listComplains;
    }

    public List<Store> getListstore() {
        return liststore;
    }

    public void setListstore(List<Store> liststore) {
        this.liststore = liststore;
    }
    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    
    //3-year member
    //2-store wiil member
    //1-specific store member
    
    public int getId() {
        return id;
    }
    public User(String userName, String password, String creditCard, int priority, String name, String identifyNumbers) {
        this.userName = userName;
        this.password = password;
        this.creditCard = creditCard;
        this.credit = 0;
        this.name = name;
        this.identifyNumbers = identifyNumbers;
        this.accountStatus=1;
        this.login=false;
        this.listOrders = new ArrayList<Order>();
        this.listComplains = new ArrayList<Complain>();
        this.liststore = new ArrayList<Store>();
        this.priority =  priority;
        this.phone = "Default";
        this.mail = "Default";
//        this.expiryDate = expiryDate;
//        this.discount=discount;
    }
    public User getUserForClien(){
        User u = new User(this.userName, this.password, this.creditCard, this.priority, this.name, this.identifyNumbers);
        u.setId(this.id);
        u.setMail(this.mail);
        u.setCredit(this.credit);
        u.setPhone(this.phone);
        u.setAccountStatus(this.accountStatus);
        u.setLogin(this.login);
//        u.setDiscount(this.discount);
//        u.setExpiryDate(this.expiryDate);
        return u;
    }

    public User(int id, String username, String name, String identifyNumbers, int priority, int acountStatus, double credit){
        this.id = id;
        this.userName = username;
        this.name = name;
        this.identifyNumbers = identifyNumbers;
        this.priority = priority;
        this.accountStatus = acountStatus;
        this.credit = credit;
    }

    public User() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIdentifyNumbers() {
        return identifyNumbers;
    }

    public void setIdentifyNumbers(String identifyNumbers) {
        this.identifyNumbers = identifyNumbers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void addComplain(Complain complain) {
        listComplains.add(complain);
        complain.setUser(this);
    }

    public void removeComplain(Complain complain){
        listComplains.remove(complain);
    }

    public void removeOrder(Order order){
        listOrders.remove(order);
    }

    public void addOrder(Order order) {listOrders.add(order);
        order.setUser(this);}

    public List<Order> getListOrders() {
        return listOrders;
    }

    public List<Complain> getListComplains() {return listComplains;}

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void addStore(Store store){
        this.liststore.add(store);
    }
    public void removeStore(Store store){
        this.liststore.remove(store);
    }
    public void addStore2(List<Store> stores) {
        for (Store store : stores) {
            this.liststore.add(store);
            store.addUser2(this);
        }
    }
}