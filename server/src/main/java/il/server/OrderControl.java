package il.server;

import il.entities.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;





public class OrderControl {

//    public static void deleteOrder(int id){
//        testDB.openSession();
//        Order a = testDB.session.get(Order.class, id);
//        a.setStatus(1);
//        testDB.session.flush();
//        testDB.session.getTransaction().commit(); // Save everything.
//        testDB.closeSession();
//    }

    public static void cancelOrder(int id){
        testDB.openSession();
        Order a = testDB.session.get(Order.class, id);
        a.setStatus(1);
        a.setCanceled(true);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void sendNewOrder(Order o){
        try{
            String title = "New order from Lilach Store";
            String text = "Hey!, \n we receive you order number " + o.getId() + ",\n to "+o.getAddress()+".\n";
            SendEmail.sendTo(o.getUser().getMail(),title,text);
        }
        catch (Exception e){

        }
    }


    public static void deliverdOrder(int orderId)  {
        testDB.openSession();
        Order o = testDB.session.get(Order.class, orderId);
        o.setStatus(2);
        String email = o.getUser().getMail();
        String text = "Hey "+o.getUser().getName()+" we glad to inform you that you order has been shipped"+"\nHave a nice day, Lilach team.";
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
        SendEmail.sendTo(email,"Your Order has been shipped!",text);
        System.out.println("Order has been delivered");

    }


    public static void refund(int id, double refund){
        testDB.openSession();
        User u = testDB.session.get(User.class, id);
        u.setCredit(u.getCredit()+refund);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();

    }



    public static Order newOrder(Order order, int storeID, int userID) throws IOException {
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
        sendNewOrder(order);
        return order;
    }

    public static LinkedList<Order> getAllOrder(LinkedList<Order> orders){
        LinkedList<Order> c = new LinkedList<>();
        for(Order order : orders){
            c.add(order.getOrderForClient());
        }
        return c;
    }

    public static LinkedList<Order> getAllnOrders(int storeID){
        testDB.openSession();
        List<Order> complains = SimpleServer.getAllItems(Order.class);
        testDB.closeSession();
        LinkedList<Order> c = new LinkedList<>();
        for(Order comp : complains){
            if(storeID!=-1){
                if(comp.getStore().getId()==storeID)
                    c.add(comp.getOrderForClient());
            }
            else
                c.add(comp.getOrderForClient());
        }
        return c;
    }
}
