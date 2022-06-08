package il.server;

import java.io.IOException;


/**
 * Hello world!
 *
 */


public class AppServer
{
    private static SimpleServer server;


    private static int port = 3010;
    private static boolean initServer = false;

    public static void main( String[] args ) throws IOException {
        try {
            server = new SimpleServer(port, initServer);
            server.listen();
        }
        catch (Exception e){
            server.closeServer();
        }
    }

}
