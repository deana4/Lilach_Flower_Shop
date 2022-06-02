package il.entities;

import java.io.Serializable;

import javax.persistence.Entity;



@Entity
public class NetworkManger extends Employee implements Serializable {

    public NetworkManger() {
        super();
    }

    public NetworkManger(String name, String username, String pass) {
        super(name, username, pass);
        this.permission = 2;
    }

}

