package il.server;

import il.entities.Flower;
import il.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class LoginControl {
    public static String checkLogin(String userName, String pass, boolean isWorker){
        List<User> lUsers = RegisterControl.getAllUsers();

        for (User user: lUsers){
            if (user.getUserName().equals(userName)){
                if(user.getPassword().equals(pass)){
                    if(user.isLogin()){
                        return "this user already sighing from another device!";
                    }
                    setToActive(user.getId());
                    return "";
                }
                else{
                    return "incorrect password!";
                }
            }
        }
        return "username does not exist!";
    }

    private static void setToActive(int idUser){
        testDB.openSssion();
        User user = testDB.session.get(User.class, idUser);
        user.setLogin(true);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setToDiactive(String username){
        testDB.openSssion();
        User user = testDB.session.get(User.class, username);
        user.setLogin(false);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

}
