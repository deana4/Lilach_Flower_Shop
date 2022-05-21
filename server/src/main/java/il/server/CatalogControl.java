package il.server;

import il.entities.Flower;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CatalogControl {

    public static void saveNewFlower (Flower flower, String url){
        File file;
        file = new File(url);
        byte[] bFile = new byte[(int) file.length()];
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            flower.setImage(bFile);
            testDB.session.save(flower);
            testDB.session.flush();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: load image to database.");
        }
    }

    public static List<Flower> getAllItems(){
        testDB.openSssion();
        CriteriaBuilder builder = testDB.session.getCriteriaBuilder();
        CriteriaQuery<Flower> query = builder.createQuery(Flower.class);
        query.from(Flower.class);
        List<Flower> data = testDB.session.createQuery(query).getResultList();
        LinkedList<Flower> listItems = new LinkedList<Flower>(data);
        testDB.closeSession();
        return listItems;
    }

    public static void setPrice(int id, int newPrice) throws IOException {
        testDB.openSssion();
        Flower a = testDB.session.get(Flower.class, id);
        a.setPrice(newPrice);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void setImage(int id, byte[] bFile){
        testDB.openSssion();
        Flower a = testDB.session.get(Flower.class, id);
        a.setImage(bFile);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }
}
