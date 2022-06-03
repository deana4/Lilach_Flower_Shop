package il.server;

import il.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class RegisterControl {

    public static<T> List<T> getAllItems(Class<T> object){
        testDB.openSession();
        CriteriaBuilder builder = testDB.session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(object);
        Root<T> root = query.from(object);
        List<T> data = testDB.session.createQuery(query.orderBy()).getResultList();
        LinkedList<T> listItems = new LinkedList<>(data);
        testDB.closeSession();
        return listItems;
    }



    public static String checknewUser(User newUser){
        List<User> lUsers = getAllItems(User.class);
//        compare_name c = new compare_name();
//        int result = Collections.binarySearch(lUsers,newUser, c.comparename());
        for (User user: lUsers){
            if (user.getUserName().equals(newUser.getUserName()) || user.getIdentifyNumbers().equals(newUser.getIdentifyNumbers()) ||
                    user.getCreditCard().equals(newUser.getCreditCard()))
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
        testDB.openSession();
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