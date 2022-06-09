package il.server;

import java.io.IOException;
import java.util.Scanner;


/**
 * Hello world!
 *
 */


public class Main
{
    private static SimpleServer server;


    public static int port = 3000;
    public static boolean initServer = false;
    public static String databaseName = "lilach_test";
    public static String databasePass = "1234";

    public static void getparameters(){
        System.out.println("----Lilach Server----");
        Scanner myObj = new Scanner(System.in);
        String init;
        System.out.println("Are you want to change the login MySql data? (y/n)");
        init = myObj.nextLine();
        while(!init.equals("y")&&!init.equals("n")){
            System.out.println("y - for yes, n - for no");
            init = myObj.nextLine();
        }
        if(init.equals("y")){
            System.out.println("database name: ");
            databaseName = myObj.nextLine();
            databaseName = "jdbc:mysql://localhost:3306/" + databaseName;
            System.out.println("database Paswword: ");
            databasePass = myObj.nextLine();
        }
        else{
            databaseName = "jdbc:mysql://localhost:3306/lilach_test";
            databasePass = "1234";
        }
        System.out.println("Are you want to init the server database? (y/n)");
        init = myObj.nextLine();
        while(!init.equals("y")&&!init.equals("n")){
            System.out.println("y - for yes, n - for no");
            init = myObj.nextLine();
        }
        initServer = init.equals("y");
        System.out.println("enter port to run: ");
        port = myObj.nextInt();
    }



    public static void main( String[] args ) throws IOException {
        try {
            getparameters();
            server = new SimpleServer(port, initServer);
            server.listen();
        }
        catch (Exception e){
            server.closeServer();
        }
    }

}
