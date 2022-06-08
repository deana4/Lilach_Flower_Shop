package il.entities;

import javax.persistence.*;
import java.io.Serializable;


@SuppressWarnings("serial")
@Entity
@Table(name = "complains")
public class Complain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Store store;

    @Column(name = "complain_date")
    private String date_complain;

    @OneToOne(fetch = FetchType.EAGER)
    private Order order;

    private double refund;
    private boolean status;
    private String complain_text;
    private String answer_text;

    public String getTimeComplain() {
        return timeComplain;
    }

    public void setTimeComplain(String timeComplain) {
        this.timeComplain = timeComplain;
    }

    private String timeComplain;


    public Complain(String complain_text, String date_complain, String timeComplain) {
        this.complain_text = complain_text;
        this.date_complain = date_complain;
        this.status=true;
        this.refund = 0;
        this.answer_text="";
        this.timeComplain = timeComplain;
    }

    public Complain getComplainForClient(){
        Complain c =  new Complain(this.complain_text, this.date_complain,this.timeComplain);
        c.setRefund(this.refund);
        c.setAnswer_text(this.answer_text);
        c.setId(this.id);
        return c;

    }

    public Complain() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User complain_user) {
        this.user = complain_user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getDate_complain() {
        return date_complain;
    }

    public void setDate_complain(String date_complain) {
        this.date_complain = date_complain;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getComplain_text() {
        return complain_text;
    }

    public void setComplain_text(String complain_text) {
        this.complain_text = complain_text;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

}