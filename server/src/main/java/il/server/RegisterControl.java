package il.server;

import il.entities.User;

public class RegisterControl {

    public static void register(User newUser){
        testDB.openSssion();
        try {
            testDB.session.save(newUser);
            testDB.session.flush();
            testDB.session.getTransaction().commit();
            System.out.println("user add to mySQL");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: register");
        }
        testDB.closeSession();
    }

}
