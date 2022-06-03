package il.server;

import il.entities.Employee;
import il.entities.User;

import java.util.List;



public class LoginControl {

    public static String checkLogin(String userName, String pass, boolean isWorker) {
        String result = "";
        if (isWorker) {
            List<Employee> lEmp = RegisterControl.getAllItems(Employee.class);
            for (Employee employee : lEmp) {
                if (employee.getUsername().equals(userName)) {
                    if (employee.getPassword().equals(pass)) {
                        if (employee.isLogin()) {
                            result = "this user already sighing from another device!";
                            return result;
                        }
                        setToActiveEmp(employee.getId());
                    } else {
                        result = "incorrect password!";
                    }
                    return result;
                }
            }
        }
        else {
            List<User> lUsers = RegisterControl.getAllItems(User.class);
            for (User user : lUsers) {
                if (user.getUserName().equals(userName)) {
                    if (user.getPassword().equals(pass)) {
                        if (user.isLogin()) {
                            result = "this user already sighing from another device!";
                            return result;
                        }
                        setToActiveUser(user.getId());
                    } else {
                        result = "incorrect password!";
                    }
                    return result;
                }
            }
            result = "username does not exist!";
        }
        return result;
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
