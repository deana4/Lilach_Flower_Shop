package il.entities;



import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name="employee")
public abstract class Employee implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "employee_name")
    protected String name;
    @Column(name = "employee_password")
    protected String password;
    protected int permission;
    protected String username;
    protected String identifyNumbers;
    boolean login;


    public Employee(){}

    public Employee(String name, String username, String pass){
        this.name = name;
        this.username =username;
        this.password =pass;
        this.login=false;

//        this.permission = permission; // 1:= system admin, 2:= store wide manager 3:= shop manager, 4:= service employee, 5: shop employee
    }

    public Employee(String name, String username, int permission){
        this.name = name;
        this.username = username;
        this.permission = permission;
    }

    /* gets and sets*/

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifyNumbers() {
        return identifyNumbers;
    }

    public void setIdentifyNumbers(String identifyNumbers) {
        this.identifyNumbers = identifyNumbers;
    }

    public int getId() {
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
//
//    public String getStore() {
//        return store;
//    }
//
//    public void setStore(String store) {
//        this.store = store;
//    }

    /* end gets and sets*/
}