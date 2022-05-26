package il.server;

import il.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class RegisterControl {



    public static List<User> getAllUsers(){
        testDB.openSssion();
        CriteriaBuilder builder = testDB.session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.orderBy(builder.asc(root.get("userName")));
        List<User> data = testDB.session.createQuery(query.orderBy()).getResultList();
        LinkedList<User> listItems = new LinkedList<>(data);
        testDB.closeSession();
        return listItems;
    }

    public static String checknewUser(User newUser){
        List<User> lUsers = getAllUsers();
//        compare_name c = new compare_name();
//        int result = Collections.binarySearch(lUsers,newUser, c.comparename());
        for (User user: lUsers){
            if (user.getUserName().equals(newUser.getUserName()) || user.getIdentifyNumbers().equals(newUser.getIdentifyNumbers()) || user.getCreditCard().equals(newUser.getCreditCard()))
                if (user.getUserName().equals(newUser.getUserName()))
                    return "Error: User has been used";
            if (user.getIdentifyNumbers().equals(newUser.getIdentifyNumbers()))
                return "Error: ID has been used";
            if (user.getCreditCard().equals(newUser.getCreditCard())) {
                if (user.getAccountStatus() == 0)
                    return "Error: credit_card belong to frozen account";
            }
        }
        return "";
    }



    public static boolean register(User newUser){
        testDB.openSssion();
        try {
            testDB.session.save(newUser);
            testDB.session.flush();
            testDB.session.getTransaction().commit();
            System.out.println("user add to mySQL");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: register");
        }
        testDB.closeSession();
        return false;
    }

}
