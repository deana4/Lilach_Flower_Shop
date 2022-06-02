package il.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "complains")
public class Complain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User complain_user;

    @ManyToOne
    private Store store;
    @Column(name = "complain_date")
    private String date;
    private double answer;
    private boolean isHandle;
    private String complain_data;




    @OneToOne
    private Order order;


    public Complain(User user, String date, double answer, boolean isHandle, String complain, Order order1) {
        this.complain_user= user;
        this.date = date;
        this.answer = answer;
        this.isHandle = isHandle;
        this.complain_data = complain;
        this.order =order1;
    }

    public Complain() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return complain_user;
    }

    public void setUser(User user) {
        this.complain_user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public boolean isHandle() {
        return isHandle;
    }

    public void setHandle(boolean handle) {
        isHandle = handle;
    }

    public String getComplain() {
        return complain_data;
    }

    public void setComplain(String complain) {
        this.complain_data = complain;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}