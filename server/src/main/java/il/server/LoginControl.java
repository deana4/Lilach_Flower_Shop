package il.server;

import il.entities.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class LoginControl {

    public static Message checkLogin(String userName, String pass, boolean isWorker) {
        Message message = new Message("result login");
        message.setWorker(isWorker);

        if (isWorker) {
            List<Employee> lEmp = RegisterControl.getAllItems(Employee.class);
            for (Employee employee : lEmp) {
                if (employee.getUsername().equals(userName)) {
                    if (employee.getPassword().equals(pass)) {
                        if (employee.isLogin()) {
                            message.setLoginResult("this user already sighing from another device!");
                            message.setLoginStatus(false);
                            return message;
                        }
                        setToActiveEmp(employee.getId());
                        message.setLoginStatus(true);
                        message.setWorker(true);
                        message.setUsername(employee.getUsername());
                        message.setName(employee.getName());
                        message.setPermision(employee.getPermission());
                        return message;

                    } else {
                        message.setLoginResult("incorrect password!");
                        message.setLoginStatus(false);
                        return message;
                    }
                }
            }
        }
        else {
            List<User> lUsers = RegisterControl.getAllItems(User.class);
            for (User user : lUsers) {
                if (user.getUserName().equals(userName)) {
                    if (user.getPassword().equals(pass)) {
                        if (user.isLogin()) {
                            message.setLoginResult("this user already sighing from another device!");
                            message.setLoginStatus(false);
                            return message;
                        }
                        setToActiveUser(user.getId());
                        message.setLoginStatus(true);
                        message.setWorker(false);



                        testDB.openSession();

                        message.setUser(user.getUserForClien());

                        user = testDB.session.get(User.class, user.getId());
                        message.setListOrder(user.getOrdersForClient());
                        message.setListComplains(user.getComplainsForClient());
                        message.setListStors(user.getStoresForClient());

                        testDB.closeSession();

                        return message;
                    } else {
                        message.setLoginResult("incorrect password!");
                        message.setLoginStatus(false);
                        return message;
                    }
                }
            }
        }
        message.setLoginResult("username does not exist!");
        message.setLoginStatus(false);
        testDB.closeSession();
        return message;
    }




    private static void setToActiveUser(int idUser){
        testDB.openSession();
        User user = testDB.session.get(User.class, idUser);
        user.setLogin(true);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }
    private static void setToActiveEmp(int idUser){
        testDB.openSession();
        Employee e = testDB.session.get(Employee.class, idUser);
        e.setLogin(true);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setToDiactiveU(int id){
        testDB.openSession();
        User a = testDB.session.get(User.class, id);
        a.setLogin(false);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setToDiactiveEmp(int id){
        testDB.openSession();
        Employee a = testDB.session.get(Employee.class, id);
        a.setLogin(false);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }


//
//        testDB.openSssion();
////        CriteriaBuilder builder = testDB.session.getCriteriaBuilder();
////        CriteriaQuery<User> query = builder.createQuery(User.class);
////        Root<User> root = query.from(User.class);
////        query.orderBy(builder.asc(root.get("userName")));
////        List<User> data = testDB.session.createQuery(query.orderBy()).getResultList();
////        LinkedList<User> listItems = new LinkedList<>(data);
////
////        for (User user : listItems){
////            if(user.getUserName().equals(username)){
////                user.setLogin(false);
////            }
////        }
//
//        testDB.session.flush();
//        testDB.session.getTransaction().commit(); // Save everything.
//        testDB.closeSession();
//    }

}
