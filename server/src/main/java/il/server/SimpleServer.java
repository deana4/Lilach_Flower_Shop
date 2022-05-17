package il.server;

import il.entities.Flower;
import il.entities.User;
import il.server.ocsf.ConnectionToClient;
import il.server.ocsf.AbstractServer;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class SimpleServer extends AbstractServer {

    public SimpleServer(int port) throws Exception {
        super(port);
        System.out.println("Server listen on port:" + port);
//        testDB.initMySQL();
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

            if (cmd.getString("command").equals("getCatalogItems")) {
                List<Flower> flowerlist = testDB.getAllItems();
                client.sendToClient(flowerlist);
                System.out.println("send Flowers to catalog");
            }

            if(cmd.getString("command").equals("setPrice")){
                int id = cmd.getInt("id");
                int price = cmd.getInt("newPrice");
                testDB.setPrice(id, price);
            }
            if(cmd.getString("command").equals("setImages")){
                int id = cmd.getInt("id");
                String bytes64 = cmd.getString("newImage");
                byte[] bFile = Base64.getDecoder().decode(bytes64);
                testDB.setImage(id, bFile);
            }
            if(cmd.getString("command").equals("register")){
                String username = cmd.getString("username");
                String name = cmd.getString("name");
                String pass = cmd.getString("pass");
                String id = cmd.getString("id");
                String credit_card = cmd.getString("credit_card");
                String plan = cmd.getString("plan");

                User user = new User(username, pass,credit_card, plan, name, id);


                System.out.println("get register request:" + username);


            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("handleMessageFromClient Error!" + client.getInetAddress());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
