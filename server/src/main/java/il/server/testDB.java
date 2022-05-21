package il.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import il.entities.Flower;
import il.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class testDB {
    public static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Flower.class).addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void generateItems()throws Exception{
        Flower flower;

        flower = new Flower("whiteroses", 20,true,25);
        CatalogControl.saveNewFlower(flower,"src/main/resources/images/whiteroses.jpeg" );

        flower = new Flower("sunflower", 23,true, 5);
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/sunflower.jpeg");

        flower = new Flower("chinaFlower", 20,false, 0);
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/chinaFlower.jpeg");

        flower = new Flower("pin", 20,false, 0);
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/pin.jpeg");

        flower = new Flower("whiteroses", 20,true, 50);
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/whiteroses.jpeg");

        flower = new Flower("sunflower", 20,true, 50);
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/sunflower.jpeg");

        session.flush();
        session.getTransaction().commit(); // Save everything.
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