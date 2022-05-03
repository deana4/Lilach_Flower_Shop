package il.server;

import il.entities.Flower;
import il.server.ocsf.ConnectionToClient;
import il.server.ocsf.AbstractServer;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.List;

public class SimpleServer extends AbstractServer {

    public SimpleServer(int port) throws Exception {
        super(port);
        System.out.println("Server listen on port:" + port);
        //testDB.initMySQL();
    }

    public void closeServer() throws IOException {
        testDB.closeSession();
        this.close();
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        try {
            System.out.print(client.getInetAddress() + ":");
            String msgString = msg.toString();
            System.out.println("get message: " + msgString);

            JSONObject cmd = new JSONObject(msgString);

            if (msgString.toLowerCase().equals("get catalog items")) {
                List<Flower> flowerlist = testDB.getAllItems();
                client.sendToClient(flowerlist);
                System.out.println("send Flowers to catalog");
            }

            if(cmd.getString("command").equals("setPrice")){
                int id = cmd.getInt("id");
                int price = cmd.getInt("newPrice");
                testDB.setPrice(id, price);
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("handleMessageFromClient Error!" + client.getInetAddress());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
