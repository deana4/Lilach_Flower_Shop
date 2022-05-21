package il.server;

import il.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class LoginControl {

    public static User tryLogIn(String userName, String pass, boolean isWorker){
        testDB.openSssion();
        try{
            if(isWorker==false){
                CriteriaBuilder builder = testDB.session.getCriteriaBuilder();
                CriteriaQuery<User> query = builder.createQuery(User.class);
                query.from(User.class);
                List<User> data = testDB.session.createQuery(query).getResultList();
                for(User user : data){
                    if(user.getUserName().equals(userName) && user.getPassword().equals(pass))
                        return user;
                }
                return null;
            }
//            if(isWorker){
//                CriteriaBuilder builder = session.getCriteriaBuilder();
//                CriteriaQuery<Flower> query = builder.createQuery(Flower.class);
//                query.from(Flower.class);
//                List<Flower> data = session.createQuery(query).getResultList();
//            }

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: tryLogIn");
        }
        return null;
    }
}
