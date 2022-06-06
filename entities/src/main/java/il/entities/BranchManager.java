package il.entities;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class BranchManager extends Employee implements Serializable {

    @OneToOne
    private Store store;

    public BranchManager() {
        super();
    }

    public BranchManager(String name, String username, String pass, Store store) {
        super(name, username, pass);
        this.permission = 3;
        setStore(store);
    }


    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
        store.setManager(this);
    }
}

