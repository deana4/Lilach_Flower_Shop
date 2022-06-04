package il.server;

import il.entities.*;

import java.io.IOException;

public class ComplainConrtol{

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
        System.out.println("answer to complain "+ complainID);
        testDB.openSession();
        Complain complain = testDB.session.get(Complain.class, complainID);
        if(complain==null){
            System.out.println("not find complain "+complainID);
        }
        else{
            User user = complain.getUser();
            if(refund>0)
                user.setCredit(user.getCredit() + ((refund/100)*complain.getOrder().getSum()));
            complain.setAnswer_text(answer);
            complain.setRefund(refund);
            complain.setStatus(false);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
        }
        testDB.closeSession();
    }

//    public static LinkedList<Complain> getAllOpenComplaint(){
//        LinkedList<Complain> c = new LinkedList<>();
//        List<Complain> complains = getAllItems(Complain.class);
//        for(Complain comp : complains){
//            if(comp.isStatus())
//                c.add(comp);
//        }
//        return c;
//    }



}