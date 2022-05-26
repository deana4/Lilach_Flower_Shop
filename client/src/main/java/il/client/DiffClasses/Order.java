package il.client.DiffClasses;

import il.client.CartItem;

import java.util.LinkedList;

public class Order {
    private static int id = 0;
    private int this_id;
    private String orderDate;
    private String orderTime;
    private String orderReceiveTime;
    private String orderReceiveDate;
    private String greeting;
    private String nameReceiver;
    private String phoneReceiver;
    private String Address;

    private LinkedList<CartItem> order_items = new LinkedList<>();
}
