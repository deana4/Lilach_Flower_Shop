package il.entities;


import java.io.Serializable;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
public class StoreEmployee extends Employee implements Serializable {

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public StoreEmployee() {
        super();
    }

    public StoreEmployee(String name, String username, String pass) {
        super(name, username, pass);
        this.permission =1;
    }

}