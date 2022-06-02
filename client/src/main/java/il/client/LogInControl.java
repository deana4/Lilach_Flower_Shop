package il.client;

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


    public static void logout(String username) throws IOException {
        Message message = new Message("logout");
        message.setUsername(username);

        SimpleClient.getClient().sendToServer(message);
    }
}
