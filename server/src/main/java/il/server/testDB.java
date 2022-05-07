package il.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import il.entities.Flower;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class testDB {

    public static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Flower.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }


    private static void saveNewFlower (Flower flower, String url){
        File file;
        file = new File(url);
        byte[] bFile = new byte[(int) file.length()];
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            flower.setImage(bFile);
            session.save(flower);
            session.flush();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: load image to database.");
        }
    }

    private static void generateItems()throws Exception{
        Flower flower;

        flower = new Flower("whiteroses", 20,true,25);
        saveNewFlower(flower,"src/main/resources/images/whiteroses.jpeg" );

        flower = new Flower("sunflower", 23,true, 5);
        saveNewFlower(flower, "src/main/resources/images/sunflower.jpeg");

        flower = new Flower("chinaFlower", 20,false, 0);
        saveNewFlower(flower, "src/main/resources/images/chinaFlower.jpeg");

        flower = new Flower("pin", 20,false, 0);
        saveNewFlower(flower, "src/main/resources/images/pin.jpeg");

        flower = new Flower("whiteroses", 20,true, 50);
        saveNewFlower(flower, "src/main/resources/images/whiteroses.jpeg");

        flower = new Flower("sunflower", 20,true, 50);
        saveNewFlower(flower, "src/main/resources/images/sunflower.jpeg");

        session.flush();
        session.getTransaction().commit(); // Save everything.
    }

    public static List<Flower> getAllItems(){
        openSssion();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flower> query = builder.createQuery(Flower.class);
        query.from(Flower.class);
        List<Flower> data = session.createQuery(query).getResultList();
        LinkedList<Flower> listItems = new LinkedList<Flower>(data);
        closeSession();
        return listItems;
    }


    public static void openSssion(){
        try {
            System.out.println("open session to mySQL");
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error: cant open session to mySQL.");
            exception.printStackTrace();
        }
    }

    public static void setPrice(int id, int newPrice) throws IOException {
        openSssion();
        Flower a = session.get(Flower.class, id);
        a.setPrice(newPrice);
        session.flush();
        session.getTransaction().commit(); // Save everything.
        closeSession();
    }

    public static void setImage(int id, byte[] bFile){
        openSssion();
        Flower a = session.get(Flower.class, id);
        a.setImage(bFile);
        session.flush();
        session.getTransaction().commit(); // Save everything.
        closeSession();
    }


    public static void initMySQL(){
        try {

            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.out.println("open session to mySQL");
            generateItems();

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error: cant init mySQL!");
            exception.printStackTrace();
        } finally {
            session.close();
            System.out.println("close session to mySQL");
            System.out.println("init mySQL!");
        }
    }

    public static void closeSession(){
        try {
            if (session != null) {
                session.getTransaction().rollback();
            }
            session.close();
            System.out.println("close session to mySQL");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}