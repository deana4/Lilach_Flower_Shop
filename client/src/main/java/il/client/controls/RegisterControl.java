package il.client.controls;

import il.client.MainPageController;
import il.client.SimpleClient;
import il.entities.Message;
import il.entities.Store;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RegisterControl {

    public static void register(String name, String username, String pass, String id, String credit_card, int plan) throws IOException {
        Message message = new Message("register");
        message.setName(name);
        message.setUsername(username);
        message.setPass(pass);
        message.setId(id);
        message.setCredit_card(credit_card);
        message.setPlan(plan);

        System.out.println("send register requests to server:" + message.getUsername());

        SimpleClient.getClient().sendToServer(message);
    }

    public static void register(String name, String username, String pass, String id, String credit_card, int plan , List<Store> stores) throws IOException {
        Message message = new Message("register");
        message.setName(name);
        message.setUsername(username);
        message.setPass(pass);
        message.setId(id);
        message.setCredit_card(credit_card);
        message.setPlan(plan);
        message.setListStors((LinkedList<Store>) stores);

        System.out.println("send register requests to server:" + message.getUsername());

        SimpleClient.getClient().sendToServer(message);
    }

    public static void testRegister() throws IOException {
        System.out.println("Test register");
        register("test testt", "test55", "123456789", "315188744", "1234567812345678", 1, MainPageController.allStores);
    }

}
