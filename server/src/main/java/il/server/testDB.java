package il.server;


import il.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class testDB {
    public static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Product.class).addAnnotatedClass(User.class).addAnnotatedClass(Complain.class).addAnnotatedClass(Order.class)
                .addAnnotatedClass(Employee.class).addAnnotatedClass(Store.class).addAnnotatedClass(SystemAdmin.class)
                .addAnnotatedClass(StoreEmployee.class).addAnnotatedClass(NetworkManger.class).addAnnotatedClass(CustomerService.class)
                .addAnnotatedClass(BranchManager.class).addAnnotatedClass(CartProduct.class);
        ;
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void generateItems()throws Exception{
        Product flower;

        flower = new Product("whiteroses", 20,true,25,"flower", "white");
        CatalogControl.saveNewFlower(flower,"src/main/resources/images/whiteroses.jpeg" );

        flower = new Product("sunflower", 23,true, 5, "flower", "yellow");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/sunflower.jpeg");

        flower = new Product("chinaFlower", 20,false, 0, "flower", "red");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/chinaFlower.jpeg");

        flower = new Product("pin", 20,false, 0,"flower", "pink");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/pin.jpeg");

        flower = new Product("whiteroses", 20,true, 50, "flower", "white");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/whiteroses.jpeg");

        flower = new Product("sunflower", 20,true, 50, "flower", "yellow");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/sunflower.jpeg");

        flower = new Product("Lotus", 100, true, 10,"flower","pink");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");


        session.flush();
    }

    private static void generateStores(List<Product> products){
        SystemAdmin a = new SystemAdmin("admin admin", "admin", "adminadmin");
        testDB.session.save(a);
        Store store = new Store("Haifa");
        testDB.session.save(store);
        BranchManager b = new BranchManager("Malki Grossman", "malki123456" , "123456789", store);
        session.save(b);
        session.flush();

        StoreEmployee e = new StoreEmployee("Shir Snea", "shir123456" , "123456789");
        session.save(e);
        store.addEmployee(e);
        session.flush();

        e = new StoreEmployee("Liran Eliav", "liran123456" , "123456789");
        session.save(e);
        store.addEmployee(e);
        session.flush();

        Store store2 = new Store("Tel Aviv");
        testDB.session.save(store2);
        b = new BranchManager("Dean Amar", "dean123456" , "123456789", store2);
        session.save(b);

        e = new StoreEmployee("Ido Shitrit", "ido123456" , "123456789");
        session.save(e);
        store2.addEmployee(e);
        e = new StoreEmployee("Roie Shahar", "roie123456" , "123456789");
        session.save(e);
        store2.addEmployee(e);
        session.flush();

        Store store3 = new Store("Jerusalem");
        testDB.session.save(store3);
        b = new BranchManager("Itai Zeitony", "itai123456" , "123456789", store3);
        session.save(b);
        e = new StoreEmployee("Shira Tzadok", "shira123456" , "123456789");
        session.save(e);
        store3.addEmployee(e);
        e = new StoreEmployee("Shahar Tavor", "shahar123456" , "123456789");
        session.save(e);
        store3.addEmployee(e);


        User u1 = new User("ido7746", "123456789", "1234567812345678", "1", "ido", "123456789");
        User u2 = new User("haziza8", "123456789", "1234567812345678", "1", "dolev", "123456789");
        User u3 = new User("cr7", "123456789", "1234567812345678", "1", "cristiano", "123456789");
        User u4 = new User("robocolos", "123456789", "1234567812345678", "1", "ori shahr", "123456789");
        User u5 = new User("goat", "123456789", "1234567812345678", "1", "leo messi", "123456789");
        User u6 = new User("boom", "123456789", "1234567812345678", "1", "maldini", "123456789");

        testDB.session.save(u1);
        testDB.session.save(u2);
        testDB.session.save(u3);
        testDB.session.save(u4);
        testDB.session.save(u5);
        testDB.session.save(u6);

        store.addUser(u1);
        store.addUser(u2);
        store.addUser(u3);

        store2.addUser(u1);
        store2.addUser(u4);
        store2.addUser(u5);
        store2.addUser(u3);
        store2.addUser(u2);

        store3.addUser(u6);
        store3.addUser(u2);
        store3.addUser(u3);

        Order o = new Order(u1, store, "orde1", "asas","asas","1212", 100.0,"121212", "21212","", "");
        session.save(o);
        o.addProduct(new CartProduct(products.get(0), 1));
        o.addProduct(new CartProduct(products.get(1), 4));
        o.addProduct(new CartProduct(products.get(4), 2));
        for(CartProduct p : o.getProducts())
            session.save(p);

        o = new Order(u2, store2, "order2", "asas","asas","1212", 100.0,"121212", "21212","", "");
        session.save(o);
        o.addProduct(new CartProduct(products.get(5), 1));
        o.addProduct(new CartProduct(products.get(3), 1));
        o.addProduct(new CartProduct(products.get(1), 3));
        for(CartProduct p : o.getProducts())
            session.save(p);
        o.getProducts().get(1).incAmount();
        o.getProducts().get(0).decAmount();


        o = new Order(u6, store3, "order3", "asas","asas","1212", 100.0,"121212", "21212","", "");
        session.save(o);
        o.addProduct(new CartProduct(products.get(5), 4));
        o.addProduct(new CartProduct(products.get(3), 4));
        o.addProduct(new CartProduct(products.get(1), 3));
        for(CartProduct p : o.getProducts())
            session.save(p);
        o.getProducts().get(1).decAmount();
        o.getProducts().get(0).decAmount();
        o.getProducts().get(0).decAmount();

        session.flush();
    }

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

    public static void openSession(){
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
            openSession();
            generateItems();
            session.getTransaction().commit(); // Save everything.
            closeSession();
            List<Product> products = CatalogControl.getAllItems();
            openSession();
            generateStores(products);
            session.getTransaction().commit(); // Save everything.
            closeSession();

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