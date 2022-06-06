package il.server;

import il.entities.*;

import java.io.IOException;
import java.util.LinkedList;


public class OrderControl {

    public static void deleteOrder(int id){
        testDB.openSession();
        Order a = testDB.session.get(Order.class, id);
        a.setStatus(1);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void cancelOrder(int id, String time, String date){
        System.out.println(time);
        System.out.println(date);
        String current_date = makeDate(date);
        testDB.openSession();
        Order a = testDB.session.get(Order.class, id);
        testDB.closeSession();
        int current_time = timeToInt(time);
        int order_time = timeToInt(a.getTimeReceive());
        if (current_date.equals(a.getDateReceive()) && order_time - current_time < 300) {
            if (order_time - current_time > 60)
                refund(a.getUser().getId(),0.5,a.getSum());
            deleteOrder(a.getId());
            return;
        }
        refund(a.getUser().getId(),1,a.getSum());
        deleteOrder(a.getId());
    }


    public static void refund(int id, double percent, double sum){
        testDB.openSession();
        User u = testDB.session.get(User.class, id);
        u.setCredit(u.getCredit()+(sum*percent));
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }


    public static String makeDate(String date){
        String[] parts = date.split("-");
        String year = parts[0];
        String month = parts[1];
        String day = parts[2];
        return day+'-'+month+'-'+year;
    }

    public static int timeToInt(String time){
        time = time.replace(":", "");
        if(time.length()>4)
            time = time.substring(0,4);
        return Integer.parseInt(time);
    }

    public static void newOrder(Order order, int storeID, int userID) throws IOException {
        testDB.openSession();
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

    public static LinkedList<Order> getAllOrder(LinkedList<Order> orders){
        LinkedList<Order> c = new LinkedList<>();
        for(Order order : orders){
            c.add(order.getOrderForClient());
        }
        return c;
    }
}
