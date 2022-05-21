package il.server;

import il.entities.User;

public class RegisterControl {

    public static boolean register(User newUser){
        testDB.openSssion();
        try {
            testDB.session.save(newUser);
            testDB.session.flush();
            testDB.session.getTransaction().commit();
            System.out.println("user add to mySQL");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: register");
        }
        testDB.closeSession();
        return false;
    }

}
