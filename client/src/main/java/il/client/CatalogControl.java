package il.client;

import il.entities.Message;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class CatalogControl {
    public static void getItemsList() throws IOException, ClassNotFoundException, InterruptedException {
        Message message = new Message("getCatalogItems");
        SimpleClient.getClient().sendToServer(message);
    }

    public static void updateImage(String url, int idItem) throws IOException {
        File file;
        file = new File(url);
        byte[] bFile = new byte[(int) file.length()];
        Message message = new Message("setImageItem");
        message.setIdItem(idItem);
        message.setbFile(bFile);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void setPrice(int idItem, double newPrice) throws IOException {
        Message message = new Message("setPriceItem");
        message.setNewPrice(newPrice);
        message.setIdItem(idItem);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void setName(String newName, int idItem) throws IOException {
        Message message = new Message("setNameItem");
        message.setNameProduct(newName);
        message.setIdItem(idItem);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void setSale(boolean sale, int idItem, double discount) throws IOException {
        Message message = new Message("setSaleItem");
        message.setSale(sale);
        message.setIdItem(idItem);
        if(sale==false)
            message.setDiscountPer(0);
        else
            message.setDiscountPer(discount);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void delteItem(int idItem) throws IOException {
        Message message = new Message("deleteItem");
        message.setIdItem(idItem);
        SimpleClient.getClient().sendToServer(message);
    }
}
