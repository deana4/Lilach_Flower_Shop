package il.server;

import il.entities.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class UserControl {
    public static void setName(int userID, String newName, boolean isWorker){
        testDB.openSession();
        User user = null;
        Employee employee=null;

        if(!isWorker){
            user = testDB.session.get(User.class, userID);
            if(user!=null){
                user.setName(newName);
            }
        }
        else{
            employee = testDB.session.get(Employee.class, userID);
            if(employee!=null)
                employee.setName(newName);
        }
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setUserName(int userID, String newUserName, boolean isWorker){
        testDB.openSession();
        User user = null;
        Employee employee=null;

        if(!isWorker){
            user = testDB.session.get(User.class, userID);
            if(user!=null){
                user.setUserName(newUserName);
            }
        }
        else{
            employee = testDB.session.get(Employee.class, userID);
            if(employee!=null)
                employee.setUsername(newUserName);
        }
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }
    public static void setPassword(int userID, String newPass, boolean isWorker){
        testDB.openSession();
        User user = null;
        Employee employee=null;

        if(!isWorker){
            user = testDB.session.get(User.class, userID);
            if(user!=null){
                user.setPassword(newPass);
            }
        }
        else{
            employee = testDB.session.get(Employee.class, userID);
            if(employee!=null)
                employee.setPassword(newPass);
        }
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }
    public static void setCreditCard(int userID, String cc, boolean isWorker) {
        if (isWorker)
            return;
        testDB.openSession();
        User user = null;
        user = testDB.session.get(User.class, userID);
        if (user != null) {
            user.setCreditCard(cc);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
        }
        testDB.closeSession();
    }
    public static void setPhone(int userID, String phone, boolean isWorker){
        if (isWorker)
            return;
        testDB.openSession();
        User user = null;
        user = testDB.session.get(User.class, userID);
        if (user != null) {
            user.setPhone(phone);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
        }
        testDB.closeSession();
    }
    public static void setMail(int userID, String mail, boolean isWorker){
        if (isWorker)
            return;
        testDB.openSession();
        User user = null;
        user = testDB.session.get(User.class, userID);
        if (user != null) {
            user.setMail(mail);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
        }
        testDB.closeSession();
    }

    public static void setPermission(int userID, int permission, boolean isWorker) throws IOException {
        testDB.openSession();
        User user = null;
        Employee employee=null;

        if(!isWorker){
            user = testDB.session.get(User.class, userID);
            if(user!=null){
                user.setPriority(permission);
            }
        }
        else{
            employee = testDB.session.get(Employee.class, userID);
            if(employee!=null)
                employee.setPermission(permission);
        }
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();

    }

    public static void setAccountStatus(int userID, int freeze, boolean isWorker) throws IOException { //1 not freeze, 0 freeze
        if (isWorker)
            return;
        testDB.openSession();
        User user = null;
        user = testDB.session.get(User.class, userID);
        if (user != null) {
            user.setAccountStatus(freeze);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
        }
        testDB.closeSession();
    }

    public static void logOutAllusers(){
        testDB.openSession();
        CriteriaBuilder builder = testDB.session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        List<User> data = testDB.session.createQuery(query).getResultList();
        for (User user : data)
            user.setLogin(false);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();

        testDB.openSession();
        CriteriaBuilder builder2 = testDB.session.getCriteriaBuilder();
        CriteriaQuery<Employee> query2 = builder2.createQuery(Employee.class);
        query2.from(Employee.class);
        List<Employee> data2 = testDB.session.createQuery(query2).getResultList();
        for (Employee worker : data2)
            worker.setLogin(false);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }


    public static LinkedList<User> getAllnUser(){
        List<User> complains = SimpleServer.getAllItems(User.class);
        LinkedList<User> c = new LinkedList<>();
        for(User comp : complains){
            c.add(comp.getUserForClien());
        }
        return c;
    }

    public static LinkedList<Employee> getAllnEmployee(int storeID){
        testDB.openSession();
        List<Employee> complains = SimpleServer.getAllItems(Employee.class);
        testDB.closeSession();
        LinkedList<Employee> c = new LinkedList<>();
        for(Employee comp : complains){
            c.add(comp);
        }
        return c;
    }


}

