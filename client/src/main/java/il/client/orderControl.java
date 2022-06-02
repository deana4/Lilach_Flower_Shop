package il.client;

import il.entities.Message;

import java.io.IOException;

public class orderControl {

    public static void cancelOrder(int orderID) throws IOException {
        Message message = new Message("cancelOrder");
        message.setOrderID(orderID);
        SimpleClient.getClient().sendToServer(message);
    }

}

