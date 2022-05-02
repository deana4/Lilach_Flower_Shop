package il.server;


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


    private static void generateItems()throws Exception{

        LinkedList<Flower> flowerlist = new LinkedList<>();
        flowerlist.add(new Flower("ido", 20,"src/main/resources/com/client/images/whiteroses.jpeg",true,25));
        flowerlist.add(new Flower("ido1", 23,"src/main/resources/com/client/images/sunflower.jpeg",true, 5));
        flowerlist.add(new Flower("ido2", 20,"src/main/resources/com/client/images/pin.jpeg",false,0));
        flowerlist.add(new Flower("ido3", 20,"src/main/resources/com/client/images/chinaFlower.jpeg",false,0));
        flowerlist.add(new Flower("ido4", 20,"src/main/resources/com/client/images/pin.jpeg",false,0));
        flowerlist.add(new Flower("ido5", 20,"src/main/resources/com/client/images/whiteroses.jpeg",true,50));
        flowerlist.add(new Flower("ido6", 20,"src/main/resources/com/client/images/sunflower.jpeg",true,50));
        flowerlist.add(new Flower("ido7", 20,"src/main/resources/com/client/images/whiteroses.jpeg",true,50));
        flowerlist.add(new Flower("ido7", 20,"src/main/resources/com/client/images/sunflower.jpeg",true,50));
        flowerlist.add(new Flower("ido7", 20,"src/main/resources/com/client/images/pin.jpeg",true,50));
        flowerlist.add(new Flower("ido7", 20,"src/main/resources/com/client/images/whiteroses.jpeg",true,50));
        flowerlist.add(new Flower("ido7", 20,"src/main/resources/com/client/images/sunflower.jpeg",true,50));
        flowerlist.add(new Flower("ido7", 20,"src/main/resources/com/client/images/whiteroses.jpeg",true,50));
        for (Flower item : flowerlist) {
            session.save(item);
        }
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