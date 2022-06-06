package il.entities;

import java.io.Serializable;

import javax.persistence.Entity;


@SuppressWarnings("serial")
@Entity
public class SystemAdmin extends Employee implements Serializable {

    public SystemAdmin() {
        super();
    }

    public SystemAdmin(String name, String username, String pass) {
        super(name, username, pass);
        this.permission = 5;
    }

}

