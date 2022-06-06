package il.client.controls;

import il.client.SimpleClient;
import il.entities.Message;

import java.io.IOException;

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

}
