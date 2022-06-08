package il.client.controls;

import il.client.CatalogController;
import il.client.SimpleClient;
import il.entities.CartProduct;
import il.entities.Complain;
import il.entities.Message;
import il.entities.Order;

import java.io.IOException;

public class OrderControl {


//    public static void testNewOrder() throws IOException {
//        Order order = new Order(null, null, "03-06-2022", "18:55","03-05-2022","1212", 0,"121212", "21212","", "");
//        order.addProduct(new CartProduct(CatalogController.getFlowerlist().get(0), 3));
//        order.addProduct(new CartProduct(CatalogController.getFlowerlist().get(2), 3));
//
//        newOrder(order, 1,1);
//    }
//
//    public static void testCancelOrder() throws IOException {
//        cancelOrder(4);
//    }

    //need to add here also double of the refund to save the refund in the user. just added the correct sign.
//    public static void cancelOrder(int orderID) throws IOException {
//        System.out.println("cancel order: "+ orderID);
//        Message message = new Message("cancelOrder");
//        message.setTimeCancel(java.time.LocalTime.now().toString());
//        message.setDateCancel(java.time.LocalDate.now().toString());
//        message.setOrderID(orderID);
//        message.setRefund(-1);
//        SimpleClient.getClient().sendToServer(message);
//    }

    //only use this one! cause sever doest calculate refund anymoreQ
    public static void cancelOrder(int orderID,double refund) throws IOException{
        System.out.println("cancel order: "+ orderID);
        Message message = new Message("cancelOrder");
//        message.setTimeCancel(java.time.LocalTime.now().toString());
//        message.setDateCancel(java.time.LocalDate.now().toString());
        message.setRefund(refund);
        message.setOrderID(orderID);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void deliverdOrder(int orderID) throws IOException {
        Message message = new Message("deliverdOrder");
        message.setOrderID(orderID);
        SimpleClient.getClient().sendToServer(message);
    }


    public static void newOrder(Order order, int storeID, int userID) throws IOException {
        System.out.println("made new order to store "+ storeID);
        Message message = new Message("newOrder");
        message.setOrder(order);
        message.setStoreID(storeID);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }
}

