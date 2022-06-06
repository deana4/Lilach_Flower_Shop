package il.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
public class CustomerService extends Employee implements Serializable {

    public CustomerService() {
        super();
    }

    public CustomerService(String name, String username, String pass) {
        super(name, username, pass);
        this.permission = 2;
    }

}
