package il.client.controls;

import il.client.SimpleClient;
import il.entities.Product;
import il.entities.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CatalogControl {
    public static void getItemsList() throws IOException, ClassNotFoundException, InterruptedException {
        Message message = new Message("getCatalogItems");
        SimpleClient.getClient().sendToServer(message);
    }

    //url if the image in our computer
    public static void updateImage(String url, int idItem) throws IOException {
        try {
            File file;
            file = new File(url);
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] bFile = new byte[(int) file.length()];
            fileInputStream.read(bFile);
            fileInputStream.close();
            Message message = new Message("setImageItem");
            message.setIdItem(idItem);
            message.setbFile(bFile);
            SimpleClient.getClient().sendToServer(message);
        }
        catch (Exception e){
            if(!url.equals(""))
                System.out.println(e.getMessage());
        }
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
        if(sale==false || discount<=0)
            message.setDiscountPer(0);
        else
            message.setDiscountPer(discount);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void delteItem(int idItem) throws IOException { //Function doesn't work;
        Message message = new Message("deleteItem");
        message.setIdItem(idItem);
        SimpleClient.getClient().sendToServer(message);
    }

    //Need to add a method of adding item into the catalog list
    public static void addItem(Product product, String urlImage) throws IOException {
        System.out.println("test add item with image");
        byte[] bFile = new byte[0];
        try {
            File file;
            file = new File(urlImage);
            FileInputStream fileInputStream = new FileInputStream(file);
            bFile = new byte[(int) file.length()];
            fileInputStream.read(bFile);
            fileInputStream.close();
        }
        catch (Exception e){
            if(!urlImage.equals(""))
                System.out.println(e.getMessage());
        }
        product.setImage(bFile);
        Message message = new Message("newItem");
        message.setProduct(product);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void addItem(Product product) throws IOException {
        Message message = new Message("newItem");
        message.setProduct(product);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void setType(int idItem, String type) throws IOException {
        Message message = new Message("setTypeItem");
        message.setType(type);
        message.setIdItem(idItem);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void setColor(int idItem, String color) throws IOException {
        Message message = new Message("setColorItem");
        message.setColor(color);
        message.setIdItem(idItem);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void testNewItem() throws IOException {
        Product flower = new Product("testNewItem", 20,true,25,"flower", "white");
        String url = "C:\\Users\\IDO\\Desktop\\lilach\\server\\src\\main\\resources\\images\\whiteroses.jpeg";

        addItem(flower, url);
    }
}