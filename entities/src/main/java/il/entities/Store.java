package il.entities;

import org.hibernate.boot.jaxb.hbm.spi.NativeQueryNonScalarRootReturn;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
@Entity
@Table(name="stores")
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "Branch_manager_name")
    private BranchManager manager;
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<StoreEmployee> listEmployees;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "liststore")
    private List<User> listUsers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<Order> listOrders;

    @OneToMany(mappedBy = "store")
    private List<Complain> listComplains;


    public Store() {}

    public Store getStoreForClient(){
        Store s = new Store(this.address);
        s.setId(this.id);
        return s;
    }

    public Store(String address) {
        this.address = address;
        this.listEmployees=new ArrayList<StoreEmployee>();
        this.listUsers=new ArrayList<User>();
        this.listOrders=new ArrayList<Order>();
        this.listComplains=new ArrayList<Complain>();

    }


    public void addOrder(Order order) {
        listOrders.add(order);
        order.setStore(this);
    }
    public void addComplain(Complain complain) {
        listComplains.add(complain);
        complain.setStore(this);
    }
    public void addUser(User user) {
        listUsers.add(user);
        user.addStore(this);
    }
    public void addUser2(User user) {
        listUsers.add(user);
    }

    public void addEmployee(StoreEmployee employee){
        this.listEmployees.add(employee);
        employee.setStore(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BranchManager getManager() {
        return manager;
    }

    public void setManager(BranchManager manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<StoreEmployee> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<StoreEmployee> listEmployees) {
        this.listEmployees = listEmployees;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<Order> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
    }

    public List<Complain> getListComplains() {
        return listComplains;
    }

    public void setListComplains(List<Complain> listComplains) {
        this.listComplains = listComplains;
    }
}