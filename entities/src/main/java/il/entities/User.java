package il.entities;



import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // not must
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    private String creditCard;
    private double credit;
    private String position;
    private String name;
    private String phone;
    private String mail;
    private String expiryDate;
    private String identifyNumbers;
    private double discount;
    private int accountStatus;
    private boolean login;


//    @ManyToMany
//    private List<Order_Obj> listOrders;
//
//    @ManyToMany
//    private List<Complain_Obj> listComplains;




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
//        this.phone = phone;
//        this.mail = mail;
//        this.expiryDate = expiryDate;
//        this.discount=discount;
//        this.listComplains=new ArrayList<>();
//        this.listOrders=new ArrayList<>();
    }
    public User() {}

//    public void addComplain(Complain_Obj complain) {listComplains.add(complain);}
//
//    public void addOrder(Order_Obj order) {listOrders.add(order);}

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

//    public List<Order_Obj> getListOrders() {
//        return listOrders;
//    }
//
//    public void setListOrders(List<Order_Obj> listOrders) {
//        this.listOrders = listOrders;
//    }
//
//
//    public List<Complain_Obj> getListComplains() {return listComplains;}
//
//    public void setListComplains(List<Complain_Obj> listComplains) {this.listComplains = listComplains;}


}
