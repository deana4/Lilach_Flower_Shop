package il.server;

import il.entities.*;

import java.io.IOException;

public class OrderControl {

    public static void cancelOrder(int id){
        testDB.openSssion();
        Order a = testDB.session.get(Order.class, id);
        testDB.session.delete(a);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void newOrder(Order order, int storeID, int userID) throws IOException {
        testDB.openSssion();
        Store store = testDB.session.get(Store.class, storeID);
        User user = testDB.session.get(User.class, userID);

        if(!user.getListstore().contains(store)){
            System.out.println(user.getUserName() + " try to made order is store that he never register!");
        }
        else{
            for(CartProduct p : order.getProducts())
                testDB.session.save(p);

            testDB.session.save(order);
            user.addOrder(order);
            store.addOrder(order);
        }
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

}
