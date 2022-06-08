package il.client.controls;

import il.client.SimpleClient;
import il.entities.Message;

import java.io.IOException;

public class UserControl {
    public static void setUserName(int userID, String newUserName, boolean isWorker) throws IOException {
        Message message = new Message("setInfo");
        message.setSetinfo("setUserName");
        message.setUsername(newUserName);
        message.setWorker(isWorker);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }
    public static void setName(int userID, String newName, boolean isWorker) throws IOException {
        Message message = new Message("setInfo");
        message.setSetinfo("setName");
        message.setName(newName);
        message.setWorker(isWorker);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }
    public static void setPassword(int userID, String newPass, boolean isWorker) throws IOException {
        Message message = new Message("setInfo");
        message.setSetinfo("setPassword");
        message.setPass(newPass);
        message.setWorker(isWorker);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }
    public static void setCreditCard(int userID, String cc, boolean isWorker) throws IOException{
        Message message = new Message("setInfo");
        message.setSetinfo("setCreditCard");
        message.setCredit_card(cc);
        message.setWorker(isWorker);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }
    public static void setPhone(int userID, String phone, boolean isWorker) throws IOException {
        Message message = new Message("setInfo");
        message.setSetinfo("setPhone");
        message.setPhone(phone);
        message.setWorker(isWorker);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }
    public static void setAddress(int userID, String address, boolean isWorker) throws IOException {
        Message message = new Message("setInfo");
        message.setSetinfo("setAddress");
        message.setAddress(address);
        message.setWorker(isWorker);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }
    public static void setMail(int userID, String mail, boolean isWorker) throws IOException {
        Message message = new Message("setInfo");
        message.setSetinfo("setMail");
        message.setMail(mail);
        message.setWorker(isWorker);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }

    public static  void testSetters() throws IOException {
        System.out.println("test setMail");
        setMail(1,"test@test.com", false);
        System.out.println("test set Name to worker");
        setName(1, "testName", true);

    }

    public static void setPermission(int userID, int permission, boolean isWorker) throws IOException {
        Message message = new Message("setInfo");
        message.setSetinfo("setPermission");
        message.setPermision(permission);
        message.setWorker(isWorker);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);

    }

    public static void setAccountStatus(int userID, int freeze, boolean isWorker) throws IOException { //1 not freeze, 0 freeze
        Message message = new Message("setInfo");
        message.setSetinfo("setAccountStatus");
        message.setAccountStatus(freeze);
        message.setWorker(isWorker);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }
}