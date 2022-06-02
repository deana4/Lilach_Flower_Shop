package il.entities;





import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // not must
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
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
    private int priority;


    @OneToMany (mappedBy = "user")
    private List<Order> listOrders;

    @OneToMany(mappedBy = "complain_user")
    private List<Complain> listComplains;

    @ManyToMany
    private List<Store> liststore;



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

    public int getId() {
        return id;
    }
    public User(String userName, String password, String creditCard, String position, String name, String identifyNumbers) {
        this.userName = userName;
        this.password = password;
        this.creditCard = creditCard;
        this.credit = 0;
        this.position = position;
        this.name = name;
        this.identifyNumbers = identifyNumbers;
        this.accountStatus=1;
        this.login=false;
        this.listOrders = new ArrayList<Order>();
        this.listComplains = new ArrayList<Complain>();
        this.liststore = new ArrayList<Store>();
//        this.priority =  priority;


//        this.phone = phone;
//        this.mail = mail;
//        this.expiryDate = expiryDate;
//        this.discount=discount;
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