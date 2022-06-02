package il.server;

import java.io.IOException;


/**
 * Hello world!
 *
 */


public class AppServer
{
    private static SimpleServer server;

    public static void main( String[] args ) throws IOException {
        try {
            server = new SimpleServer(3011);
            server.listen();
        }
        catch (Exception e){
            server.closeServer();
        }
    }

}
