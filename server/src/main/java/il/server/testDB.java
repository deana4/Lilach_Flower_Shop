package il.server;


import il.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class testDB {
    public static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Product.class).addAnnotatedClass(User.class)
                .addAnnotatedClass(Employee.class).addAnnotatedClass(Store.class).addAnnotatedClass(SystemAdmin.class)
                .addAnnotatedClass(StoreEmployee.class).addAnnotatedClass(NetworkManger.class).addAnnotatedClass(CustomerService.class)
                .addAnnotatedClass(BranchManager.class).addAnnotatedClass(Complain.class).addAnnotatedClass(Order.class)
        ;
        //configuration.addAnnotatedClass(Employee.class); //added this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void generateItems()throws Exception{
        Product product;

        product = new Product("whiteroses", 20,true,25,"flower", "white");
        CatalogControl.saveNewFlower(product,"src/main/resources/images/whiteroses.jpeg" );

        product = new Product("sunflower", 23,true, 5, "flower", "yellow");
        CatalogControl.saveNewFlower(product, "src/main/resources/images/sunflower.jpeg");

        product = new Product("chinaFlower", 20,false, 0, "flower", "red");
        CatalogControl.saveNewFlower(product, "src/main/resources/images/chinaFlower.jpeg");

        product = new Product("pin", 20,false, 0,"flower", "pink");
        CatalogControl.saveNewFlower(product, "src/main/resources/images/pin.jpeg");

        product = new Product("whiteroses", 20,true, 50, "flower", "white");
        CatalogControl.saveNewFlower(product, "src/main/resources/images/whiteroses.jpeg");

        product = new Product("sunflower", 20,true, 50, "flower", "yellow");
        CatalogControl.saveNewFlower(product, "src/main/resources/images/sunflower.jpeg");

        product = new Product("Lotus", 100, true, 10,"flower","pink");
        CatalogControl.saveNewFlower(product, "src/main/resources/images/Lotus.png");


        session.flush();

    }

//    //added this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    private static void generateEmployee()throws Exception {
//
//        Employee employee;
//
//        employee = new Employee("Malki Grossman", "malki123456" , "123456789", 1 , "All");
//
//        employee = new Employee("Shir Snea", "shir123456" , "123456789", 2 , "All");
//
//        employee = new Employee("Liran Eliav", "liran123456" , "123456789", 3 , "Store 1");
//
//        employee = new Employee("Dean Amar", "dean123456" , "123456789", 3 , "Store 2");
//
//        employee = new Employee("Ido Shitrit", "ido123456" , "123456789", 3 , "Store 3");
//
//        employee = new Employee("Roie Shahar", "roie123456" , "123456789", 3 , "Store 4");
//
//        employee = new Employee("Shahar Tavor", "shahar123456" , "123456789", 5 , "Store 1");
//
//        employee = new Employee("Itai Zeitony", "itai123456" , "123456789", 5 , "Store 2");
//
//        employee = new Employee("Shira Tzadok", "shira123456" , "123456789", 5 , "Store 3");
//
//        employee = new Employee("Gal Some", "gal123456" , "123456789", 5 , "Store 4");
//
//        employee = new Employee("Saar Gorman", "saar123456" , "123456789", 4 , "All");
//
//        employee = new Employee("Demian Brom", "demian123456" , "123456789", 4 , "All");
//
//        employee = new Employee("Shani Koren", "shanik98" , "123456789", 5 , "Store 1");
//
//        employee = new Employee("Shani Amar", "shani123456" , "123456789", 5 , "Store 2");
//
//        employee = new Employee("Aviv Shitrit", "aviv123456" , "123456789", 5 , "Store 3");
//
//        session.flush();
//        session.getTransaction().commit(); // Save everything.
//    }

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
            session.getTransaction().commit(); // Save everything.
           // generateEmployee(); //added this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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