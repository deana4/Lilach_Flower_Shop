package il.server;

import il.entities.Employee;
import il.entities.Message;
import il.entities.Product;
import il.entities.User;

public class UserControl {
    public static void setName(int userID, String newUserName, boolean isWorker){
        testDB.openSssion();
        User user = null;
        Employee employee=null;

        if(!isWorker){
            user = testDB.session.get(User.class, userID);
            if(user!=null){
                user.setName(newUserName);
            }
        }
        else{
            employee = testDB.session.get(Employee.class, userID);
            if(employee!=null)
                employee.setName(newUserName);
        }
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }
    public static void setPassword(int userID, String newPass, boolean isWorker){
        testDB.openSssion();
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
        testDB.openSssion();
        User user = null;
        user = testDB.session.get(User.class, userID);
        if (user != null) {
            user.setCreditCard(cc);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
            testDB.closeSession();
        }
    }
    public static void setPhone(int userID, String phone, boolean isWorker){
        if (isWorker)
            return;
        testDB.openSssion();
        User user = null;
        user = testDB.session.get(User.class, userID);
        if (user != null) {
            user.setPhone(phone);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
            testDB.closeSession();
        }
    }
    public static void setAddress(int userID, String address, boolean isWorker){
        if (isWorker)
            return;
        testDB.openSssion();
        User user = null;
        user = testDB.session.get(User.class, userID);
        if (user != null) {
            user.setAddress(address);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
            testDB.closeSession();
        }
    }
    public static void setMail(int userID, String mail, boolean isWorker){
        if (isWorker)
            return;
        testDB.openSssion();
        User user = null;
        user = testDB.session.get(User.class, userID);
        if (user != null) {
            user.setMail(mail);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
            testDB.closeSession();
        }
    }
}

