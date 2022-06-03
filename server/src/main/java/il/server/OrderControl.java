package il.server;

import il.entities.*;

import java.io.IOException;

public class OrderControl {

    public static void deleteOrder(Order a){
        testDB.openSession();
        testDB.session.delete(a);
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
                //a.get 50% refunds
                deleteOrder(a);
            return;
        }
        //a.get 100% refunds
        deleteOrder(a);
    }

    public static String makeDate(String date){
        return date.substring(7,9)+date.substring(4,7);
    }

    public static int timeToInt(String time){
        time = time.replace(":", "");
        time = time.substring(0,3);
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

    public static void newComplain(Complain complain, int orderID) throws IOException {
        testDB.openSession();
        System.out.println("new complain to order "+ orderID);
        Order order = testDB.session.get(Order.class, orderID);
        if(order==null){
            System.out.println("not maje new complain: not found order");
        }
        else{
            testDB.session.save(complain);
            Store store = order.getStore();
            User user = order.getUser();

            store.addComplain(complain);
            user.addComplain(complain);
            order.setComplain(complain);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
        }
        testDB.closeSession();
    }

    public static void complainAnswer(String answer, double refund, int complainID) throws IOException {
        Message message = new Message("complainAnswer");
        System.out.println("answer to complain "+ complainID);
        testDB.openSession();
        Complain complain = testDB.session.get(Complain.class, complainID);
        if(complain==null){
            System.out.println("not find complain "+complainID);
        }
        else{
            User user = complain.getUser();
            if(refund>0)
                user.setCredit(user.getCredit() + refund);
            complain.setAnswer_text(answer);
            complain.setRefund(refund);
            complain.setStatus(true);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
        }
        testDB.closeSession();
    }




}
