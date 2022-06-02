package il.server;

import il.entities.Order;
import il.entities.Product;
import il.entities.Store;

public class OrderControl {

    public static void cancelOrder(int id){
        testDB.openSssion();
        Order a = testDB.session.get(Order.class, id);
        testDB.session.delete(a);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

}
