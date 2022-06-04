package il.client.controls;

import il.client.SimpleClient;
import il.entities.Complain;
import il.entities.Message;

import java.io.IOException;

public class ComplainConrtol{

    public static void newComplain(Complain complain, int orderID) throws IOException {
        System.out.println("new complain to order "+ orderID);
        Message message = new Message("newComplain");
        message.setComplain(complain);
        message.setOrderID(orderID);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void complainAnswer(String answer, double refund, int complainID) throws IOException {
        Message message = new Message("complainAnswer");
        message.setComplainID(complainID);
        message.setAnswer(answer);
        message.setRefund(refund);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void testNewComplain() throws IOException {
        Complain complain = new Complain("it's test complain!", "04-06-2022");
        newComplain(complain, 1);
    }

    public static void testAnswerComplain() throws IOException {
        complainAnswer("it's a test answer!", 50, 4);
    }
}