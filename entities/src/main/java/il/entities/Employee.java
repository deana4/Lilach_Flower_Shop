package il.entities;

import java.io.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String password;
    private int permission;
    private String username;
    private String store;

    public Employee(){}

    public Employee(String name, String username, String pass, int permission, String store){
        this.name = name;
        this.username =username;
        this.password =pass;
        this.permission = permission; // 1:= system admin, 2:= store wide manager 3:= shop manager, 4:= service employee, 5: shop employee
        this.store = store;
    }

    /* gets and sets*/

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    /* end gets and sets*/
}
