package il.client.events;

import il.entities.Order;

public class OrderEvent {


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderEvent(Order order) {
        this.order = order;
    }

    Order order;

}
