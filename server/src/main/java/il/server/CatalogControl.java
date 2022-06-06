package il.server;

import il.entities.Message;
import il.entities.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CatalogControl {

    public static void saveNewFlower (Product product, String url){
        File file;
        file = new File(url);
        byte[] bFile = new byte[(int) file.length()];
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            product.setImage(bFile);
            testDB.session.save(product);
            testDB.session.flush();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: load image to database.");
        }
    }


    public static void setPrice(int id, double newPrice) throws IOException {
        testDB.openSession();
        Product a = testDB.session.get(Product.class, id);
        a.setPrice(newPrice);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setImage(int id, byte[] bFile){
        testDB.openSession();
        Product a = testDB.session.get(Product.class, id);
        a.setImage(bFile);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setName(int id, String newName) throws IOException {
        testDB.openSession();
        Product a = testDB.session.get(Product.class, id);
        a.setName(newName);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setSale(int id, boolean sale, double discountPer) throws IOException {
        testDB.openSession();
        Product a = testDB.session.get(Product.class, id);
        a.setSale(sale);
        a.setDiscount_perc(discountPer);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void deleteItem(int id) throws IOException {
        testDB.openSession();
        Product a = testDB.session.get(Product.class, id);
        testDB.session.delete(a);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setType(int id, String type) throws IOException {
        testDB.openSession();
        Product a = testDB.session.get(Product.class, id);
        a.setType(type);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setColor(int id, String color) throws IOException {
        testDB.openSession();
        Product a = testDB.session.get(Product.class, id);
        a.setColor(color);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void newItem(Product item){
        testDB.openSession();
        testDB.session.save(item);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
        System.out.println("add new item: "+ item.getName());
    }

}
