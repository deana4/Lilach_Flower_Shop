package il.client.controls;

import il.client.LoginController;
import il.client.SimpleClient;
import il.entities.Message;


import java.io.IOException;

public class LogInControl{




    public static void logIn(String username, String pass, boolean isWorker) throws IOException {
        Message message = new Message("login");
        message.setWorker(isWorker);
        message.setUsername(username);
        message.setPass(pass);
        System.out.println("test -login");

        SimpleClient.getClient().sendToServer(message);
    }


    public static void logout(int id) throws IOException {
        Message message = new Message("logout");
        message.setIddatabase(id);
        message.setWorker(LoginController.isWorker);
        SimpleClient.getClient().sendToServer(message);
    }
}
